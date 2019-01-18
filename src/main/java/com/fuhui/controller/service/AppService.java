package com.fuhui.controller.service;

import com.fuhui.controller.manager.AppManage;
import com.fuhui.controller.manager.JPushManage;
import com.fuhui.controller.manager.OauthManage;
import com.fuhui.controller.manager.SchoolManage;
import com.fuhui.dao.trj.AppInstallMapper;
import com.fuhui.entity.trj.AppInstall;
import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.AppVersion;
import com.fuhui.entity.txu.App;
import com.fuhui.util.DateFromat;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("appService")
public class AppService {

    @Autowired
    private AppManage appManage;

    @Autowired
    private SchoolManage schoolManage;

    @Autowired
    private JPushManage jPushManage;

    @Autowired
    private OauthManage oauthManage;

    @Autowired
    private AppInstallMapper appInstallMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(AppService.class);

    /**
     * 保存应用版本（数据库中没有-新增，已有-更新）
     *
     * @param appId
     * @param versionId
     * @param version
     * @param versionCode
     * @param isCompel
     * @param vRemark
     * @param downUrl
     * @param releaseTime
     * @param isReview
     * @param remark
     * @param status
     * @return
     */
    @RequestMapping("saveAppVersion")
    public Object saveAppVersion(String appId, String versionId, String version, String versionCode, String isCompel, String vRemark
            , String downUrl, String releaseTime, String isReview, String remark, String status) {
        if (appId == null || appId.equals("") || version == null || version.equals("") || versionCode == null || versionCode.equals("")) {
            return ReturnJson.returnFail("参数异常！");
        }
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long userId = 1;////增加页面后从session中获取
        Long versionIdNew = snowFlake.nextId();
        AppVersion appVersion = new AppVersion();
        ///必填字段
        appVersion.setiAppid(Long.valueOf(appId));
        appVersion.setcVersion(version);
        appVersion.setcVersionCode(versionCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (versionId == null || version.equals("")) {
            appVersion.setiVersionid(versionIdNew);
        } else {
            appVersion.setiVersionid(Long.valueOf(versionId));
        }
        if (releaseTime == null || releaseTime.equals("")) {
            appVersion.settReleasetime(sdf.format(new Date()));
        } else {
            appVersion.settReleasetime(sdf.format(releaseTime));
        }
        if (isCompel == null || isCompel.equals("")) {
            appVersion.setiIscompel(1);
        } else {
            appVersion.setiIscompel(Integer.parseInt(isCompel));
        }
        if (status == null || status.equals("")) {
            appVersion.setiStatus(2);
        } else {
            appVersion.setiStatus(Integer.parseInt(status));
        }
        if (vRemark == null || vRemark.equals("")) {
            appVersion.setcVersionremark(sdf.format(new Date()) + "发布！");
        } else {
            appVersion.setcVersionremark(vRemark);
        }
        if (isReview == null || isReview.equals("")) {
            appVersion.setIsReview(0);
        } else {
            appVersion.setIsReview(Integer.parseInt(isReview));
        }
        if (downUrl != null && !downUrl.equals("")) {
            appVersion.setcDownurl(downUrl);
        }
        if (remark != null && !remark.equals("")) {
            appVersion.setcRemark(remark);
        }
        try {
            appVersion.settCtime(sdf.format(new Date()));
            appVersion.settRtime(sdf.format(new Date()));
            appVersion.setiCuserid(userId);
            int k = appManage.addAppVersion(appVersion);
            if (k > 0) {
                //添加版本信息成功后继续执行
                if (appVersion.getiStatus() == 1) {
                    int jg = appManage.updateVersion(Long.valueOf(appId), versionIdNew, userId);
                    if (jg > 0) {
                        LOGGER.info("添加appVersion+更新app***success");
                        List<App> apps = appManage.getDifferentSchoolByVersionId(Long.valueOf(appId), versionIdNew);
                        LOGGER.info("需要更新的schoolID集合==" + apps);
                        for (App app : apps) {
                            long schoolId = app.getiSchoolid();
                            int result = schoolManage.setAppVersionBySchoolId(schoolId, Long.valueOf(appId), versionIdNew, userId);
                            //W_JPushManage. sendMsg()发送激光推送
                            if (result > 0) {
                                LOGGER.info("更新txuApp完成,准备发送激光!");
                                jPushManage.sendMsg("11", "0", String.valueOf(schoolId), "all", "11", "");
                            }
                        }
                        LOGGER.info("更新应用版本完成!");
                        return ReturnJson.returnSuccessMap();
                    }
                } else {
                    LOGGER.info("添加应用版本完成!");
                    return ReturnJson.returnSuccessMap();
                }
            }
        } catch (Exception e) {
            System.out.println("添加数据异常!");
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /**
     * 条件查询应用列表
     *
     * @param appIds
     * @param name
     * @param packageName
     * @param type
     * @param place
     * @param status
     * @param productId
     * @param schoolId
     * @param sort
     * @param sCTime
     * @param eCTime
     * @param isPage
     * @param pNum
     * @param pLine
     * @return
     */
    @RequestMapping("getAppList")
    public Object getAppList(String appIds, String name, String packageName, String type, String place, String status,
                             String productId, String schoolId, String sort, String sCTime, String eCTime, String isPage, String pNum, String pLine) {
        LOGGER.info("NO.start");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> page = new HashMap<>();
        Long appId = null;
        if (appIds != null && !appIds.equals("")) {
            appId = Long.valueOf(appIds);
        }
        Integer types = null;
        if (type != null && !type.equals("")) {
            types = Integer.parseInt(type);
        }
        Integer places = null;
        if (place != null && !place.equals("")) {
            places = Integer.parseInt(place);
        }
        Integer statuss = null;
        if (status != null && !status.equals("")) {
            statuss = Integer.parseInt(status);
        }
        Integer sorts = null;
        if (sort != null && !sort.equals("")) {
            sorts = Integer.parseInt(sort);
        }
        Long productIds = null;
        if (productId != null && !productId.equals("")) {
            productIds = Long.valueOf(productId);
        }
        Long schoolIds = null;
        if (schoolId != null && !schoolId.equals("")) {
            schoolIds = Long.valueOf(schoolId);
        }
        List list = null;
        //////////////////////需要增加判断schoolId productId有无的关系///////////////////////////////
        try {
            if (isPage == null || isPage.equals("") || isPage.equals("")) {
                LOGGER.info("NO.1");
                list = appManage.getList(appId, name, packageName, types, places, statuss, productIds, schoolIds, sorts, sCTime, eCTime, null, null, null);
                // LOGGER.info("query==" + list);
            } else if (isPage.equals("1")) {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("当需要分页时，页码和每页条数不能为空！");
                }
                list = appManage.getList(appId, name, packageName, types, places, statuss, productIds, schoolIds, sorts, sCTime, eCTime, Integer.parseInt(isPage), Integer.parseInt(pNum) - 1, Integer.parseInt(pLine));
                page.put("pageNum", pNum);
                page.put("pageLine ", pLine);
                List list2 = appManage.getList(appId, name, packageName, types, places, statuss, productIds, schoolIds, sorts, sCTime, eCTime, null, null, null);
                page.put("countAll", list2.size());
                result.put("page", page);
            }
        } catch (Exception e) {
            LOGGER.info("queryException");
            e.printStackTrace();
        }
        result.put("list", list);
        return ReturnJson.returnSuccessMap(result);
    }


    /**
     * 保存应用版本（数据库中没有-新增，已有-更新）如果版本不为空，则同时增加版本
     *
     * @param appId
     * @param name
     * @param code
     * @param productId
     * @param imgPath
     * @param place
     * @param placeRemark
     * @param type
     * @param isShare
     * @param sort
     * @param schoolId
     * @param isFree
     * @param price
     * @param desc
     * @param shortDesc
     * @param remark
     * @param status
     * @param appVersion
     * @param userType
     * @return
     */
    @RequestMapping("saveApp")
    public Object saveApp(String appId, String name, String code, String productId, String imgPath, String place, String placeRemark, String type
            , String isShare, String sort, String schoolId, String isFree, String price, String desc, String shortDesc, String remark, String status, String appVersion, String userType) {
        if (name == null || name.equals("") || code == null || code.equals("") || place == null || place.equals("") || type == null || type.equals("") || isShare == null || isShare.equals("") ||
                sort == null || sort.equals("")) {
            return ReturnJson.returnFail("参数异常！请检查数据重试！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AppRj appRj = new AppRj();
        long suppParm = 1;
        {
            appRj.setcName(name);
            appRj.setcPackage(code);
            appRj.setiPlace(Integer.parseInt(place));
            appRj.setiType(Integer.parseInt(type));
            appRj.setiIsshare(Integer.parseInt(isShare));
            appRj.setiSort(Integer.parseInt(sort));
            appRj.settUtime(new Date());
            if (productId == null || productId.equals("")) {
                appRj.setiProductid(suppParm);
            } else {
                appRj.setiProductid(Long.valueOf(productId));
            }
            if (isFree == null || isFree.equals("")) {
                appRj.setiIsfree(0);
                BigDecimal bDouble = new BigDecimal(0);
                appRj.setnPrice(bDouble);
            } else {
                appRj.setiIsfree(Integer.parseInt(isFree));
                Integer trueFree = Integer.parseInt(isFree);
                LOGGER.info("setiIsfree==" + trueFree);
                if (trueFree < 1) {
                    LOGGER.info("trueFree==" + trueFree);
                    if (price == null || price.equals("") || price.equals("0.00")) {
                        return ReturnJson.returnFail("选择收费时，价格不能为空!");
                    } else {
                        BigDecimal bDoubles = new BigDecimal(price);
                        LOGGER.info("setnPrice==" + bDoubles);
                        appRj.setnPrice(bDoubles);
                    }
                } else {
                    appRj.setiIsfree(0);
                    BigDecimal bDouble = new BigDecimal(0);
                    appRj.setnPrice(bDouble);
                }
            }
            if (status == null || status.equals("")) {
                appRj.setiStatus(1);
            } else {
                appRj.setiStatus(Integer.parseInt(status));
            }
            if (imgPath != null && !imgPath.equals("")) {
                appRj.setcImgpath(imgPath);
            }
            if (placeRemark != null && !placeRemark.equals("")) {
                appRj.setcPlaceremark(placeRemark);
            }
            if (schoolId != null && !schoolId.equals("")) {
                appRj.setiSchoolid(Long.valueOf(schoolId));
            }
            if (desc != null && !desc.equals("")) {
                appRj.setcDesc(desc);
            }
            if (shortDesc != null && !shortDesc.equals("")) {
                appRj.setcShortdesc(shortDesc);
            }
            if (remark != null && !remark.equals("")) {
                appRj.setcRemark(remark);
            }
            if (userType != null && !userType.equals("")) {
                appRj.setiUserType(Integer.parseInt(userType));
            }
        }
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long appIds = snowFlake.nextId();
        long versionId = snowFlake.nextId();
        Map<String, Object> successMap = new HashMap<>();
        try {
            if (appId == null || appId.equals("")) {
                LOGGER.info("新增");
                appRj.setiAppid(appIds);
                appRj.settCtime(new Date());
                int k = appManage.add(appRj);
                LOGGER.info("addResult==" + k);
                if (k > 0) {
                    Object returnObject = addVersion(appVersion, versionId, appRj, successMap, appIds);
                    LOGGER.info("log==" + returnObject);
                    return returnObject;
                }
            } else {
                LOGGER.info("更新");
                appRj.setiAppid(Long.valueOf(appId));
                appRj.setiUuserid(suppParm);
                appRj.settUtime(new Date());
                int k = appManage.update(appRj);
                if (k > 0) {
                    Object returnObject = addVersion(appVersion, versionId, appRj, successMap, Long.valueOf(appId));
                    return returnObject;
                }
            }
        } catch (Exception e) {
            LOGGER.info("添加应用fail！");
            //  appManage.deletAppVersion(versionId);
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    ///////////////saveApp接口中公共部分
    public Object addVersion(String appVersion, Long versionId, AppRj appRj, Map<String, Object> successMap, Long appIds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (appVersion != null && !appVersion.equals("")) {
            try {
                JSONObject jsonObject = JSONObject.fromObject(appVersion);
                LOGGER.info("jsonArray==" + jsonObject);
                String version = jsonObject.getString("version");
                String versionCode = jsonObject.getString("versionCode");
                String versionRemark = jsonObject.getString("versionRemark");
                String isCompel = jsonObject.getString("isCompel");
                String downUrl = jsonObject.getString("downUrl");
                String releaseTime = jsonObject.getString("releaseTime");
                String remarks = jsonObject.getString("remark");
                LOGGER.info("添加新的应用版本");
                AppVersion appVersion1 = new AppVersion();
                appVersion1.setiAppid(appIds);
                appVersion1.setiVersionid(versionId);
                appVersion1.setiStatus(2);
                if (version != null && !version.equals("")) {
                    appVersion1.setcVersion(version);
                } else {
                    return ReturnJson.returnFail("版本号不能为空!");
                }
                if (versionCode != null && !versionCode.equals("")) {
                    appVersion1.setcVersionCode(versionCode);
                } else {
                    return ReturnJson.returnFail("版本编号不能为空!");
                }
                if (versionRemark != null) {
                    appVersion1.setcVersionremark(versionRemark);
                }
                if (isCompel != null) {
                    appVersion1.setiIscompel(Integer.parseInt(isCompel));
                }
                if (downUrl != null && !downUrl.equals("")) {
                    appVersion1.setcDownurl(downUrl);
                } else {
                    return ReturnJson.returnFail("下载地址不能为空!");
                }
                if (releaseTime != null && releaseTime.equals("''")) {
                    appVersion1.settReleasetime(releaseTime);
                }
                if (remarks != null) {
                    appVersion1.setcRemark(remarks);
                }
                {
                    appVersion1.settCtime(sdf.format(new Date()));
                    appVersion1.settRtime(sdf.format(new Date()));
                    appVersion1.setIsReview(1);
                }
                int jg = appManage.addAppVersion(appVersion1);
                if (jg > 0) {
                    appRj.setiVersionid(versionId);
                    int fina = appManage.update(appRj);
                    if (fina > 0) {
                        successMap.put("appId", appIds);
                        return ReturnJson.returnSuccessMap();
                    }
                }
            } catch (Exception e) {
                LOGGER.info("应用版本操作异常!");
                appManage.deletApp(appIds);
                e.printStackTrace();
                return ReturnJson.returnFail("应用版本操作异常!");
            }
        } else {
            LOGGER.info("应用版本为空不操作!");
            successMap.put("appId", appIds);
            return ReturnJson.returnSuccessMap();
        }
        return ReturnJson.returnFail();
    }

    /**
     * 根据应用id和版本id获取应用版本列表
     *
     * @param appId
     * @param versionId
     * @return
     */
    @RequestMapping("getAppVersion")
    public Object getAppVersion(String appId, String versionId) {
        if (appId == null || appId.equals("") || versionId == null || versionId.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        AppVersion appVersion = appManage.getAppVersion(Long.valueOf(appId), null, versionId, "0");
        Map<String, Object> current = new HashMap<String, Object>();
        if (appVersion != null) {
            current.put("appId", appId);
            current.put("versionId", String.valueOf(appVersion.getiVersionid()));
            current.put("version", appVersion.getcVersion());
            current.put("versionCode", appVersion.getcVersionCode());
            if (appVersion.getcVersionremark() != null) {
                current.put("versionRemark", appVersion.getcVersionremark());
            }
            if (appVersion.getcDownurl() != null) {
                current.put("downUrl", appVersion.getcDownurl());
            }
            current.put("isCompel", appVersion.getiIscompel());
            return ReturnJson.returnSuccessMap(current);
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getNewAppVersion")
    public Object getNewAppVersion(String appId, String schoolId, String versionId, String versionCode) {
        if (appId == null || appId.equals("") || versionCode == null || versionCode.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("appId", appId);
        if (schoolId != null && !schoolId.equals("")) {
            resultMap.put("schoolId", schoolId);
        }
        AppVersion appVersion = new AppVersion();
        Map<String, Object> current = new HashMap<String, Object>();
        Map<String, Object> newApp = new HashMap<String, Object>();
        appVersion = appManage.getAppVersion(Long.valueOf(appId), schoolId, versionCode, "1");
        try {
            if (appVersion == null) {
                resultMap.put("current", current);
                AppVersion newsApp1 = appManage.getAppNewVersionMax(Long.valueOf(appId), schoolId, versionCode);
                if (newsApp1 != null) {
                    newApp.put("versionId", String.valueOf(newsApp1.getiVersionid()));
                    newApp.put("version", newsApp1.getcVersion());
                    newApp.put("versionCode", newsApp1.getcVersionCode());
                    if (newsApp1.getcVersionremark() != null) {
                        newApp.put("versionRemark", newsApp1.getcVersionremark());
                    }
                    if (newsApp1.getcDownurl() != null) {
                        newApp.put("downUrl", newsApp1.getcDownurl());
                    }
                    newApp.put("isCompel", newsApp1.getiIscompel());
                }
                resultMap.put("newApp", newApp);
                resultMap.put("isNew", 1);
                return ReturnJson.returnSuccessMap(resultMap);
            } else {
                if (appVersion.getIsNew() == 0) {
                    current.put("versionId", String.valueOf(appVersion.getiVersionid()));
                    current.put("version", appVersion.getcVersion());
                    current.put("versionCode", appVersion.getcVersionCode());
                    if (appVersion.getcVersionremark() != null) {
                        current.put("versionRemark", appVersion.getcVersionremark());
                    }
                    if (appVersion.getcDownurl() != null) {
                        current.put("downUrl", appVersion.getcDownurl());
                    }
                    current.put("isCompel", appVersion.getiIscompel());
                    resultMap.put("current", current);
                    AppVersion newsApp1 = appManage.getAppNewVersionMax(Long.valueOf(appId), schoolId, versionCode);
                    if (newsApp1 != null) {
                        newApp.put("versionId", String.valueOf(newsApp1.getiVersionid()));
                        newApp.put("version", newsApp1.getcVersion());
                        newApp.put("versionCode", newsApp1.getcVersionCode());
                        if (newsApp1.getcVersionremark() != null) {
                            newApp.put("versionRemark", newsApp1.getcVersionremark());
                        }
                        if (newsApp1.getcDownurl() != null) {
                            newApp.put("downUrl", newsApp1.getcDownurl());
                        }
                        newApp.put("isCompel", newsApp1.getiIscompel());
                    }
                    resultMap.put("newApp", newApp);
                    resultMap.put("isNew", 0);
                    return ReturnJson.returnSuccessMap(resultMap);
                } else {
                    newApp.put("versionId", String.valueOf(appVersion.getiVersionid()));
                    newApp.put("version", appVersion.getcVersion());
                    newApp.put("versionCode", appVersion.getcVersionCode());
                    if (appVersion.getcVersionremark() != null) {
                        newApp.put("versionRemark", appVersion.getcVersionremark());
                    }
                    if (appVersion.getcDownurl() != null) {
                        newApp.put("downUrl", appVersion.getcDownurl());
                    }
                    newApp.put("isCompel", appVersion.getiIscompel());
                    resultMap.put("current", newApp);
                    resultMap.put("newApp", current);
                    resultMap.put("isNew", 1);
                    return ReturnJson.returnSuccessMap(resultMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /***
     * 获取最新的应用版本信息
     * */
    @RequestMapping("v1/getNewAppVersion")
    public Object getNewAppVersion_Token(String appId, String schoolId, String versionId, String versionCode, String access_token, HttpServletRequest request) {
        if (appId == null || appId.equals("") || versionCode == null || versionCode.equals("")) {
            return ReturnJson.returnFail("参数异常!");
        }
        String token = "";
        try {
            if (access_token == null || access_token.equals("")) {
                token = request.getHeader("access-token");
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
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("appId", appId);
        if (schoolId != null && !schoolId.equals("")) {
            resultMap.put("schoolId", schoolId);
        }
        AppVersion appVersion = new AppVersion();
        Map<String, Object> current = new HashMap<String, Object>();
        Map<String, Object> newApp = new HashMap<String, Object>();
        appVersion = appManage.getAppVersion(Long.valueOf(appId), schoolId, versionCode, "1");
        try {
            if (appVersion == null) {
                resultMap.put("current", current);
                AppVersion newsApp1 = appManage.getAppNewVersionMax(Long.valueOf(appId), schoolId, versionCode);
                if (newsApp1 != null) {
                    newApp.put("versionId", String.valueOf(newsApp1.getiVersionid()));
                    newApp.put("version", newsApp1.getcVersion());
                    newApp.put("versionCode", newsApp1.getcVersionCode());
                    if (newsApp1.getcVersionremark() != null) {
                        newApp.put("versionRemark", newsApp1.getcVersionremark());
                    }
                    if (newsApp1.getcDownurl() != null) {
                        newApp.put("downUrl", newsApp1.getcDownurl());
                    }
                    newApp.put("isCompel", newsApp1.getiIscompel());
                }
                resultMap.put("newApp", newApp);
                resultMap.put("isNew", 1);
                return ReturnJson.returnSuccessMap(resultMap);
            } else {
                if (appVersion.getIsNew() == 0) {
                    current.put("versionId", String.valueOf(appVersion.getiVersionid()));
                    current.put("version", appVersion.getcVersion());
                    current.put("versionCode", appVersion.getcVersionCode());
                    if (appVersion.getcVersionremark() != null) {
                        current.put("versionRemark", appVersion.getcVersionremark());
                    }
                    if (appVersion.getcDownurl() != null) {
                        current.put("downUrl", appVersion.getcDownurl());
                    }
                    current.put("isCompel", appVersion.getiIscompel());
                    resultMap.put("current", current);
                    AppVersion newsApp1 = appManage.getAppNewVersionMax(Long.valueOf(appId), schoolId, versionCode);
                    if (newsApp1 != null) {
                        newApp.put("versionId", String.valueOf(newsApp1.getiVersionid()));
                        newApp.put("version", newsApp1.getcVersion());
                        newApp.put("versionCode", newsApp1.getcVersionCode());
                        if (newsApp1.getcVersionremark() != null) {
                            newApp.put("versionRemark", newsApp1.getcVersionremark());
                        }
                        if (newsApp1.getcDownurl() != null) {
                            newApp.put("downUrl", newsApp1.getcDownurl());
                        }
                        newApp.put("isCompel", newsApp1.getiIscompel());
                    }
                    resultMap.put("newApp", newApp);
                    resultMap.put("isNew", 1);
                    return ReturnJson.returnSuccessMap(resultMap);
                } else {
                    newApp.put("versionId", String.valueOf(appVersion.getiVersionid()));
                    newApp.put("version", appVersion.getcVersion());
                    newApp.put("versionCode", appVersion.getcVersionCode());
                    if (appVersion.getcVersionremark() != null) {
                        newApp.put("versionRemark", appVersion.getcVersionremark());
                    }
                    if (appVersion.getcDownurl() != null) {
                        newApp.put("downUrl", appVersion.getcDownurl());
                    }
                    newApp.put("isCompel", appVersion.getiIscompel());
                    resultMap.put("current", newApp);
                    resultMap.put("newApp", current);
                    resultMap.put("isNew", 1);
                    return ReturnJson.returnSuccessMap(resultMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }


    /*************版本列表*******getAppVersionList***************/
    @RequestMapping("getAppVersionList")
    public Object getAppVersionList(String appId) {
        Long trueAppId = null;
        if (appId != null && !appId.equals("")) {
            trueAppId = Long.valueOf(appId);
        }
        //getAppVersionList(appId);
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> avList = appManage.getAppVersionList(trueAppId);
            map.put("list", avList);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("queryListByAppId")
    public Object queryListByAppId(String appId) {
        Long trueAppId = null;
        if (appId != null && !appId.equals("")) {
            trueAppId = Long.valueOf(appId);
        }
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> avList = appManage.queryListByAppId(trueAppId);
            map.put("list", avList);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }


    @RequestMapping("annoAppVersion")
    public Object annoAppVersion(String schoolList, String appId, String versionId) {
        if (schoolList == null || schoolList.equals("") || appId == null || appId.equals("") || versionId == null || versionId.equals("")) {
            return ReturnJson.returnFail("参数异常，请重试！");
        }
        String[] sourceStrArray = schoolList.split(",");//分割出来的字符数组
        //System.out.println("一个的时候==" + sourceStrArray.length);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (sourceStrArray.length > 1) {
                for (int i = 0; i < sourceStrArray.length; i++) {
                    //long I_IDX = snowFlake.nextId();
                    long schoolId = Long.valueOf(sourceStrArray[i]);
                    int res = appManage.updateXuApp(schoolId, Long.valueOf(appId), Long.valueOf(versionId));
                    if (res == 0) {
                        return ReturnJson.returnFail("Update_Fail");
                    }
                    jPushManage.sendMsg("11", "0", String.valueOf(schoolId), "all", "11", null);
                    AppVersion appVersion = new AppVersion();
                    appVersion.setiVersionid(Long.valueOf(versionId));
                    appVersion.setiStatus(1);
                    appVersion.settRtime(sdf.format(new Date()));
                    appManage.updateVersion(appVersion);
                }
                return ReturnJson.returnSuccessMap();
            } else {
                //System.out.println("正常");
                int res = appManage.updateXuApp(Long.valueOf(schoolList), Long.valueOf(appId), Long.valueOf(versionId));
                if (res > 0) {
                    jPushManage.sendMsg("11", "0", schoolList, "all", "11", null);
                    AppVersion appVersion = new AppVersion();
                    appVersion.setiVersionid(Long.valueOf(versionId));
                    appVersion.setiStatus(1);
                    appVersion.settRtime(sdf.format(new Date()));
                    appManage.updateVersion(appVersion);
                    return ReturnJson.returnSuccessMap();
                } else {
                    return ReturnJson.returnFail("Update_Fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getIntallByAppId")
    public Object getIntallByAppId(String pName, String isPage, String pNum, String pLine) {
        if (pName == null || pName.equals("")) {
            return ReturnJson.returnFail("参数为空，请检查数据!");
        }
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> avList = new ArrayList<>();
            Map<String, Object> pageMap = new LinkedHashMap<>();
            if (isPage == null || isPage.equals("")) {
                avList = appManage.getIntallByAppId(pName, 0, 0, 0);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码或行数不能为空!");
                } else {
                    Integer truePnum = Integer.parseInt(pNum);
                    Integer truePline = Integer.parseInt(pLine);
                    pageMap.put("pNum", truePnum);
                    pageMap.put("pLine", truePline);
                    pageMap.put("countAll", appInstallMapper.selectByAppIdCount(pName));
                    map.put("page", pageMap);//selectByAppIdCount
                    avList = appManage.getIntallByAppId(pName, Integer.parseInt(isPage), (truePnum - 1) * truePline, truePline);
                }
            }
            map.put("list", avList);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("selectAppUsers")
    public Object selectAppUsers(String appId, String versionId) {
        if (appId == null || appId.equals("")) {
            return ReturnJson.returnFail("参数有误!");
        }
        Long trueVersionId = null;
        try {
            if (versionId != null && !versionId.equals("")) {
                trueVersionId = Long.valueOf(versionId);
            }
            AppInstall appInstall = appManage.selectAppUsers(Long.valueOf(appId), trueVersionId);
            Map<String, Object> map = new HashMap<>();
            if (appInstall != null) {
                if (appInstall.getAllUsers() != null) {
                    map.put("allUsers", appInstall.getAllUsers());
                } else {
                    map.put("allUsers", "0");
                }
                if (appInstall.getVersionUsers() != null) {
                    map.put("versionUsers", appInstall.getVersionUsers());
                } else {
                    map.put("versionUsers", "0");
                }
                return ReturnJson.returnSuccessMap(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("selectThisVersion")
    public Object selectThisVersion(String appId) {
        if (appId == null || appId.equals("")) {
            return ReturnJson.returnFail("参数有误!");
        }
        try {
            AppVersion appVersion = appManage.selectThisVersion(Long.valueOf(appId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (appVersion != null) {
                Map<String, Object> map = new HashMap<>();
                if (appVersion.gettCtime() != null) {
                    Date date = DateFromat.parse(appVersion.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    map.put("upTime", sdf.format(date));
                } else {
                    map.put("upTime", "");
                }
                if (appVersion.getcVersion() != null) {
                    map.put("version", appVersion.getcVersion());
                } else {
                    map.put("version", "");
                }
                if (appVersion.getiVersionid() != null) {
                    map.put("versionId", String.valueOf(appVersion.getiVersionid()));
                } else {
                    map.put("versionId", "");
                }
                return ReturnJson.returnSuccessMap(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getDownCount")
    public Object getDownCount(String pName) {
        if (pName == null || pName.equals("")) {
            return ReturnJson.returnFail("参数有误!");
        }
        try {
            String down = appManage.getDownCount(pName);
            Map<String, Object> map = new HashMap<>();
            map.put("downCount", down);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }
}
