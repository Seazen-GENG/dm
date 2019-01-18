package com.fuhui.controller.manager;

import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.txm.PadConfigMapper;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.txm.PadConfig;
import com.fuhui.entity.txm.PadConfigList;
import com.fuhui.util.DateFromat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SchoolPadConfigManage {

    @Autowired
    private PadConfigMapper padConfigMapper;

    @Autowired
    private PadMapper padMapper;


    public Object getList(String schoolIds, String name, Integer type, Integer status, Integer pNum, Integer pLine, Integer isPage) {
        if (isPage == 0 || isPage < 1) {
            pNum = null;
            pLine = null;
        } else {
            pNum = pNum - 1;
        }
        List list = padConfigMapper.getList(schoolIds, name, type, status, pNum, pLine, isPage);
        System.out.println("list==" + list);
        return list;
    }

    public PadConfig getInfo(String configId) {
        long iConfigId = Long.valueOf(configId);
        PadConfig padConfig = padConfigMapper.selectByPrimaryKey(iConfigId);
        return padConfig;
    }

    public Map getNewInfoByPadMac(String mac, String configTime, Integer type) {
        Pad pad = padMapper.getInfoByPadMac(mac);
        System.out.println("pad==" + pad);
        if (pad == null) {
            Map fMap = new HashMap();
            return fMap;
        }
        long SchoolId = -1;
        if (pad.getiSchoolid() != null) {
            SchoolId = pad.getiSchoolid();
        }
        long PadId = -1;
        if (pad.getiPadid() != null) {
            PadId = pad.getiPadid();
        }
        System.out.println("pad实体==" + pad);
        List<PadConfig> list = padConfigMapper.getNewInfoByPadMac(SchoolId, PadId, type, configTime);
        System.out.println("PadConfig==" + list);
        List mapList = new ArrayList();
        Map<String, Object> newMap = new HashMap<>();
        for (PadConfig padConfig : list) {
            Map<String, Object> padConfig1 = new HashMap<String, Object>();
            if (padConfig.getcName() != null) {
                padConfig1.put("name", padConfig.getcName());
            } else {
                padConfig1.put("name", "");
            }
            if (padConfig.getiType() != null) {
                padConfig1.put("type", padConfig.getiType());
            } else {
                padConfig1.put("type", "");
            }
            if (padConfig.getiConfigid() != null) {
                padConfig1.put("configId", String.valueOf(padConfig.getiConfigid()));
            } else {
                padConfig1.put("configId", "");
            }
            if (padConfig.getiStatus() != null) {
                padConfig1.put("status", padConfig.getiStatus());
            } else {
                padConfig1.put("status", "");
            }
            if (padConfig.gettUtime() != null) {
                String date = DateFromat.format(padConfig.gettUtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                System.out.println("date==" + date);
                padConfig1.put("uTime", date);
            } else {
                padConfig1.put("uTime", "");
            }
            List<PadConfigList> padConfigLists = padConfig.getPadConfigList();
            List newList = new ArrayList();
            for (PadConfigList p : padConfigLists) {
                Map<String, Object> padMap = new HashMap<String, Object>();
                if (p.getcConfig() != null) {
                    padMap.put("content", p.getcConfig());
                } else {
                    padMap.put("content", p.getcConfig());
                }
                if (p.getcExtend1() != null) {
                    padMap.put("extend1", p.getcExtend1());
                } else {
                    padMap.put("extend1", "");
                }
                newList.add(padMap);
            }
            padConfig1.put("contentList", newList);
            mapList.add(padConfig1);
        }
        newMap.put("configList", mapList);
        System.out.println("padList==" + newMap);
        return newMap;
    }


}
