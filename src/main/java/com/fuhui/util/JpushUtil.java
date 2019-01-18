package com.fuhui.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushUtil {

    private static final String MASTER_SECRET = "2c987b3961b51e891fb5bf43";//JPush服务器端下发的master_key
    private static final String APP_KEY = "f729db7cdd6c47547cc7daed";//JPush服务器端下发的app_key
    private static JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);

    /*******************自定义消息提示***********************/
    /**
     * 发送给所有用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @return 0推送失败，1推送成功
     */
    public static int sendToAll(String notification_title, String msg_title, String msg_content, Map<String, String> map) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushUtil.buildPushObject_android_and_ios(msg_content, msg_title, map);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有用户
     *
     * @return 0推送失败，1推送成功
     */
    public static int sendToTags(String msgContent, String msgTitle, Map<String, String> map, String... tags) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushUtil.buildPushObject_all_tag(msgContent, msgTitle, map, tags);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有用户
     *
     * @param msgContent 消息内容
     * @return 0推送失败，1推送成功
     */
    public static int sendToAliases(String msgTitle, String msgContent, String aliases, Map<String, String> map) {
        int result = 0;
        List list = new ArrayList();
        list.add(aliases);
        try {
            PushPayload pushPayload = JpushUtil.buildPushObject_all_aliases(msgContent, msgTitle, list, map);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 消息推送全部平台全部应用
     *
     * @return
     */
    public static PushPayload buildPushObject_android_and_ios(String msgContent, String msgTitle, Map<String, String> map) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                //控制设备pad声音不响
                /*.setNotification(Notification.newBuilder()
                        .setAlert(notification_title)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key", extrasparam)
                                .build()
                        )
                        .build()
                )*/

                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .setTitle(msgTitle)
                        .addExtras(map)
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build()
                )
                .build();
    }

    /**
     * 构建推送对象：平台是 all，推送目标是 tags(可以是一个设备也可以是多个设备)，推送内容同时包括通知与消息 - 消息内容是 msgContent。
     * 通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。
     * APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
     *
     * @param msgContent
     * @param tags
     * @return
     */
    public static PushPayload buildPushObject_all_tag(String msgContent, String msgTitle, Map<String, String> map, String... tags) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag_and(tags))
                //控制设备推送极光令消息通知无声音
                /*.setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert("msgContent")
                                .setTitle("测试tags")
                                .addExtra("from", "JPush")
                                .build())
                        .build())*/
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .setTitle(msgTitle)
                        .addExtras(map)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    /**
     * 构建推送对象：平台是 all，推送的设备有（推送目标为tags和推送目标别名为aliases），推送内容是 - 内容为 msg_content的消息。
     *
     * @param aliases
     * @return
     */
    public static PushPayload buildPushObject_all_aliases(String msgContent, String msgTitle, List aliases, Map<String, String> map) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.newBuilder()
                        //.addAudienceTarget(AudienceTarget.tag(tags))
                        .addAudienceTarget(AudienceTarget.alias(aliases))
                        .build())
                //控制设备推送极光令消息通知无声音
                /*.setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg_content)
                                .addExtras(map)
                                .build())
                        .build())*/
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .setTitle(msgTitle)
                        .addExtras(map)
                        .build())
                .build();
    }

    /***********自定义推送信息************/

    public static void main(String[] args) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("type", "20");
            Integer jg = JpushUtil.sendToAliases("", "", "ac83f3dd5eb0", map);
            System.out.println("result==" + jg);
        } catch (Exception e) {
            System.out.print("极光连接错误***" + e);
        }
    }
}