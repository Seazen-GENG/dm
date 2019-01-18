package com.fuhui.controller.manager;

import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.tut.OauthTokenMapper;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.tut.OauthToken;
import com.fuhui.util.HTTPUtils;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OauthManage {
    @Autowired
    private PadMapper padMapper;

    @Autowired
    private OauthTokenMapper oauthTokenMapper;

    @Autowired
    private Param param;

    public Map<String, Object> getToken(String padMac, String appId) {
        SnowFlake snowFlake = new SnowFlake(2, 3);
        Map<String, Object> map = new HashMap<>();
        /**************token************************/
        long tokenId = snowFlake.nextId();
        String token = "";
        String expires_in = "";
        Pad padAuthorize = padMapper.getInfoByPadMac(padMac);
        try {
            String url = param.getOauthApi() + "&client_id=" + appId + "&username=" + padMac + "&" + param.getRedirect();
            System.out.println(url);
            String result = HTTPUtils.doGet(url);
            JSONObject json_test = JSONObject.fromObject(result);
            String access_token = json_test.get("access_token").toString();
            expires_in = json_test.get("expires_in").toString();
            System.out.println("****==access_token:" + json_test.get("access_token") + "****==expires_in:" + expires_in);
            token = access_token;
            System.out.println("token****" + token);
            ////**************************将token信息添加到库********************************************///
            OauthToken tokenEntity = new OauthToken();
            if(padAuthorize != null){
                tokenEntity.setiPadid(padAuthorize.getiPadid());
            }
            tokenEntity.settUtime(new Date());
            long nowMillis = System.currentTimeMillis() + Long.valueOf(expires_in) * 1000;//一分钟过期+60000
            Date now = new Date(nowMillis);//过期时间（暂时库中无字段）
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(nowMillis + "过期时间***" + sdf.format(now));
            tokenEntity.setcMac(padMac);
            tokenEntity.setiAppid(Long.valueOf(appId));
            tokenEntity.setcToken(token);
            //先判断是否使web端获取token
            if(padAuthorize != null ){
                //有则更新无则添加
                OauthToken oauthToken = oauthTokenMapper.selectByPadId(padAuthorize.getiPadid());
                if (oauthToken == null || oauthToken.equals("{}")) {
                    tokenEntity.setiId(tokenId);
                    tokenEntity.settCtime(new Date());
                    oauthTokenMapper.insertSelective(tokenEntity);
                } else {
                    tokenEntity.setiId(oauthToken.getiId());
                    oauthTokenMapper.updateByPrimaryKeySelective(tokenEntity);
                }
            }else {
                //暂时web端默认新建
                tokenEntity.setiId(tokenId);
                tokenEntity.settCtime(new Date());
                oauthTokenMapper.insertSelective(tokenEntity);
            }
            map.put("access_token", access_token);
            map.put("expires_in", expires_in);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }

    /**
     * 判断结果集是否为空，为空则为过期
     *
     * @param token
     * @return
     */
    public Map<String, Object> checkToken(String token) {
        Map<String, Object> map = new HashMap<>();
        OauthToken oauthToken = oauthTokenMapper.selectByToken(token);
        String url = param.getCheckToken() + "?access_token=" + token;
        String result = HTTPUtils.doPost(url);
        if (result != null && result.equals("{\"code\":401,\"msg\":\"accessToken无效或已过期。\"}")) {
            oauthTokenMapper.deleteByPrimaryKey(oauthToken.getiId());//删除过期、无效token数据
            /*JSONObject json_test = JSONObject.fromObject(result);
            String code = json_test.get("code").toString();
            String msg = json_test.get("msg").toString();
            map.put()
            return map;*/
        } else {
            result = result.replace("\"", "");
//            Map<String, Object> map = new HashMap<>();
            map.put("padMac", result);
//            return ReturnJson.returnSuccessMap(map);
        }
        return map;
    }

}
