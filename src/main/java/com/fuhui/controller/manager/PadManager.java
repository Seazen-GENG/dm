package com.fuhui.controller.manager;

import com.fuhui.dao.model.PadInstallAppMapper;
import com.fuhui.dao.model.padConfigModelMapper;
import com.fuhui.dao.tpd.JPushMapper;
import com.fuhui.dao.tpd.PadBangDingLogMapper;
import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.txm.PadConfigMapper;
import com.fuhui.dao.txm.PadConfigconfigListMapper;
import com.fuhui.entity.model.PadInstallApp;
import com.fuhui.entity.model.padConfigModel;
import com.fuhui.entity.tpd.JPush;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.tpd.PadBangDingLog;
import com.fuhui.entity.txm.PadConfig;
import com.fuhui.entity.txm.PadConfigconfigList;
import com.fuhui.util.DateFromat;
import com.fuhui.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PadManager {

    @Autowired
    private PadMapper padMapper;

    @Autowired
    private JPushMapper jPushMapper;

    @Autowired
    private PadInstallAppMapper padInstallAppMapper;

    @Autowired
    private PadConfigMapper padConfigMapper;

    @Autowired
    private PadConfigconfigListMapper padConfigconfigListMapper;

    @Autowired
    private PadBangDingLogMapper padBangDingLogMapper;

    @Autowired
    private padConfigModelMapper padConfigModelMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(PadManager.class);

    public Pad getInfoByPadMac(String padMac) {
        System.out.println("padMac==" + padMac);
        Pad pad = padMapper.getInfoByPadMac(padMac);
        System.out.println("输出pad信息==" + pad);
        return pad;
    }

    public int add(Pad pad) {
        int result = padMapper.add(pad);
        if (result > 0) {
            return 1;
        }
        return 0;
    }

    public int update(Pad pad) {
        int result = padMapper.update(pad);
        if (result > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * success返回1，fail返回0
     *
     * @param padId
     * @param registrationId
     * @return
     */
    public int savePadJPush(String padId, String registrationId) {
        Date date = new Date();
        SnowFlake snowFlake = new SnowFlake(2, 3);
        Pad pad = padMapper.selectByPadId(Long.valueOf(padId));
        if (pad == null) {
            return 0;
        }
        System.out.println("getPad==**" + pad);
        JPush jPush = new JPush();
        ///判断极光信息表中数据是否已存在（存在更新，不存在新增）
        JPush jPushs = jPushMapper.getPadJPushInfoByPadId(Long.valueOf(padId));
        if (jPushs == null || jPushs.equals("{}")) {
            //重组成极光信息
            jPush.setiJpushid(snowFlake.nextId());
            jPush.setcAlias(pad.getcMac());
            jPush.setcTags(pad.getTags());
            jPush.setiPadid(pad.getiPadid());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = DateFromat.parse(sdf.format(date), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            System.out.println("date==" + sdf.format(date));
            jPush.settCtime(date1);
            jPush.settUtime(date1);
            jPush.setcRegistrationid(registrationId);
            int result = jPushMapper.savePadJPush(jPush);
            if (result > 0) {
                return 1;
            }
        } else {
            jPush.setiJpushid(jPushs.getiJpushid());
            jPush.setcAlias(pad.getcMac());
            jPush.setcTags(pad.getTags());
            jPush.setiPadid(pad.getiPadid());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = DateFromat.parse(sdf.format(date), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            System.out.println("date==" + sdf.format(date));
            jPush.settUtime(date1);
            jPush.setcRegistrationid(registrationId);
            int result = jPushMapper.updateByPrimaryKeySelective(jPush);
            if (result > 0) {
                return 1;
            }
        }
        return 0;
    }

    public List getList(String schoolIds, String padMac, String classId, long factoryId,
                        long deviceId, String model, String fVersion, Integer type,
                        Integer status, Integer isPage, Integer pNum, Integer pLine) {
        List list = padMapper.getPadList(schoolIds, padMac, classId, factoryId, deviceId, model, fVersion, type, status, isPage, pNum, pLine);
        return list;
    }

    public String countPad(String schoolIds, String padMac, String classId, long factoryId,
                           long deviceId, String model, String fVersion, Integer type,
                           Integer status) {
        Pad pad = padMapper.getPadCount(schoolIds, padMac, classId, factoryId, deviceId, model, fVersion, type, status);
        return pad.getCountAll();
    }

    ///安装记录
    public List<PadInstallApp> getInstallAppList(String mac, String isPage, Integer pNum, Integer pLine) {
        List<PadInstallApp> list = padInstallAppMapper.getPadAppLogList(mac, isPage, pNum, pLine);
        return list;
    }

    public String getInstallAppListCount(String mac) {
        String list = padInstallAppMapper.getPadAppLogListCount(mac);
        return list;
    }

    ////当前安装情况
    public List<PadInstallApp> getPadAppList(String mac) {
        List<PadInstallApp> list = padInstallAppMapper.getPadAppList(mac);
        return list;
    }

    public List<PadInstallApp> getAppInfoBySchool(Long schoolId) {
        List<PadInstallApp> list = padInstallAppMapper.getAppInfoBySchool(schoolId);
        return list;
    }

    public List<padConfigModel> getConfigList(String padId) {
        List<Map<String, Object>> returnList = new ArrayList<>();
        List<padConfigModel> list = null;
        try {
            list = padConfigModelMapper.getPadConfigModel(Long.valueOf(padId));
        } catch (Exception e) {
            System.out.println("数据问题!");
            e.printStackTrace();
        }
        return list;
    }

    public int savePadBinding(List<PadBangDingLog> bangding) {
        try {
            int k = padBangDingLogMapper.insertList(bangding);
            if (k > 0) {
                LOGGER.info("批量插入成功!");
                return 1;
            }
        } catch (Exception e) {
            LOGGER.info("批量插入异常!");
            e.printStackTrace();
        }
        return 0;
    }

    public void deletePadBindingLog(Long LogId) {
        padBangDingLogMapper.deleteByPrimaryKey(LogId);
    }
}
