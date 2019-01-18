package com.fuhui.controller.service;


import com.fuhui.controller.manager.JPushManage;
import com.fuhui.controller.manager.OauthManage;
import com.fuhui.controller.manager.SchoolPadConfigManage;
import com.fuhui.dao.txm.AppconfigApplistMapper;
import com.fuhui.dao.txm.PadConfigMapper;
import com.fuhui.dao.txm.PadConfigconfigListMapper;
import com.fuhui.dao.txu.ClassMapper;
import com.fuhui.dao.txu.GradeMapper;
import com.fuhui.dao.txu.SchoolMapper;
import com.fuhui.entity.txm.*;
import com.fuhui.entity.txu.Class;
import com.fuhui.entity.txu.Grade;
import com.fuhui.entity.txu.School;
import com.fuhui.util.DateFromat;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("padService")
public class SchoolPadConfigService {

    @Autowired
    private PadConfigMapper padMapper;

    @Autowired
    private SchoolPadConfigManage padConfigManage;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private PadConfigconfigListMapper padConfigMapper;

    @Autowired
    private AppconfigApplistMapper appconfigApplistMapper;

    @Autowired
    private JPushManage jPushManage;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private PadConfigconfigListMapper padConfigconfigListMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(SchoolPadConfigService.class);

    /**
     * padService/padSaveConfig?name=1&schoolId=1232&range=0&rangeIds=1,2,3&remark=remark&status=1
     *
     * @return
     */
    @RequestMapping("padSaveConfig")
    public Object saveConfig(String configId, String name, String range, String type,
                             String rangeIds, String remark, String status, String contentList, String schoolId) {
        LOGGER.info(name);///////////////////////////
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SnowFlake snowFlake = new SnowFlake(2, 3);
        PadConfig padConfig = new PadConfig();
        Date date = new Date();
        long userId1 = 1;//需要使用token获取
        if (name == null || name.equals("")) {
            return ReturnJson.returnFail("参数异常");
        }
        LOGGER.info("configId===**有无配置id***" + configId);////
        if (configId == null || configId.equals("") || configId.equals("null")) {
            if (schoolId == null || schoolId.equals("")) {
                return ReturnJson.returnFail("参数异常");
            }
            long iConfigid = snowFlake.nextId();
            padConfig.setiConfigid(iConfigid);
            padConfig.setiSchoolid(Long.valueOf(schoolId));//
            padConfig.setiType(Integer.parseInt(type));//需要从页面传值
            padConfig.setcName(name);
            padConfig.setcRangeids(rangeIds);
            padConfig.setcRemark(remark);
            padConfig.settCtime(date);
            padConfig.settUtime(date);
            padConfig.setiCuserid(userId1);//操作人id登录后取值（创建人）
            padConfig.setiUuserid(userId1);//操作人id登录后取值（更新人）
            if (range != null) {
                padConfig.setiRange(Integer.parseInt(range));
            }
            if (status != null) {
                padConfig.setiStatus(Integer.parseInt(status));
            } else {
                padConfig.setiStatus(1);
            }
            LOGGER.info("padConfig===**参数对象***" + padConfig);////
            int result = padMapper.insertSelective(padConfig);
            LOGGER.info("result===**是否添加成功***" + result);////
            if (result > 0) {
                PadConfigconfigList padConfigList = new PadConfigconfigList();
                padConfigList.setiIdx(snowFlake.nextId());
                padConfigList.setiConfigid(iConfigid);
                //解析applist
                if (contentList != null) {
                    padConfigList.setcConfig(contentList);
                } else {
                    padConfigList.setcConfig("pc");
                }
                padConfigMapper.insertSelective(padConfigList);
                try {
                    jPushManage.sendMsg("12", range, schoolId, "all", "12", null);
                } catch (Exception e) {
                    LOGGER.info("发送极光失败!可能是由于极光系统检测不到此学校的极光信息！");
                    e.printStackTrace();
                }
                map.put("configId", iConfigid);
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            ////update更新后查询出学校设为极光发送对象
            padConfig.setiConfigid(Long.valueOf(configId));
            //padConfig.setiSchoolid(Long.valueOf("0"));
            padConfig.setcName(name);
            padConfig.setcRangeids(rangeIds);
            padConfig.setcRemark(remark);
            padConfig.settUtime(DateFromat.parse(sdf.format(new Date()), "yyyy-MM-dd HH:mm:ss", Locale.CHINA));
            padConfig.setiUuserid(userId1);//操作人id登录后取值（更新人）
            if (range != null) {
                padConfig.setiRange(Integer.parseInt(range));
            }
            if (status != null) {
                padConfig.setiStatus(Integer.parseInt(status));
            }
            int result = padMapper.updateByPrimaryKeySelective(padConfig);
            if (result > 0) {
                if (contentList != null) {
                    List<PadConfigconfigList> list = padConfigMapper.selectByConfigId(Long.valueOf(configId));
                    if (list == null || list.isEmpty()) {
                        //解析applist
                        PadConfigconfigList padConfigList = new PadConfigconfigList();
                        padConfigList.setiIdx(snowFlake.nextId());
                        padConfigList.setiConfigid(Long.valueOf(configId));
                        padConfigList.setcConfig(contentList);
                        padConfigMapper.insertSelective(padConfigList);
                    } else {
                        System.out.println("contentList==" + contentList);
                        PadConfigconfigList padConfigList = new PadConfigconfigList();
                        padConfigList.setiConfigid(Long.valueOf(configId));
                        padConfigList.setcConfig(contentList);
                        padConfigMapper.updateFinaly(padConfigList);
                    }
                }
                map.put("configId", configId);
                try {
                    PadConfig padConfig1 = padMapper.selectByPrimaryKey(Long.valueOf(configId));
                    jPushManage.sendMsg("12", range, String.valueOf(padConfig1.getiSchoolid()), "all", "12", null);
                } catch (Exception e) {
                    LOGGER.info("发送极光失败!可能是由于极光系统检测不到此学校的极光信息！");
                    e.printStackTrace();
                }
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail();
    }

    private int saveAppList(String isDel, AppconfigApplist appconfigApplist) {
        //isDel用于判断是否删除T_XM_AppConfig_AppListLog中的数据
        /*if (isDel == "1" || isDel.equals("1") ){
        }else {
        }*/
        int k = appconfigApplistMapper.saveAppList(appconfigApplist);
        if (k > 0) {
            return 1;
        } else {
            return 0;
        }
    }


    /**
     * 根据学校ID获取配置列表
     * 参数：padService/getConfigListBySchoolId?schoolIds=1&name=1&type=1&status=1&isPage=0&pNum=1&pLine=10
     * Integer schoolIds,  String name , Integer type,  Integer status, Integer pNum,Integer pLine, Integer isPage
     *
     * @return
     */
    @RequestMapping("getConfigListBySchoolId")
    public Object getConfigListBySchoolId(String schoolIds, String name, String isPage, String type,
                                          String status, String pNum, String pLine) {
        LOGGER.info("schoolIds===**参数***" + schoolIds);////
        Map<String, Object> mapList = new HashMap<String, Object>();
        Map<String, Object> mapPage = new HashMap<String, Object>();
        Integer type1 = 0;
        Integer status1 = -1;
        if (schoolIds == null && schoolIds.equals("")) {
            return ReturnJson.returnFail("参数异常");
        }
        if (type != null) {
            type1 = Integer.parseInt(type);
        }
        if (status != null) {
            status1 = Integer.parseInt(status);
        }
        Integer isPage1 = 0;
        if (isPage == null || isPage.equals("0")) {
            isPage1 = 0;
        } else if (isPage.equals("1")) {
            isPage1 = Integer.parseInt(isPage);
        }
        Integer pNum1 = 0;
        Integer pLine1 = 0;
        List<PadConfig> list = null;
        if (isPage1 > 0 || isPage1 == 1) {
            if (pNum != null && pLine != null && !pNum.equals("") && !pLine.equals("")) {
                pNum1 = Integer.parseInt(pNum);
                pLine1 = Integer.parseInt(pLine);
                list = padMapper.getList(schoolIds, name, type1, status1, pNum1 - 1, pLine1, isPage1);
                mapPage.put("pageNum", pNum);
                mapPage.put("pageLine", pLine);
                mapPage.put("countAll", list.size());
                mapList.put("page", mapPage);
                System.out.println("page==" + mapPage);
            } else {
                return ReturnJson.returnFail();
            }
        } else {
            list = padMapper.getList(schoolIds, name, type1, status1, null, null, 0);
        }
        LOGGER.info("list===**查询结果***" + list);////
        List<Map<String, Object>> listMap1 = new ArrayList<>();
        if (list != null) {
            for (PadConfig app : list) {
                Map<String, Object> map = new HashMap<>();
                if (app.getiConfigid() != null) {
                    map.put("configId", String.valueOf(app.getiConfigid()));
                } else {
                    map.put("configId", "");
                }
                if (app.getcName() != null) {
                    map.put("name", app.getcName());
                } else {
                    map.put("name", "");
                }
                if (app.getiSchoolid() != null) {
                    map.put("schoolId", String.valueOf(app.getiSchoolid()));
                } else {
                    map.put("schoolId", "");
                }
                if (app.getiRange() != null) {
                    map.put("range", app.getiRange());
                } else {
                    map.put("range", "");
                }
                if (app.getiType() != null) {
                    map.put("type", app.getiType());
                } else {
                    map.put("type", "");
                }
                if (app.gettCtime() != null) {
                    String date = DateFromat.format(app.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    map.put("cTime", date);
                } else {
                    map.put("cTime", "");
                }
                if (app.gettUtime() != null) {
                    String date = DateFromat.format(app.gettUtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    map.put("uTime", date);
                } else {
                    map.put("uTime", "");
                }
                if (app.getiStatus() != null) {
                    map.put("status", app.getiStatus());
                } else {
                    map.put("status", "");
                }
                List<PadConfigconfigList> pa = app.getPadConfigconfigLists();
                String str = "";
                if (pa != null && !pa.isEmpty()) {
                    for (PadConfigconfigList pad : pa) {
                        str += pad.getcConfig();
                    }
                }
                map.put("site", str);
                listMap1.add(map);
            }
            System.out.println("list==" + listMap1);
            if (listMap1 == null || listMap1.isEmpty()) {
                return ReturnJson.returnList(list);
            }
            mapList.put("list", listMap1);
        }
        return ReturnJson.returnSuccessMap(mapList);
    }


    @RequestMapping("getAllConfigList")
    public Object getAllConfigList(String name, String isPage, String type,
                                   String status, String pNum, String pLine, String schoolIds) {
        Map<String, Object> mapList = new HashMap<String, Object>();
        Map<String, Object> mapPage = new HashMap<String, Object>();
        Integer type1 = 0;
        Integer status1 = -1;
        if (type != null) {
            type1 = Integer.parseInt(type);
        }
        if (status != null) {
            status1 = Integer.parseInt(status);
        }
        Integer isPage1 = 0;
        if (isPage == null || isPage.equals("0")) {
            isPage1 = 0;
        } else if (isPage.equals("1")) {
            isPage1 = Integer.parseInt(isPage);
        }
        String finallySchoolId = "-1";
        if (schoolIds != null && !schoolIds.equals("")) {
            finallySchoolId = schoolIds;
        }
        Integer pNum1 = 0;
        Integer pLine1 = 0;
        List<PadConfig> list = null;
        if (isPage1 > 0 || isPage1 == 1) {
            if (pNum != null && pLine != null && !pNum.equals("") && !pLine.equals("")) {
                LOGGER.info("不执行");
                pNum1 = Integer.parseInt(pNum);
                pLine1 = Integer.parseInt(pLine);
                list = padMapper.getList(finallySchoolId, name, type1, status1, pNum1 - 1, pLine1, isPage1);
                mapPage.put("pageNum", pNum);
                mapPage.put("pageLine", pLine);
                mapPage.put("countAll", list.size());
                mapList.put("page", mapPage);
                System.out.println("page==" + mapPage);
            } else {
                return ReturnJson.returnFail();
            }
        } else {
            LOGGER.info("finallySchoolId===" + finallySchoolId);
            list = padMapper.getList(finallySchoolId, name, type1, status1, null, null, 0);
        }
        LOGGER.info("list===**查询结果***" + list);////
        List<Map<String, Object>> listMap1 = new ArrayList<>();
        if (list != null) {
            for (PadConfig app : list) {
                Map<String, Object> map = new HashMap<>();
                if (app.getiConfigid() != null) {
                    map.put("configId", String.valueOf(app.getiConfigid()));
                } else {
                    map.put("configId", "");
                }
                if (app.getcName() != null) {
                    map.put("name", app.getcName());
                } else {
                    map.put("name", "");
                }
                if (app.getiSchoolid() != null) {
                    map.put("schoolId", String.valueOf(app.getiSchoolid()));
                } else {
                    map.put("schoolId", "");
                }
                School school = schoolMapper.selectByPrimaryKey(app.getiSchoolid());
                if (school != null) {
                    map.put("schoolName", school.getcName());
                } else {
                    map.put("schoolName", "");
                }
                if (app.getiRange() != null) {
                    map.put("range", app.getiRange());
                } else {
                    map.put("range", "");
                }
                if (app.getiType() != null) {
                    map.put("type", app.getiType());
                } else {
                    map.put("type", "");
                }
                if (app.gettCtime() != null) {
                    String date = DateFromat.format(app.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    map.put("cTime", date);
                } else {
                    map.put("cTime", "");
                }
                if (app.gettUtime() != null) {
                    String date = DateFromat.format(app.gettUtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    map.put("uTime", date);
                } else {
                    map.put("uTime", "");
                }
                if (app.getiStatus() != null) {
                    map.put("status", app.getiStatus());
                } else {
                    map.put("status", "");
                }
                String str = " ";
                String extend = " ";
                if(app.getPadConfigconfigLists() != null){
                    List<PadConfigconfigList> pa = app.getPadConfigconfigLists();
                    if (pa != null && !pa.isEmpty()) {
                        for (PadConfigconfigList pad : pa) {
                            str += pad.getcConfig();
                            extend += pad.getcExtend1();
                        }
                    }
                }
                map.put("extend", extend);
                map.put("site", str);
                listMap1.add(map);
            }
            System.out.println("list==" + listMap1);
            if (listMap1 == null || listMap1.isEmpty()) {
                return ReturnJson.returnList(list);
            }
            mapList.put("list", listMap1);
        }
        return ReturnJson.returnSuccessMap(mapList);
    }

    /**
     * padService/getConfigInfo?configId=1&isShowRangeAll=0
     *
     * @return
     */
    @RequestMapping("getConfigInfo")
    public Object getConfigInfo(String configId, String isShowRangeAll) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (configId == null || configId.equals("")) {
            return ReturnJson.returnFail();
        }
        if (isShowRangeAll == null) {
            isShowRangeAll = "0";
        }
        PadConfig padConfig = padConfigManage.getInfo(configId);
        System.out.println("padConfig==" + padConfig);
        if (padConfig == null) {
            return ReturnJson.returnSuccessMap(map);
        }
        Integer rang = padConfig.getiRange();
        String rangIds = padConfig.getcRangeids();
        //整合数组
        map.put("configId", configId);
        map.put("name", padConfig.getcName());
        map.put("schoolId", padConfig.getiSchoolid());
        map.put("type", padConfig.getiType());
        map.put("remark", padConfig.getcRemark());
        map.put("status", padConfig.getiStatus());
        map.put("range", rang);
        map.put("rangeIds", rangIds);
        System.out.println("rang==" + rang + "*****rangIds==" + rangIds);
//        Map<String,Object> objectMap = new HashMap<String, Object>();
        if (rang == 1) {
            List<Grade> grades = gradeMapper.findByschoolidAndGradeId(padConfig.getiSchoolid(), rangIds);
            for (Grade g : grades) {
                System.out.println("grades==" + grades);
                Map<String, Object> objectMap = new HashMap<String, Object>();
                if (isShowRangeAll.equals("1")) {
                    objectMap.put("isAdd", 1);
                }
                objectMap.put("rangeId", g.getiGradeid());
                objectMap.put("rangeName", g.getcName());
                Date date = DateFromat.parse(g.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("date==" + sdf.format(date));
                objectMap.put("addTime", sdf.format(date));
                list.add(objectMap);
            }
            System.out.println("list==grades=" + list);
        } else if (rang == 2) {
            List<Class> entityClass = classMapper.findByschoolidAndRangIds(padConfig.getiSchoolid(), rangIds);
            for (Class c : entityClass) {
                System.out.println("grades==" + entityClass);
                Map<String, Object> objectMap = new HashMap<String, Object>();
                if (isShowRangeAll.equals("1")) {
                    objectMap.put("isAdd", 1);
                }
                objectMap.put("rangeId", c.getiClassid());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("date==" + sdf.format(c.gettCtime()));
                objectMap.put("addTime", sdf.format(c.gettCtime()));
                objectMap.put("rangeName", c.getcName());
                list.add(objectMap);
            }
            System.out.println("list==Class=" + list);
        }
        map.put("rangeIdList", list);
        List<Map<String, Object>> newPadList = new ArrayList<Map<String, Object>>();
        List<PadConfigconfigList> padList = padConfigMapper.selectByConfigId(Long.valueOf(configId));
        for (PadConfigconfigList pad : padList) {
            Map<String, Object> listMap = new HashMap<String, Object>();
            listMap.put("idx", pad.getiIdx());
            listMap.put("content", pad.getcConfig());
            listMap.put("extend1", pad.getcExtend1());
            newPadList.add(listMap);
        }
        System.out.println("newPadList==" + newPadList);
        map.put("contentList", newPadList);
        return ReturnJson.returnSuccessMap(map);
    }

    /**
     * padService/getNewInfoByPadMacAndType&padMac=1&type=1&configTime=2018-03-01 22:26:30
     *
     * @return
     */
    @RequestMapping("getNewInfoByPadMacAndType")
    public Object getNewInfoByPadMacAndType(String padMac, String type, String configTime) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        Integer type1 = 0;
        if (type != null) {
            type1 = Integer.parseInt(type);
        }
        if (configTime == null) {
            configTime = null;
        }
        Map list = padConfigManage.getNewInfoByPadMac(padMac, configTime, type1);
        if (list == null || list.isEmpty()) {
            return ReturnJson.returnSuccessMap(list);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("configList", list);
        return ReturnJson.returnSuccessMap(map);
    }

    /**
     * 即使管控API
     *
     * @param token
     * @param type
     * @param range
     * @param rangeIds
     * @param types
     * @return
     */
    @RequestMapping("setRealTimeData")
    public Object setRealTimeData(String token, String type, String range, String rangeIds, String types, String schoolId) {
        if (type == null || type.equals("") || types == null || types.equals("") || range == null || range.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        try {
            /*if (token == null || token.equals("")) {
                return ReturnJson.returnFail("没有携带有效令牌!");
            }
            Map<String, Object> map = oauthManage.checkToken(token);
            if (map == null || map.isEmpty()) {
                return ReturnJson.returnFail(401, "accessToken无效或已过期。");
            }*/
            int k = 0;
            //types = types.replace("\"", "");
            System.out.println("result****" + types);
            if (range.equals("0")) {
                System.out.println("jpush****全校");
                if (schoolId == null || schoolId.equals("")) {
                    return ReturnJson.returnFail("学校不能为空!");
                }
                k = jPushManage.sendMsg(type, range, schoolId, "all", types, "");
            } else {
                System.out.println("jpush****指定pad");
                if (rangeIds == null || rangeIds.equals(",")) {
                    return ReturnJson.returnFail("指定pad,Mac不能为空!");
                }
                k = jPushManage.sendMsg(type, range, rangeIds, "all", types, "");
            }
            if (k > 0) {
                return ReturnJson.returnSuccessMap();
            }
        } catch (Exception e) {
            return ReturnJson.returnFail(401, "accessToken无效或已过期。");
        }
        return ReturnJson.returnFail();
    }


}
