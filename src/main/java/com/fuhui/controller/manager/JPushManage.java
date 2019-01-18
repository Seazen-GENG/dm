package com.fuhui.controller.manager;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.fuhui.dao.tms.MessageMapper;
import com.fuhui.entity.tms.Message;
import com.fuhui.util.JpushUtil;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 极光推送
 */
@Service
public class JPushManage {

    private static final String MASTER_SECRET = "2c987b3961b51e891fb5bf43";//JPush服务器端下发的master_key
    private static final String APP_KEY = "f729db7cdd6c47547cc7daed";//JPush服务器端下发的app_key
    private static JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);

    @Autowired
    private MessageMapper messageMapper;


    /**
     * @return
     */
    public int sendMsg(String type, String range, String rangeIds, String platform, String msgContent, String msgTitle) throws Exception {
        int k = 0;
        SnowFlake snowFlake = new SnowFlake(2,3);
        try {
            if (platform.equals("all")) {
                k = publicMethod(type, range, rangeIds, msgContent, msgTitle);
            } else if (platform.equals("android")) {
                k = publicMethod(type, range, rangeIds, msgContent, msgTitle);
            } else if (platform.equals("winphone")) {
                k = publicMethod(type, range, rangeIds, msgContent, msgTitle);
            }
            Message message = new Message();
            message.setiMsgid(snowFlake.nextId());
            message.setcPlatform(platform);
            message.setiType(Integer.parseInt(type));
            message.settStime(new Date());
            message.setiSuserid(Long.valueOf("0"));
            message.setiStatus(1);
            if (msgTitle != null){
                message.setcTitle(msgTitle);
            }
            if (msgContent != null){
                message.setcMessage(msgContent);
            }
            if (rangeIds != null){
                message.setcAudience(rangeIds);
            }
            messageMapper.insertSelective(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public int publicMethod(String type, String range, String rangeIds, String msgContent, String msgTitle) {
        PushPayload pushPayload = null;
        Map<String, String> map = new HashMap<>();//
        map.put("type", type);
        try {
            if (range.equals("-1")) {
                pushPayload = JpushUtil.buildPushObject_android_and_ios(msgContent, msgTitle, map);
            } else if (range.equals("0") || range.equals("1") || range.equals("2") || range.equals("3")) {
                if (rangeIds == null || rangeIds.equals("")) {
                    return 0;
                }
                pushPayload = JpushUtil.buildPushObject_all_tag(msgContent, msgTitle, map, rangeIds);
            } else if (range.equals("4")) {
                if (rangeIds == null || rangeIds.equals("")) {
                    return 0;
                }
                //List list = new ArrayList();
                //list.add(rangeIds);
                //处理多个mac
                String[] arrayStr =new String[]{};
                arrayStr = rangeIds.split(",");
                List list = java.util.Arrays.asList(arrayStr);
                System.out.println("mac***list=="+list);
                pushPayload = JpushUtil.buildPushObject_all_aliases(msgContent, msgTitle, list, map);
            } else {
                return 0;
            }
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
