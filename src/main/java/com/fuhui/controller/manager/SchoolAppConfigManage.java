package com.fuhui.controller.manager;

import com.fuhui.dao.taz.PadauthorizeMapper;
import com.fuhui.dao.tpd.JPushMapper;
import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.tqx.TokenMapper;
import com.fuhui.dao.txm.AppconfigApplistMapper;
import com.fuhui.dao.txm.AppconfigMapper;
import com.fuhui.dao.txu.AppMapper;
import com.fuhui.dao.txu.PlatformMapper;
import com.fuhui.entity.taz.Padauthorize;
import com.fuhui.entity.tpd.JPush;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.tqx.Token;
import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.AppVersion;
import com.fuhui.entity.txm.Appconfig;
import com.fuhui.entity.txm.AppconfigApplist;
import com.fuhui.entity.txu.*;
import com.fuhui.entity.txu.App;
import com.fuhui.entity.txu.Class;
import com.fuhui.util.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class SchoolAppConfigManage {

    @Autowired
    private AppconfigMapper appconfigMapper;

    @Autowired
    private AppconfigApplistMapper appconfigApplistMapper;

    @Autowired
    private PadMapper padMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private PlatformMapper platformMapper;

    @Autowired
    private PadauthorizeMapper padauthorizes;

    /**
     * 添加配置
     *
     * @param request
     * @return
     */
    @RequestMapping("add")
    public Object add(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SnowFlake snowFlake = new SnowFlake(2, 3);
        Date date = new Date();
        String idDel = "0";
        long iConfigid = snowFlake.nextId();
        long I_IDX = snowFlake.nextId();
        long iSchoolid = 0;
        String name = ""; //request.getParameter("name");
        String schoolId = "1"; //request.getParameter("schoolId");
        String range = "0";//request.getParameter("range");
        Integer intRange = 0;
        String rangeIds = "";//request.getParameter("rangeIds");
        String remark = "";//request.getParameter("remark");
        String status = "0";//request.getParameter("status");
        Integer intStatus = 0;
        try {
            name = request.getParameter("name");
            schoolId = request.getParameter("schoolId");
            range = request.getParameter("range");
            rangeIds = request.getParameter("rangeIds");
            remark = request.getParameter("remark");
            status = request.getParameter("status");
        } catch (Exception e) {
            System.out.println("提交参数异常！");
        }
        System.out.println("**schoolId**" + schoolId);
        if (schoolId == null || schoolId.equals("")) {
            iSchoolid = 1;
        } else {
            iSchoolid = Long.valueOf(schoolId);
        }
        if (status == null || status.equals("")) {
            intStatus = 1;
        } else {
            intStatus = Integer.parseInt(range);
        }
        if (range == null || range.equals("")) {
            intRange = 0;
        } else {
            intRange = Integer.parseInt(range);
        }
        if (name == null || name.equals("")) {
            name = "admin";
        }
        Appconfig appconfig = new Appconfig();
        appconfig.setiConfigid(iConfigid);
        appconfig.setiSchoolid(iSchoolid);
        appconfig.setcName(name);
        appconfig.setcRangeids(rangeIds);
        appconfig.setcRemark(remark);
        appconfig.settCtime(DateFromat.datetimeChange(date));
        appconfig.settUtime(DateFromat.datetimeChange(date));
        appconfig.setiCuserid(iConfigid);//操作人id登录后取值（创建人）
        appconfig.setiUuserid(iConfigid);//操作人id登录后取值（更新人）
        appconfig.setiRange(intRange);
        appconfig.setiStatus(intStatus);

        int result = appconfigMapper.add(appconfig);
        if (result > 0) {
            map.put("iConfigid", iConfigid);
            AppconfigApplist txmApplist = new AppconfigApplist();
            txmApplist.setiConfigid(iConfigid);
            txmApplist.setiIdx(I_IDX);
            txmApplist.setiAppid(iConfigid);
            txmApplist.setiCuserid(iConfigid);
            Date txmdate = new Date();
            txmApplist.settCtime(DateFromat.datetimeChange(txmdate));
            int jg = saveAppList(idDel, txmApplist);
            if (jg > 0) {
                return iConfigid;
            }
        }
        return 0;
    }


    /**
     * 更新配置
     *
     * @param request
     * @return
     */
    @RequestMapping("update")
    public Object update(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SnowFlake snowFlake = new SnowFlake(2, 3);
        Date date = new Date();
        String idDel = "0";
        long iConfigid = Long.valueOf("222763776917909504");
        long I_IDX = Long.valueOf("222764811182944257");
        long iSchoolid = 0;
        Integer finalResut = 0;
        String name = ""; //request.getParameter("name");
        String schoolId = "1"; //request.getParameter("schoolId");
        String range = "0";//request.getParameter("range");
        Integer intRange = 0;
        String rangeIds = "";//request.getParameter("rangeIds");
        String remark = "";//request.getParameter("remark");
        String status = "0";//request.getParameter("status");
        Integer intStatus = 0;
        try {
            name = request.getParameter("name");
            schoolId = request.getParameter("schoolId");
            range = request.getParameter("range");
            rangeIds = request.getParameter("rangeIds");
            remark = request.getParameter("remark");
            status = request.getParameter("status");
        } catch (Exception e) {
            System.out.println("提交参数异常！");
        }
        System.out.println("**schoolId**" + schoolId);
        if (schoolId == null || schoolId.equals("")) {
            iSchoolid = 1;
        } else {
            iSchoolid = Long.valueOf(schoolId);
        }
        if (status == null || status.equals("")) {
            intStatus = 1;
        } else {
            intStatus = Integer.parseInt(range);
        }
        if (range == null || range.equals("")) {
            intRange = 1;
        } else {
            intRange = Integer.parseInt(range);
        }
        if (name == null || name.equals("")) {
            name = "user";
        }
        Appconfig appconfig = new Appconfig();
        appconfig.setiConfigid(iConfigid);
        appconfig.setiSchoolid(iSchoolid);
        appconfig.setcName(name);
        appconfig.setcRangeids(rangeIds);
        appconfig.setcRemark(remark);
        appconfig.settUtime(DateFromat.datetimeChange(date));
        appconfig.setiUuserid(iConfigid);//操作人id登录后取值（更新人）
        appconfig.setiRange(intRange);
        appconfig.setiStatus(intStatus);
        int result = appconfigMapper.update(appconfig);
        if (result > 0) {
            map.put("iConfigid", iConfigid);
            AppconfigApplist txmApplist = new AppconfigApplist();
            txmApplist.setiConfigid(iConfigid);
            txmApplist.setiIdx(I_IDX);
            txmApplist.setiAppid(iConfigid);
            txmApplist.setiCuserid(iConfigid);
            Date txmdate = new Date();
            txmApplist.settCtime(DateFromat.datetimeChange(txmdate));
            int jg = updateAppList(txmApplist);
            if (jg > 0) {
                finalResut = 1;
            }
        }
        System.out.println("**finalResut**" + finalResut);
        return ReturnJson.returnSuccessMap(map);
    }


    /**
     * 表TxmAppconfigApplist中添加数据
     *
     * @param isDel
     * @param appconfigApplist
     * @return
     */
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
     * 更新副表txmAppconfigApplist
     *
     * @param appconfigApplist
     * @return
     */
    private int updateAppList(AppconfigApplist appconfigApplist) {
        int k = appconfigApplistMapper.updateByPrimaryKey(appconfigApplist);
        if (k > 0) {
            return 1;
        } else {
            return 0;
        }
    }


    public Map<String, Object> getNewInfoByPadMac(String padMac, String appConfigTime) {
        Map<String, Object> allMap = new HashMap<String, Object>();
        System.out.println("param==" + padMac);
        Pad padAuthorize = padMapper.getAuthorizeByPadMac(padMac);
        System.out.println("授权信息****" + padAuthorize);
        try {
            List<Platform> p1 = platformMapper.selectBySchoolIdAndType(padAuthorize.getiSchoolid(), null);
            System.out.println("Platform**" + p1);
            if (p1 != null && !p1.isEmpty()) {
                for (Platform p : p1) {
                    if (p.getiType() == 1) {
                        allMap.put("eduPath", p.getcPath());
                    } else if (p.getiType() == 2) {
                        allMap.put("dmPath", p.getcPath());
                    } else if (p.getiType() == 3) {
                        allMap.put("mqPath", p.getcPath());
                    }
                }
            } else {
                allMap.put("eduPath", "");
                allMap.put("dmPath", "");
                allMap.put("mqPath", "");
            }
        } catch (Exception e) {
            System.out.println("脏数据!");
        }
        Padauthorize authorize = padAuthorize.getPadauthorize();//得到授权表，为了取出授权过期时间
        List<Pad> pad = padMapper.getNewInfoByPadMac(padMac);
        if (pad == null) {
            return allMap;
        }
//        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        long padId = -1;///存放于token值里
        Map<String, Object> appMap = new HashMap<String, Object>();
        for (Pad padFor : pad) {
            padId = padFor.getiPadid();
            System.out.println("padId==" + padId);
            map.put("padId", String.valueOf(padId));
            if (padFor.getcName() != null) {
                map.put("padName", padFor.getcName());
            } else {
                map.put("padName", "");
            }
            /*****************************/
            long schoolId = -1;
            if (padFor.getiSchoolid() != null) {
                schoolId = padFor.getiSchoolid();
                map.put("schoolId", String.valueOf(schoolId));
            } else {
                map.put("schoolId", "");
            }
            if (padFor.getsName() != null) {
                map.put("schoolName", padFor.getsName());
            } else {
                map.put("schoolName", "");
            }
            if (padFor.getiGradeid() != null) {
                map.put("gradeId", String.valueOf(padFor.getiGradeid()));
            } else {
                map.put("gradeId", "");
            }
            if (padFor.getgName() != null) {
                map.put("gradeName", padFor.getgName());
            } else {
                map.put("gradeName", "");
            }
            if (padFor.getiUserid() != null) {
                map.put("userId", String.valueOf(padFor.getiUserid()));
            } else {
                map.put("userId", "");
            }
            if (padFor.getiClassid() != null) {
                map.put("classId", String.valueOf(padFor.getiClassid()));
            } else {
                map.put("classId", "");
            }
            if (padFor.getClName() != null) {
                map.put("className", padFor.getClName());
            } else {
                map.put("className", "");
            }
            map.put("lastTime", "");
            //上次开机时间
            /*****************************/
            if (padFor.getiAuzid() != null) {
                map.put("auzId", String.valueOf(padFor.getiAuzid()));
            } else {
                map.put("auzId", "");
            }
            JPush jPush = padFor.getjPush();
            if (jPush != null) {
                map.put("registrationId", String.valueOf(jPush.getcRegistrationid()));
                map.put("alias", jPush.getcAlias());
                map.put("tags", jPush.getcTags());
            } else {
                map.put("registrationId", "");
                map.put("alias", "");
                map.put("tags", "");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (authorize.getdEdate() == null) {
                map.put("auzEDate", "");
            } else {
                map.put("auzEDate", sdf.format(authorize.getdEdate()));
            }
            System.out.println("auzDType == " + authorize);
            Padauthorize authorizePad = padauthorizes.selectByPrimaryKey(padFor.getiAuzid());
            if (authorizePad.getiType() != null) {
                map.put("auzDType", authorizePad.getiType());
            } else {
                map.put("auzDType", "");
            }
            Appconfig appconfig = padFor.getAppconfig();
            System.out.println("appconfig==" + appconfig);
            Appconfig aconfig = new Appconfig();
            System.out.println("schoolId==" + schoolId);
            //appConfigTime是否要加条件
            if (appconfig.getiConfigid() == 0) {
                System.out.println("start==" + 1);
                aconfig = appconfigMapper.getInfoList(schoolId, -1, appConfigTime);
            } else {
                System.out.println("start==" + 1.1);
                aconfig = appconfigMapper.getInfoList(schoolId, appconfig.getiConfigid(), appConfigTime);
            }
            System.out.println("aconfig==" + aconfig);
            if (aconfig != null) {
                if (aconfig.gettUtime() != null) {
                    Date date = DateFromat.parse(aconfig.gettUtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    appMap.put("uTime", sdf.format(date));
                } else {
                    appMap.put("uTime", "");
                }
                if (aconfig.getcName() != null) {
                    appMap.put("name", aconfig.getcName());
                } else {
                    appMap.put("name", "");
                }
                appMap.put("configId", String.valueOf(aconfig.getiConfigid()));
                List<AppconfigApplist> app = aconfig.getApplist();
                for (AppconfigApplist applist : app) {
                    long appConfigId = -1;
                    if (applist.getiConfigid() != null) {
                        appConfigId = applist.getiConfigid();
                    }
                    System.out.println("start==" + 2);
                    List<App> appList = appMapper.getNewConfigInfo(appConfigId, schoolId);
                    System.out.println("appList==" + appList);
                    List editList = new ArrayList();
                    for (App a : appList) {
                        System.out.println("App==" + a);
                        Map<String, Object> appListMap = new HashMap<String, Object>();
                        AppRj appRj = a.getAppRj();
                        appListMap.put("appId", String.valueOf(appRj.getiAppid()));
                        if (a.getiVersionid() != null) {
                            appListMap.put("versionId", String.valueOf(a.getiVersionid()));
                        } else {
                            appListMap.put("versionId", "");
                        }
                        if (appRj.getcPackage() != null) {
                            appListMap.put("pName", appRj.getcPackage());
                        } else {
                            appListMap.put("pName", "");
                        }
                        if (appRj.getcDesc() != null) {
                            appListMap.put("desc", appRj.getcDesc());
                        } else {
                            appListMap.put("desc", "");
                        }
                        if (appRj.getcName() != null) {
                            appListMap.put("name", appRj.getcName());
                        } else {
                            appListMap.put("name", "");
                        }
                        if (appRj.getcImgpath() != null) {
                            appListMap.put("imgPath", appRj.getcImgpath());
                        } else {
                            appListMap.put("imgPath", "");
                        }
                        if (a.getcDownurl() != null) {
                            appListMap.put("downUrl", a.getcDownurl());
                        } else {
                            appListMap.put("downUrl", "");
                        }
                        if (a.getiType() != null) {
                            appListMap.put("type", a.getiType());
                        } else {
                            appListMap.put("type", "");
                        }
                        if (a.getiUway() != null) {
                            appListMap.put("updateWay", a.getiUway());
                        } else {
                            appListMap.put("updateWay", "");
                        }
                        AppVersion appVersion = a.getAppVersion();
                        if (appVersion.getcVersion() != null) {
                            appListMap.put("curVersion", appVersion.getcVersion());
                        } else {
                            appListMap.put("curVersion", "");
                        }
                        if (appVersion.getcVersionCode() != null) {
                            appListMap.put("curVersionCode", appVersion.getcVersionCode());
                        } else {
                            appListMap.put("curVersionCode", "");
                        }
                        editList.add(appListMap);
                    }
                    appMap.put("count", editList.size());
                    appMap.put("appList", editList);
                }
            } else {
                List list = new ArrayList();
                allMap.put("appConfig", list);
            }
//            mapList.add(map);
        }
        allMap.put("appConfig", appMap);
        allMap.put("padInfo", map);
        //allMap.put("token", token);
        return allMap;
    }

    public List<Appconfig> getList(String iSchoolid, String appIds, String name, String iStatus, String pNum, String pLine,
                                   String isPage, String sTime, String eTime) {
        List<Appconfig> list = new ArrayList<>();
        Integer isPage1 = 0;
        Integer iStatis1 = 0;
        if (iStatus != null) {
            iStatis1 = Integer.parseInt(iStatus);
        }
        if (isPage != null && isPage.equals("1")) {
            isPage1 = Integer.parseInt(isPage);
            Integer pNum1 = 0;
            Integer pLine1 = 0;
            if (pNum != null && pLine != null) {
                pNum1 = Integer.parseInt(pNum);
                pLine1 = Integer.parseInt(pLine);
                list = appconfigMapper.getList(iSchoolid, appIds, name, iStatis1, pNum1 - 1, pLine1, isPage1, sTime, eTime);
            } else {
                return list;
            }
        } else {
            list = appconfigMapper.getList(iSchoolid, appIds, name, iStatis1, 0, 0, isPage1, sTime, eTime);
        }

        return list;
    }

    public int setStatus(Appconfig record) {
        if (record != null) {
            int result = appconfigMapper.setStatus(record);
            if (result > 0) {
                return 1;
            }
        }
        return 0;
    }

    public Map getInfo(long configId, long schoolId, Integer isShowRangeAll, Integer isShowAppAll) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> resultMap = new HashMap<>();
        Appconfig appconfig = appconfigMapper.selectByPrimaryKey(configId);
        if (appconfig == null) {
            return resultMap;
        }
        resultMap.put("configId", String.valueOf(appconfig.getiConfigid()));
        if (appconfig.getcName() != null) {
            resultMap.put("name", appconfig.getcName());
        } else {
            resultMap.put("name", "");
        }
        if (appconfig.getiRange() != null) {
            resultMap.put("range", appconfig.getiRange());
        } else {
            resultMap.put("range", "");
        }
        if (appconfig.getcRangeids() != null) {
            resultMap.put("rangeIds", appconfig.getcRangeids());
        } else {
            resultMap.put("rangeIds", "");
        }
        if (appconfig.getcRemark() != null) {
            resultMap.put("remark", appconfig.getcRemark());
        } else {
            resultMap.put("remark", "");
        }
        if (appconfig.getiStatus() != null) {
            resultMap.put("status", appconfig.getiStatus());
        } else {
            resultMap.put("status", "");
        }
        List<AppconfigApplist> applists = appconfigApplistMapper.getInfo(configId, schoolId, isShowAppAll);
        System.out.println("applists**" + applists);
        List<Map<String, Object>> appList = new ArrayList<Map<String, Object>>();
        for (AppconfigApplist ap : applists) {
            Map<String, Object> map = new HashMap<>();
            if (ap.getiIdx() != null) {
                map.put("idx", String.valueOf(ap.getiIdx()));
            } else {
                map.put("idx", "");
            }
            AppRj appRj = ap.getAppRj();
            if (appRj.getiAppid() != null) {
                map.put("appId", String.valueOf(appRj.getiAppid()));
            } else {
                map.put("appId", "");
            }
            try {
                if (ap.gettCtime() != null) {
                    Date date = DateFromat.parse(ap.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    map.put("addTime", sdf.format(date));
                } else {
                    map.put("addTime", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                map.put("addTime", "");
            }
            if (appRj.getcName() != null) {
                map.put("appName", appRj.getcName());
            } else {
                map.put("appName", "");
            }
            if (appRj.getcPackage() != null) {
                map.put("pName", appRj.getcPackage());
            } else {
                map.put("pName", "");
            }
            App app = ap.getApp();
            if (app.getiType() != null) {
                map.put("type", app.getiType());
            } else {
                map.put("type", "");
            }
            AppVersion appVersion = ap.getAppVersion();
            if (appVersion.getcVersion() != null) {
                map.put("version", appVersion.getcVersion());
            } else {
                map.put("version", "");
            }
            if (app.getcDownurl() != null) {
                map.put("downUrl", app.getcDownurl());
            } else {
                map.put("downUrl", "");
            }
            if (app.getiUway() != null) {
                map.put("updateWay", app.getiUway());
            } else {
                map.put("updateWay", "");
            }
            if (appRj.getcDesc() != null) {
                map.put("desc", appRj.getcDesc());
            } else {
                map.put("desc", "");
            }
            appList.add(map);
        }
        resultMap.put("appList", appList);
        return resultMap;
    }


}
