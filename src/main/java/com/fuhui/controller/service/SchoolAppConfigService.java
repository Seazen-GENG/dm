package com.fuhui.controller.service;

import com.fuhui.controller.manager.*;
import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.trj.AppRjMapper;
import com.fuhui.dao.txm.AppConfigconPadListMapper;
import com.fuhui.dao.txm.AppconfigApplistMapper;
import com.fuhui.dao.txm.AppconfigMapper;
import com.fuhui.dao.txu.SchoolMapper;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.txm.AppConfigconPadList;
import com.fuhui.entity.txm.Appconfig;
import com.fuhui.entity.txm.AppconfigApplist;
import com.fuhui.entity.txu.App;
import com.fuhui.entity.txu.School;
import com.fuhui.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("appConfigService")
public class SchoolAppConfigService {

    @Autowired
    private AppconfigMapper appconfigMapper;

    @Autowired
    private SchoolAppConfigManage schoolAppConfigManage;

    @Autowired
    private SchoolPadConfigManage padConfigManage;

    @Autowired
    private JPushManage jPushManage;

    @Autowired
    private AppconfigApplistMapper appconfigApplistMapper;

    @Autowired
    private PadMapper padMapper;

    @Autowired
    private AppRjMapper appRjMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private OauthManage oauthManage;

    @Autowired
    private AppConfigconPadListMapper appConfigconPadListMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(SchoolAppConfigService.class);

    /**
     * appConfigService/getNewInfoByPadMac?padMac=1&padConfigFlg=0
     *
     * @return
     */
    @RequestMapping("getNewInfoByPadMac")
    public Object getNewInfoByPadMac(String padMac, String padConfigFlg, String appConfigTime) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        Pad padAuthorize = padMapper.getAuthorizeByPadMac(padMac);
        System.out.println(padAuthorize);
        if (padAuthorize == null) {
            return ReturnJson.returnFail(101, "设备未授权");
        }
        Integer padConfigFlg1 = 0;
        if (padConfigFlg != null && !padConfigFlg.equals("")) {
            padConfigFlg1 = Integer.parseInt(padConfigFlg);
        }
        Map<String, Object> pad = schoolAppConfigManage.getNewInfoByPadMac(padMac, appConfigTime);
        if (pad == null || pad.isEmpty()) {
            return ReturnJson.returnFail();
        }
        // pad.put("token", token);
        if (padConfigFlg1 > 0 || padConfigFlg == null || padConfigFlg.equals("")) {
            Map padConfig = padConfigManage.getNewInfoByPadMac(padMac, null, -1);
            pad.put("padConfig", padConfig);
        }
        return ReturnJson.returnSuccessMap(pad);
    }

    /**
     * 实现接口aouth验证
     *
     * @param padMac
     * @param padConfigFlg
     * @param appConfigTime
     * @param request
     * @param access_token
     * @return
     */
    @RequestMapping("v1/getNewInfoByPadMac")
    public Object getNewInfoByPadMacs(String padMac, String padConfigFlg, String appConfigTime, HttpServletRequest request, String access_token) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        String token = "";
        try {
            if (access_token == null || access_token.equals("")) {
                token = request.getHeader("access-token");
                Enumeration e1 = request.getHeaderNames();
                while (e1.hasMoreElements()) {
                    String headerName = (String) e1.nextElement();
                    String headValue = request.getHeader(headerName);
                    System.out.println(headerName + "=" + headValue);
                    LOGGER.info(headerName + "=请求头参数=" + headValue);
                }
            } else {
                token = access_token;
            }
            System.out.println("token==" + token);
            if (token == null || token.equals("")) {
                return ReturnJson.returnFail(102, "没有携带有效令牌!");
            }
            Map<String, Object> map = oauthManage.checkToken(token);
            if (map == null || map.isEmpty()) {
                return ReturnJson.returnFail(102, "accessToken无效或已过期。");
            }
        } catch (Exception e) {
            return ReturnJson.returnFail(102, "accessToken无效或已过期。");
        }
        Pad pad1 = padMapper.getInfoByPadMac(padMac);
        LOGGER.info("查询是否存在mac");
        if (pad1 == null) {
            LOGGER.info("无mac，相应正常报错信息！");
            return ReturnJson.returnFail(201,"设备未注册!");
        }
        Pad padAuthorize = padMapper.getAuthorizeByPadMac(padMac);
        System.out.println(padAuthorize);
        if (padAuthorize == null) {
            return ReturnJson.returnFail(220, "设备未授权");
        }
        Integer padConfigFlg1 = 0;
        if (padConfigFlg != null && !padConfigFlg.equals("")) {
            padConfigFlg1 = Integer.parseInt(padConfigFlg);
        }
        Map<String, Object> pad = schoolAppConfigManage.getNewInfoByPadMac(padMac, appConfigTime);
        if (pad == null || pad.isEmpty()) {
            return ReturnJson.returnFail();
        }
        // pad.put("token", token);
        if (padConfigFlg1 > 0 || padConfigFlg == null || padConfigFlg.equals("")) {
            Map padConfig = padConfigManage.getNewInfoByPadMac(padMac, null, -1);
            pad.put("padConfig", padConfig);
        }
        return ReturnJson.returnSuccessMap(pad);
    }


    @RequestMapping("getConfigListBySchoolId")
    public Object getConfigListBySchoolId(String schoolId, String appIds, String name, String status, String pNum, String pLine,
                                          String isPage, String sCTime, String eCTime) {
        Map<String, Object> pageMap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        System.out.println("***" + schoolId);
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        List<Appconfig> list = schoolAppConfigManage.getList(schoolId, appIds, name, status, pNum, pLine, isPage, sCTime, eCTime);
        System.out.println("result==" + list.size());
        if (list == null || list.size() == 0) {
            return ReturnJson.returnFail();
        }
        if (isPage != null && isPage.equals("1")) {
            if (pNum == null || pLine == null) {
                return ReturnJson.returnFail();
            } else {
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine ", pLine);
                pageMap.put("countAll", list.size());
                data.put("page", pageMap);
            }
        }
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Appconfig app : list) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("configId", String.valueOf(app.getiConfigid()));
            if (app.getcName() != null) {
                newMap.put("name", app.getcName());
            } else {
                newMap.put("name", "");
            }
            newMap.put("schoolId", String.valueOf(app.getiSchoolid()));
            if (app.getiRange() != null) {
                newMap.put("range", app.getiRange());
            } else {
                newMap.put("range", "");
            }
            App app1 = app.getApp();
            newMap.put("appCount_sys", app1.getSysCount());
            newMap.put("appCount_ext", app1.getExtCount());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (app.gettCtime() != null) {
                Date date = DateFromat.parse(app.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                newMap.put("cTime", sdf.format(date));
            } else {
                newMap.put("cTime", "");
            }
            if (app.gettUtime() != null) {
                Date date1 = DateFromat.parse(app.gettUtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                newMap.put("uTime", sdf.format(date1));
            } else {
                newMap.put("uTime", "");
            }
            if (app.getiStatus() != null) {
                newMap.put("status", app.getiStatus());
            } else {
                newMap.put("status", "");
            }
            newList.add(newMap);
        }
        data.put("list", newList);
        return ReturnJson.returnSuccessMap(data);
    }

    @RequestMapping("getAllConfigList")
    public Object getAllConfigList(String schoolId) {
        Map<String, Object> data = new HashMap<>();
        List<Appconfig> list = null;
        if (schoolId == null || schoolId.equals("")) {
            list = schoolAppConfigManage.getList(null, null, null, null, null, null, null, null, null);
        } else {
            list = schoolAppConfigManage.getList(schoolId, null, null, null, null, null, null, null, null);
        }
        System.out.println("result==" + list.size());
        if (list == null || list.size() == 0) {
            return ReturnJson.returnFail();
        }
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Appconfig app : list) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("configId", String.valueOf(app.getiConfigid()));
            if (app.getcName() != null) {
                newMap.put("name", app.getcName());
            } else {
                newMap.put("name", "");
            }
            newMap.put("schoolId", String.valueOf(app.getiSchoolid()));
            //查询school_name
            School school = schoolMapper.selectByPrimaryKey(app.getiSchoolid());
            if (school.getcName() != null) {
                newMap.put("schoolName", school.getcName());
            } else {
                newMap.put("schoolName", "");
            }
            if (app.getiRange() != null) {
                newMap.put("range", app.getiRange());
            } else {
                newMap.put("range", "");
            }
            App app1 = app.getApp();
            newMap.put("appCount_sys", app1.getSysCount());
            newMap.put("appCount_ext", app1.getExtCount());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (app.gettCtime() != null) {
                Date date = DateFromat.parse(app.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                newMap.put("cTime", sdf.format(date));
            } else {
                newMap.put("cTime", "");
            }
            if (app.gettUtime() != null) {
                Date date1 = DateFromat.parse(app.gettUtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                newMap.put("uTime", sdf.format(date1));
            } else {
                newMap.put("uTime", "");
            }
            if (app.getiStatus() != null) {
                newMap.put("status", app.getiStatus());
            } else {
                newMap.put("status", "");
            }
            newList.add(newMap);
        }
        data.put("list", newList);
        return ReturnJson.returnSuccessMap(data);
    }

    @RequestMapping("setConfigStatus")
    public Object setConfigStatus(String configId, String status, String schoolIs) throws Exception {
        List list = new ArrayList();
        if (configId == null || status == null || configId.equals("") || status.equals("")) {
            return ReturnJson.returnFail();
        }
        long config = Long.valueOf(configId);
        Integer status1 = Integer.parseInt(status);
        Appconfig appconfig = new Appconfig();
        appconfig.setiConfigid(config);
        appconfig.setiStatus(status1);
        int result = schoolAppConfigManage.setStatus(appconfig);
        if (result > 0) {
            //调用sendMesg发送极光推送
            //jPushManage.sendMsg("11", "0", schoolIs, "all", "11", null);
            return ReturnJson.returnList(list);
        }
        return ReturnJson.returnFail();
    }


    /**
     * 保存配置信息，根据不同场景进行新增或更新操作
     */
    @RequestMapping("saveConfig")
    public Object schoolsaveConfig(String configId, String name, String range, String rangeIds, String remark,
                                   String status, String appList, String schoolId) throws Exception {
        System.out.println("**configId**" + configId);
        Map<String, Object> map = new HashMap<>();
        //long schoolId = 0;
        if (name == null || name.equals("") || range == null || range.equals("")) {
            return ReturnJson.returnFail("参数异常");
        }
        Integer status1 = 1;
        if (status != null) {
            status1 = Integer.parseInt(status);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SnowFlake snowFlake = new SnowFlake(2, 3);
        if (configId == null || configId.equals("") || configId.equals("null")) {
            if (schoolId == null || schoolId.equals("")) {
                return ReturnJson.returnFail("参数异常");
            }
            Appconfig appconfigs = new Appconfig();
            long configId1 = snowFlake.nextId();
            appconfigs.setiConfigid(configId1);
            appconfigs.setcName(name);
            appconfigs.setiRange(Integer.parseInt(range));
            if (!range.equals("0")) {
                if (rangeIds == null || rangeIds.equals("")) {
                    return ReturnJson.returnFail("当指定时，指定对象不能为空!");
                } else {
                    appconfigs.setcRangeids(rangeIds);
                    setRangeIds(configId1, rangeIds);
                }
            }
            appconfigs.setiSchoolid(Long.valueOf(schoolId));
            appconfigs.settCtime(sdf.format(new Date()));
            appconfigs.settUtime(sdf.format(new Date()));
            if (remark != null) {
                appconfigs.setcRemark(remark);
            }
            appconfigs.setiStatus(status1);
            //处理appList
            int k = appconfigMapper.add(appconfigs);
            if (k > 0) {
                if (appList != null) {
                    String[] sourceStrArray = appList.split(",");//分割出来的字符数组
                    for (int i = 0; i < sourceStrArray.length; i++) {
                        System.out.println(sourceStrArray[i]);
                        long I_IDX = snowFlake.nextId();
                        AppconfigApplist appconfigApplist = new AppconfigApplist();
                        appconfigApplist.setiIdx(I_IDX);
                        appconfigApplist.setiAppid(Long.valueOf(sourceStrArray[i]));
                        appconfigApplist.setiConfigid(configId1);
                        //userId定值1
                        appconfigApplist.setiCuserid(Long.valueOf("1"));
                        appconfigApplist.settCtime(sdf.format(new Date()));
                        appconfigApplistMapper.saveAppList(appconfigApplist);
                    }
                }
                map.put("configId", configId1);
                if (range.equals("0")) {
                    jPushManage.sendMsg("11", range, schoolId, "all", "11", "");
                } else {
                    jPushManage.sendMsg("11", range, rangeIds, "all", "11", "");
                }
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            long config = Long.valueOf(configId);
            Appconfig appconfig = new Appconfig();
            appconfig.setiConfigid(config);
            appconfig.setiStatus(status1);
            appconfig.setcName(name);
            appconfig.setiRange(Integer.parseInt(range));
            if (!range.equals("0")) {
                LOGGER.info("配置为指定时，更新t_xm_appconfig_padlist");
                if (rangeIds == null || rangeIds.equals("")) {
                    return ReturnJson.returnFail("当指定时，指定对象不能为空!");
                } else {
                    appconfig.setcRangeids(rangeIds);
                    setRangeIds(config, rangeIds);
                }
            }
            appconfig.settUtime(sdf.format(new Date()));
            if (remark != null) {
                appconfig.setcRemark(remark);
            }
            int result = appconfigMapper.update(appconfig);
            if (result > 0) {
                //调用sendMesg发送极光推送
                if (appList != null) {
                    appconfigApplistMapper.deleteByConfigId(config);//先删除原有的appList信息
                    String[] sourceStrArray = appList.split(",");//分割出来的字符数组
                    for (int i = 0; i < sourceStrArray.length; i++) {
                        System.out.println(sourceStrArray[i]);
                        long I_IDX = snowFlake.nextId();
                        AppconfigApplist appconfigApplist = new AppconfigApplist();
                        appconfigApplist.setiIdx(I_IDX);
                        appconfigApplist.setiAppid(Long.valueOf(sourceStrArray[i]));
                        appconfigApplist.setiConfigid(Long.valueOf(configId));
                        //userId定值1
                        appconfigApplist.setiCuserid(Long.valueOf("1"));
                        appconfigApplist.settCtime(sdf.format(new Date()));
                        appconfigApplistMapper.saveAppList(appconfigApplist);
                    }
                }else {
                    appconfigApplistMapper.deleteByConfigId(Long.valueOf(configId));
                }
                map.put("configId", configId);
                if (range.equals("0")) {
                    jPushManage.sendMsg("11", range, schoolId, "all", "11", "");
                } else {
                    jPushManage.sendMsg("11", range, rangeIds, "all", "11", "");
                }
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail();
    }

    /******
     * saveConfig中公用方法
     * *******/
    public void setRangeIds(Long configId, String rangeIds) {
        SnowFlake snowFlake = new SnowFlake(2, 3);
        try {
            //根据mac查询出对应的Mac地址，然后添加限制表t_xm_appconfig_padlist
            String[] sourceStrArray = rangeIds.split(",");//分割出来的字符数组
            for (int i = 0; i < sourceStrArray.length; i++) {
                Pad pad = padMapper.getInfoByPadMac(sourceStrArray[i]);
                AppConfigconPadList appConfigconPadList = new AppConfigconPadList();
                appConfigconPadList.setiIdx(snowFlake.nextId());
                appConfigconPadList.setiConfigid(configId);
                appConfigconPadList.settCtime(new Date());
                appConfigconPadList.setiPadid(pad.getiPadid());
                AppConfigconPadList appConfigconPadList1 = appConfigconPadListMapper.selectByPadId(pad.getiPadid());
                if (appConfigconPadList1 == null || appConfigconPadList1.equals("")) {
                    appConfigconPadListMapper.insertSelective(appConfigconPadList);
                } else {
                    LOGGER.info("t_xm_appconfig_padlist限制表中数据已存在，不进行操作!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("添加t_xm_appconfig_padlist限制表失败!");
        }
    }


    @RequestMapping("getConfigInfoById")
    public Object getConfigInfoById(String configId, String schoolId, String isShowRangeAll, String isShowAppAll) {
        if (configId == null || configId.equals("")) {
            return ReturnJson.returnFail("参数异常");
        }
        long sch = -1;
        if (schoolId != null) {
            sch = Long.valueOf(schoolId);
        }
        Integer isRange = 0;
        Integer isApp = 0;
        if (isShowRangeAll != null) {
            isRange = Integer.parseInt(isShowRangeAll);
        }
        if (isShowAppAll != null) {
            isApp = Integer.parseInt(isShowAppAll);
        }
        Map map = schoolAppConfigManage.getInfo(Long.valueOf(configId), sch, isRange, isApp);
        return ReturnJson.returnSuccessMap(map);
    }

    @RequestMapping("getAllAppList")
    public Object getAllAppList() {
        List<AppRj> appRjs = appRjMapper.selectAllapp();
        Map<String, Object> allMap = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (AppRj aj : appRjs
                ) {
            Map<String, Object> map = new HashMap<>();
            map.put("appId", String.valueOf(aj.getiAppid()));
            if (aj.getcName() != null) {
                map.put("appName", aj.getcName());
            } else {
                map.put("appName", "");
            }
            if (aj.getcPackage() != null) {
                map.put("pName", aj.getcPackage());
            } else {
                map.put("pName", "");
            }
            if (aj.getiVersionid() != null) {
                map.put("versionId", String.valueOf(aj.getiVersionid()));
            } else {
                map.put("versionId", "");
            }
            if (aj.getiType() != null) {
                map.put("type", String.valueOf(aj.getiType()));
            } else {
                map.put("type", "");
            }
            list.add(map);
        }
        allMap.put("allAppList", list);
        return ReturnJson.returnSuccessMap(allMap);
    }
}
