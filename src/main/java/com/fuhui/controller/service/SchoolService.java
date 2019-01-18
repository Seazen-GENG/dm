package com.fuhui.controller.service;

import com.fuhui.controller.manager.SchoolManage;
import com.fuhui.dao.taz.PadauthorizeMapper;
import com.fuhui.dao.tjc.StageMapper;
import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.txm.AppconfigApplistMapper;
import com.fuhui.dao.txm.AppconfigMapper;
import com.fuhui.dao.txu.AppMapper;
import com.fuhui.dao.txu.GradeMapper;
import com.fuhui.entity.taz.Padauthorize;
import com.fuhui.entity.tjc.Stage;
import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.AppVersion;
import com.fuhui.entity.txm.Appconfig;
import com.fuhui.entity.txm.AppconfigApplist;
import com.fuhui.entity.txu.App;
import com.fuhui.entity.txu.Grade;
import com.fuhui.entity.txu.School;
import com.fuhui.util.DateFromat;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("schService")
public class SchoolService {

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private SchoolManage schoolManage;

    @Autowired
    private AppconfigMapper appconfigMapper;

    @Autowired
    private AppconfigApplistMapper appconfigApplistMapper;

    @Autowired
    private PadauthorizeMapper padauthorizeMapper;

    @Autowired
    private StageMapper stageMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(SchoolPadConfigService.class);

    /**
     * 获取学校可用的应用列表
     * 参数==schService/getAppListBySchoolId?iSchoolid=1&iPlace=1&iStatus=1&isPage=0
     * 参数==?iSchoolid=1&iPlace=1&iStatus=1&isPage=1&pNum=1&pLine=10
     * 返回JSON
     *
     * @return
     */
    @RequestMapping("getAppListBySchoolId")
    public Object getAppListBySchoolId(String isPage, String schoolId, String pNum, String pLine) {
        Map<String, Object> map2 = new HashMap<String, Object>();
        List<App> list = new ArrayList<App>();
        Map<String, Object> pages = new HashMap<String, Object>();
        List mapList = new ArrayList();
        //判断是否分页
        long iSchoolid1 = -1;
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        } else {
            iSchoolid1 = Long.valueOf(schoolId);
        }
        Integer isPage1 = 0;
        if (isPage != null) {
            isPage1 = Integer.parseInt(isPage);
        }
        if (isPage1 != 0 || isPage1 == 1) {
            Integer pNum1 = 0;
            Integer pLine1 = 0;
            if (pNum != null && pLine != null) {
                pNum1 = Integer.parseInt(pNum);
                pLine1 = Integer.parseInt(pLine);
            } else {
                return ReturnJson.returnFail();
            }
            pages.put("pageNum", pNum);//pNum
            pages.put("pageLine ", pLine);//pLine
            list = appMapper.getAppListBySchoolId(iSchoolid1, null, null, pNum1 - 1, pLine1, isPage1);
            System.out.println("分页查询后的总记录数：" + list.size());
            pages.put("countAll", list.size());
            map2.put("pages", pages);
        } else {
            list = appMapper.getAppListBySchoolId(iSchoolid1, null, null, null, null, 0);
        }
        System.out.println("appList====" + list);
        AppRj appRj = new AppRj();
        AppVersion appVersion = new AppVersion();
        if (list.isEmpty() || list == null) {
            return ReturnJson.returnList(list);
        } else {
            for (App app : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                if (app.getiType() != null) {
                    map.put("type", app.getiType());
                } else {
                    map.put("type", "");
                }
                if (app.getiUway() != null) {
                    map.put("updateWay", app.getiUway());
                } else {
                    map.put("updateWay", "");
                }
                appRj = app.getAppRj();
                appVersion = app.getAppVersion();
                if (appRj.getiAppid() != null) {
                    map.put("appId", String.valueOf(appRj.getiAppid()));
                } else {
                    map.put("appId", "");
                }
                if (appRj.getcName() != null) {
                    map.put("name", appRj.getcName());
                } else {
                    map.put("name", "");
                }
                if (appRj.getcPackage() != null) {
                    map.put("package", appRj.getcPackage());
                } else {
                    map.put("package", "");
                }
                if (appVersion.getcDownurl() != null) {
                    map.put("downUrl", appVersion.getcDownurl());
                } else {
                    map.put("downUrl", "");
                }
                if (appVersion.getcVersion() != null) {
                    map.put("version", appVersion.getcVersion());
                } else {
                    map.put("version", "");
                }
                if (appRj.getcDesc() != null) {
                    map.put("desc", appRj.getcDesc());
                } else {
                    map.put("desc", "");
                }
                System.out.println("map==" + map);
                mapList.add(map);
            }
        }

        System.out.println("mapList==" + mapList);
        map2.put("list", mapList);
        return ReturnJson.returnSuccessMap(map2);
    }

    /**
     * 获取学校的年级列表
     * 根据schoolid
     * 参数== ?iSchoolid=1
     *
     * @return
     */
    @RequestMapping("getGradeListBySchoolId")
    public Object getGradeListBySchoolId(String schoolId) {
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> listMap = new HashMap<String, Object>();
        List mapList = new ArrayList();
        try {
            List<Grade> list = gradeMapper.getGradeListBySchoolId(Long.valueOf(schoolId), null);
            System.out.println("GradeList==" + list);
            if (list != null && !list.isEmpty()) {
                for (Grade grade : list) {
                    map.put("idx", String.valueOf(grade.getiXgradeid()));
                    map.put("gradeId", String.valueOf(grade.getiGradeid()));
                    map.put("name", grade.getcName());
                    map.put("code", grade.getcCode());
                    map.put("stageId", String.valueOf(grade.getiStageid()));
                    map.put("orderNo", grade.getiOrderno());
                    //setGrade.setStage(grade.getStage());
                    Stage stage = stageMapper.selectByPrimaryKey(grade.getiStageid());
                    map.put("stageName", stage.getcName());
                    mapList.add(map);
                }
                listMap.put("list", mapList);
                return ReturnJson.returnSuccessMap(listMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnSuccessMap(listMap);
    }

    @RequestMapping("seaveSchool")
    public Object seaveSchool(String schoolId, String name, String code, String address, String regionId, String calendarId, String remark, String status,
                              String serialNo, String isAddDef, String appList, String gradList, HttpServletRequest request) {
        LOGGER.info("添加学校start");
        SnowFlake snowFlake = new SnowFlake(2, 3);
        School school = new School();
        long iSchoolId;
        Integer isAddDefs = 0;
        if (name == null || name.equals("") || address == null || address.equals("") || appList == null || appList.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        LOGGER.info(sdf.format(date) + "====接收参数：" + name + "," + address + "," + serialNo + "," + isAddDef + "," + appList);
        HttpSession session = request.getSession();
        long userId = 0;
        try {
            userId = (Long) session.getAttribute("userId");
            LOGGER.info("获取userID=" + userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("session转换异常!");
            //System.out.println("session转换异常!");
        }
        {
            school.setcName(name);
            school.setcAddress(address);
            school.settUtime(sdf.format(date));
            school.setiUuserid(userId);
        }
        if (code != null) {
            school.setcCode(code);
        }
        if (regionId != null) {
            school.setiRegionid(Long.valueOf(regionId));
        }
        if (calendarId != null) {
            school.setiCalendarid(Long.valueOf(calendarId));
        }
        if (remark != null) {
            school.setcRemark(remark);
        }
        if (status == null || status.equals("")) {
            school.setiStatus(1);
        } else {
            school.setiStatus(Integer.parseInt(status));
        }
        if (isAddDef != null) {
            isAddDefs = Integer.parseInt(isAddDef);
        }
        if (schoolId == null || schoolId.equals("")) {
            //新增学校
            iSchoolId = snowFlake.nextId();
            school.setiSchoolid(iSchoolId);
            school.settCtime(sdf.format(date));
            school.setiCuserid(userId);
            LOGGER.info("参数school==" + school);
            int k = schoolManage.add(school);
            LOGGER.info("添加学校result==" + k);
            if (k > 0) {
                //授权
                try {
                    if (serialNo != null && !serialNo.equals("")) {
                        LOGGER.info("添加授权码==" + serialNo);
                        Padauthorize authorize = padauthorizeMapper.selectBySerialNo(serialNo);
                        if (authorize != null) {
                            LOGGER.info("授权码被占用！");
                            //将已添加的学校删除
                            schoolManage.delSchool(iSchoolId);
                            return ReturnJson.returnFail("授权码被占用！");
                        }
                        Padauthorize padauthorize = new Padauthorize();
                        padauthorize.setiAuzid(snowFlake.nextId());
                        padauthorize.setiSchoolid(iSchoolId);
                        padauthorize.setcSerialno(serialNo);
                        padauthorize.setiAType(2);
                        padauthorize.setiStatus(1);
                        padauthorize.setiType(0);
                        padauthorize.settCtime(new Date());
                        padauthorize.settUtime(new Date());
                        padauthorize.setiCuserid(userId);
                        int j = padauthorizeMapper.add(padauthorize);
                        LOGGER.info("添加授权码result==" + j);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.info("添加授权码error");
                    return ReturnJson.returnFail("授权操作有误!");
                }
                //添加应用学校关系表t_xu_app
                try {
                    LOGGER.info("添加关系表t_xu_app");
                    String appLists = "[" + appList + "]";
                    System.out.println(appLists);
                    JSONArray json = JSONArray.fromObject(appLists); // 首先把字符串转成 JSONArray  对象
                    if (json.size() > 0) {
                        for (int i = 0; i < json.size(); i++) {
                            JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            String appId = (String) job.get("appId");
                            String versionId = (String) job.get("versionId");
                            String type = (String) job.get("type");
                            App app = new App();
                            app.setiIdx(snowFlake.nextId());
                            app.setiAppid(Long.valueOf(appId));
                            app.setiVersionid(Long.valueOf(versionId));
                            app.setiType(Integer.parseInt(type));
                            app.setiSchoolid(iSchoolId);
                            app.settCtime(sdf.format(date));
                            app.settUtime(sdf.format(date));
                            app.setiUway(1);
                            app.setiStatus(1);
                            BigDecimal bigDecimal = new BigDecimal("1");
                            app.setnPrice(bigDecimal);
                            appMapper.insert(app);
                        }
                    }
                    LOGGER.info("添加关系表success");
                } catch (Exception e) {
                    LOGGER.info("添加关系表error");
                    e.printStackTrace();
                    return ReturnJson.returnFail("保存学校应用关系错误！");
                }
                if (isAddDefs > 0) {
                    LOGGER.info("添加配置表");
                    //新增配置表  "全校默认应用"
                    try {
                        Appconfig appconfig = new Appconfig();
                        long configId = snowFlake.nextId();
                        appconfig.setiConfigid(configId);
                        appconfig.setiSchoolid(iSchoolId);
                        appconfig.setiStatus(1);
                        appconfig.setiRange(0);
                        appconfig.setiUuserid(userId);//
                        appconfig.setiCuserid(userId);//
                        appconfig.settCtime(sdf.format(date));
                        appconfig.settUtime(sdf.format(date));
                        appconfig.setcName(name + "_新建配置");
                        LOGGER.info("添加配置表param==" + appconfig);
                        int jg = appconfigMapper.add(appconfig);
                        LOGGER.info("添加配置表result==" + jg);
                        //新增appList
                        String appLists = "[" + appList + "]";
                        System.out.println(appLists);
                        JSONArray json = JSONArray.fromObject(appLists);
                        for (int i = 0; i < json.size(); i++) {
                            JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            String appId = (String) job.get("appId");
                            AppconfigApplist appconfigApplist = new AppconfigApplist();
                            appconfigApplist.setiAppid(Long.valueOf(appId));
                            appconfigApplist.setiConfigid(configId);
                            appconfigApplist.setiCuserid(userId);
                            appconfigApplist.settCtime(sdf.format(date));
                            appconfigApplist.setiIdx(snowFlake.nextId());
                            appconfigApplistMapper.insertSelective(appconfigApplist);
                        }
                        LOGGER.info("添加appList==success" + jg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ReturnJson.returnFail("app列表有误！");
                    }
                }
                Map<String, Object> map = new HashMap<>();
                map.put("schoolId", String.valueOf(iSchoolId));
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            //更新学校
            int k = schoolManage.update(school);
            if (k > 0) {
                //更新授权
                try {
                    if (serialNo != null && !serialNo.equals("")) {
                        Padauthorize padauthorize = padauthorizeMapper.selectBySerialNo(serialNo);
                        if (padauthorize == null) {
                            padauthorize.setiAuzid(snowFlake.nextId());
                            padauthorize.setiSchoolid(Long.valueOf(schoolId));
                            padauthorize.setcSerialno(serialNo);
                            padauthorize.setiStatus(1);
                            padauthorize.settCtime(new Date());
                            padauthorize.settUtime(new Date());
                            padauthorize.setiCuserid(userId);
                            padauthorizeMapper.add(padauthorize);
                        } else {
                            Padauthorize padauthorize1 = new Padauthorize();
                            padauthorize1.setiAuzid(padauthorize.getiAuzid());
                            padauthorize1.setiSchoolid(Long.valueOf(schoolId));
                            padauthorize1.setcSerialno(serialNo);
                            padauthorize1.settUtime(new Date());
                            padauthorize1.setiUuserid(userId);
                            padauthorizeMapper.update(padauthorize1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ReturnJson.returnFail("更新授权操作有误!");
                }
                //添加应用学校关系表t_xu_app
                try {
                    //先删除之前关系
                    List<App> xuApp = appMapper.selectBySchoolId(Long.valueOf(schoolId));
                    if (!xuApp.isEmpty() || xuApp != null) {
                        appMapper.deleteBySchoolId(Long.valueOf(schoolId));
                    }
                    String appLists = "[" + appList + "]";
                    System.out.println(appLists);
                    JSONArray json = JSONArray.fromObject(appLists); // 首先把字符串转成 JSONArray  对象
                    if (json.size() > 0) {
                        for (int i = 0; i < json.size(); i++) {
                            JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            String appId = (String) job.get("appId");
                            String versionId = (String) job.get("versionId");
                            String type = (String) job.get("type");
                            App app = new App();
                            app.setiIdx(snowFlake.nextId());
                            app.setiAppid(Long.valueOf(appId));
                            app.setiVersionid(Long.valueOf(versionId));
                            app.setiType(Integer.parseInt(type));
                            app.setiSchoolid(Long.valueOf(schoolId));
                            app.settCtime(sdf.format(date));
                            app.settUtime(sdf.format(date));
                            app.setiStatus(1);
                            BigDecimal bigDecimal = new BigDecimal("1");
                            app.setnPrice(bigDecimal);
                            appMapper.insert(app);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ReturnJson.returnFail("更新学校应用关系错误！");
                }
                //配置信息
                if (isAddDefs > 0) {
                    //新增配置表  "全校默认应用"
                    try {
                        Appconfig appconfig1 = appconfigMapper.selectBySchoolId(Long.valueOf(schoolId));
                        Appconfig appconfig = new Appconfig();
                        if (appconfig1 != null || appconfig1.equals("")) {
                            appconfig.setiConfigid(appconfig1.getiConfigid());
                            appconfig.setiSchoolid(Long.valueOf(schoolId));
                            appconfig.setiUuserid(userId);
                            appconfig.settUtime(sdf.format(date));
                            appconfigMapper.update(appconfig);
                        }
                        appconfigApplistMapper.deleteBySchooId(Long.valueOf(schoolId));
                        //新增appList
                        String appLists = "[" + appList + "]";
                        System.out.println(appLists);
                        JSONArray json = JSONArray.fromObject(appLists);
                        for (int i = 0; i < json.size(); i++) {
                            JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            String appId = (String) job.get("appId");
                            AppconfigApplist appconfigApplist = new AppconfigApplist();
                            appconfigApplist.setiAppid(Long.valueOf(appId));
                            appconfigApplist.setiCuserid(userId);
                            appconfigApplist.settCtime(sdf.format(date));
                            appconfigApplist.setiIdx(snowFlake.nextId());
                            appconfigApplistMapper.insertSelective(appconfigApplist);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ReturnJson.returnFail("app列表有误！");
                    }
                }
                Map<String, Object> map = new HashMap<>();
                map.put("schoolId", schoolId);
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getSchoolList")
    public Object getSchoolList() {
        List<Map<String, Object>> list = schoolManage.getSchoolList();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolList", list);
        return ReturnJson.returnSuccessMap(map);
    }

    @RequestMapping("getSchoolListByAppId")
    public Object getSchoolListByAppId(String appId) {
        if (appId == null || appId.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> list = schoolManage.getSchoolListByAppId(Long.valueOf(appId));
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("addSerialNo")
    public Object addSerialNo(String schoolId, String serialNo, String sDate, String eDate, String total) {
        if (serialNo == null || serialNo.equals("") || schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        Padauthorize authorize = padauthorizeMapper.selectBySerialNo(serialNo);
        if (authorize != null) {
            LOGGER.info("授权码被占用！");
            return ReturnJson.returnFail("授权码被占用！");
        }
        Padauthorize padauthorize = new Padauthorize();
        SnowFlake snowFlake = new SnowFlake(2, 4);
        padauthorize.setiAuzid(snowFlake.nextId());
        padauthorize.setcSerialno(serialNo);
        padauthorize.setiSchoolid(Long.valueOf(schoolId));
        padauthorize.setiStatus(1);
        padauthorize.setiAType(2);
        if (sDate != null && !sDate.equals("")) {
            Date date = DateFromat.parse(sDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            padauthorize.setdSdate(date);
        }
        if (eDate != null && !eDate.equals("")) {
            Date date = DateFromat.parse(eDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            padauthorize.setdEdate(date);
            padauthorize.setiType(1);
        } else {
            padauthorize.setiType(0);
        }
        padauthorize.settCtime(new Date());
        padauthorize.settUtime(new Date());
        System.out.println("param==" + padauthorize);
        if (total != null && !total.equals("")) {
            padauthorize.setiTotal(Integer.parseInt(total));
        }
        int k = padauthorizeMapper.add(padauthorize);
        if (k > 0) {
            return ReturnJson.returnSuccessMap();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getSchoolListByVersionId")
    public Object getSchoolListByVersionId(String versionId) {
        if (versionId != null && !versionId.equals("")) {
            try {
                Map<String, Object> map = new HashMap<>();
                List<Map<String, Object>> list = schoolManage.getSchoolListByVersionId(Long.valueOf(versionId));
                map.put("list", list);
                return ReturnJson.returnSuccessMap(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ReturnJson.returnFail();
    }
}
