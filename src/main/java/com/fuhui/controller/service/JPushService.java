package com.fuhui.controller.service;

import com.fuhui.controller.manager.JPushManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jPushService")
public class JPushService {

    @Autowired
    private JPushManage jPushManage;

    @RequestMapping("sendMsg")
    public Object sendMessage(String type, String range, String rangeIds, String platform, String msgContent, String msgTitle) throws Exception {
        if (range == null || range.equals("") || platform==null || platform.equals("") || type== null || type.equals("")){
            return 0;
        }
        int k = jPushManage.sendMsg(type, range, rangeIds, platform, msgContent, msgTitle);
        if (k > 0) {
            return 1;
        }
        return 0;
    }
}
