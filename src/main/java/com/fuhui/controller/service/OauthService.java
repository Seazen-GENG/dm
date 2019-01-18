package com.fuhui.controller.service;

import com.fuhui.controller.manager.OauthManage;
import com.fuhui.controller.manager.Param;
import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.trj.AppRjMapper;
import com.fuhui.dao.trj.OauthMapper;
import com.fuhui.dao.tut.OauthTokenMapper;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.Oauth;
import com.fuhui.entity.tut.OauthToken;
import com.fuhui.util.HTTPUtils;
import com.fuhui.util.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("oauth")
public class OauthService {

    @Autowired
    private PadMapper padMapper;

    @Autowired
    private Param param;

    @Autowired
    private OauthMapper oauthMapper;

    @Autowired
    private OauthManage oauthManage;

    @Autowired
    private OauthTokenMapper oauthTokenMapper;

    @Autowired
    private AppRjMapper appRjMapper;

    @RequestMapping("/v1/authorize")
    public Object test(String code) {
        try {
            if (code == null || code.equals("")) {
                return ReturnJson.returnFail("code不存在");
            }
            System.out.println("client_secret==" + param.getClient_secret());
            //拿到code然后携带参数请求token
            String url = param.getRequest() + "&code=" + code + "&client_id=" + param.getClient_id() + "&client_secret=" + param.getClient_secret() + "&" + param.getRedirect();
            String result = HTTPUtils.doPost(url);
            return result;
        } catch (Exception e) {
            return ReturnJson.returnFail("授权码已过期");
        }
    }

    /**
     * 请求token
     *
     * @param padMac
     * @param appId
     * @return
     */
    @RequestMapping("/v1/getToken")
    public Object oauth(String padMac, String appId, String key, HttpServletRequest request) {
        if (appId == null || appId.equals("") || key == null || key.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        param.setClient_id(appId);
        param.setClient_secret(key);
        /**************token************************/
        if (padMac == null || padMac.equals("")) {
            padMac = request.getRemoteAddr();
            Map<String, Object> map = oauthManage.getToken(padMac, appId);
            if (map == null || map.isEmpty()) {
                return ReturnJson.returnFail();
            } else {
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            Pad pad = padMapper.getInfoByPadMac(padMac);
            if (pad == null) {
                return ReturnJson.returnFail(220,"设备未注册!");
            }
            Oauth oauth = oauthMapper.selectByIdAndKey(Long.valueOf(appId), key);
            if (oauth == null) {
                return ReturnJson.returnFail(226,"没有此App,或key不正确!");
            }
            Map<String, Object> map = oauthManage.getToken(padMac, appId);
            if (map == null || map.isEmpty()) {
                return ReturnJson.returnFail();
            } else {
                return ReturnJson.returnSuccessMap(map);
            }
        }
        /**************************************/
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @RequestMapping("/v1/checkToken")
    public Object oauth(String token) {
        //查询数据库中token表
        OauthToken oauthToken = oauthTokenMapper.selectByToken(token);
        if (oauthToken != null) {
            Map<String, Object> map = oauthManage.checkToken(token);
            if (map != null && !map.isEmpty()) {
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail(102, "accessToken无效或已过期。");
    }

    @RequestMapping("saveAppOauth")
    public Object saveAppOauth(String appId){
        if (appId == null || appId.equals("")){
            return ReturnJson.returnFail("参数异常!");
        }
        //Oauth oauth = new Oauth();
        long  clientId = Long.valueOf(appId);
        AppRj  appRj = appRjMapper.selectByPrimaryKey(clientId);
        if (appRj != null ){
            Map<String,Object> map = new HashMap<>();
            Oauth oauth1 = oauthMapper.selectByPrimaryKey(clientId);
            if (oauth1 == null){
                Oauth oauth = new Oauth();
                oauth.setiAppid(clientId);
                oauth.setcKey(UUID.randomUUID().toString());
                oauth.settCtime(new Date());
                oauth.settUtime(new Date());
                oauth.setiIsreview(0);
                oauth.setiStatus(1);
                map.put("appId",appId);
                int k = oauthMapper.insert(oauth);
                if (k > 0){
                    return ReturnJson.returnSuccessMap(map);
                }
            }else {
                //更新
                oauth1.setiAppid(Long.valueOf(appId));
                oauth1.settUtime(new Date());
                map.put("appId",appId);
                int k = oauthMapper.updateByPrimaryKeySelective(oauth1);
                if (k > 0){
                    return ReturnJson.returnSuccessMap(map);
                }
            }
        }
        return ReturnJson.returnFail();
    }

}
