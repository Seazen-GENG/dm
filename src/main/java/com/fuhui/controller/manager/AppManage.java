package com.fuhui.controller.manager;

import com.fuhui.dao.trj.AppInstallLogMapper;
import com.fuhui.dao.trj.AppInstallMapper;
import com.fuhui.dao.trj.AppRjMapper;
import com.fuhui.dao.trj.AppVersionMapper;
import com.fuhui.dao.txu.AppMapper;
import com.fuhui.dao.txu.SchoolMapper;
import com.fuhui.entity.trj.AppInstall;
import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.AppVersion;
import com.fuhui.entity.txu.App;
import com.fuhui.entity.txu.School;
import com.fuhui.util.DateFromat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppManage {

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Autowired
    private AppRjMapper appRjMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private AppInstallMapper appInstallMapper;

    @Autowired
    private AppInstallLogMapper appInstallLogMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(AppManage.class);


    public void deletAppVersion(Long versionId) {
        appVersionMapper.deleteByPrimaryKey(versionId);
    }

    public void updateVersion(AppVersion appVersion) {
        appVersionMapper.updateByPrimaryKeySelective(appVersion);
    }

    public void deletApp(Long appId) {
        appRjMapper.deleteByPrimaryKey(appId);
    }

    public int addAppVersion(AppVersion appVersion) {
        //判断应用ID+版本号+版本编号是否重复
        try {
            AppVersion appVersion1 = appVersionMapper.selectByParam(appVersion.getiAppid(), appVersion.getcVersion(), appVersion.getcVersionCode());
            int k = 0;
            if (appVersion1 == null || appVersion1.equals("{}")) {
                k = appVersionMapper.insertSelective(appVersion);
                if (k > 0) {
                    return 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateVersion(Long appId, Long versionId, Long userId) {
        AppRj appRj = new AppRj();
        appRj.setiAppid(appId);
        appRj.setiVersionid(versionId);
        if (userId != null) {
            appRj.setiUuserid(userId);
        }
        int k = appRjMapper.updateByPrimaryKeySelective(appRj);
        if (k > 0) {
            return 1;
        }
        return 0;
    }


    public List<App> getDifferentSchoolByVersionId(Long appId, Long versionId) {
        List<App> apps = appMapper.selectByAppIdAndVersionId(appId, versionId);
        return apps;
    }


    public List<Map<String, Object>> getList(Long appIds, String name, String packageName, Integer type, Integer place, Integer status,
                                             Long productId, Long schoolId, Integer sort, String sCTime, String eCTime, Integer isPage, Integer pNum, Integer pLine) {
        // LOGGER.info("NO.2");
        List<AppRj> apps = appRjMapper.queryAppList(appIds, name, packageName, type, place, status, productId, schoolId, sort, sCTime, eCTime, isPage, pNum, pLine);
        LOGGER.info("queryResult=" + apps);
        List<Map<String, Object>> appsNew = new ArrayList<Map<String, Object>>();
        for (AppRj app : apps) {
            //LOGGER.info("NO.3");
            Map<String, Object> map = new HashMap<String, Object>();
            if (app.getiAppid() != null) {
                map.put("appId", String.valueOf(app.getiAppid()));
            } else {
                map.put("appId", "");
            }
            if (app.getcName() != null) {
                map.put("name", app.getcName());
            } else {
                map.put("name", "");
            }
            if (app.getcPackage() != null) {
                map.put("package", app.getcPackage());
            } else {
                map.put("package", "");
            }
            if (app.getiPlace() != null) {
                map.put("place", app.getiPlace());
            } else {
                map.put("place", "");
            }
            if (app.getiType() != null) {
                map.put("type", app.getiType());
            } else {
                map.put("type", "");
            }
            if (app.getcDesc() != null) {
                map.put("shortDesc", app.getcDesc());
            } else {
                map.put("shortDesc", "");
            }
            if (app.getiProductid() != null) {
                map.put("productId", String.valueOf(app.getiProductid()));
            } else {
                map.put("productId", "");
            }
            if (app.getcRemark() != null) {
                map.put("remark", app.getcRemark());
            } else {
                map.put("remark", "");
            }
            if (app.gettCtime() != null) {
                map.put("cTime", app.gettCtime());
            } else {
                map.put("cTime", "");
            }
            if (app.getiCuserid() != null) {
                map.put("cUserId", String.valueOf(app.getiCuserid()));
            } else {
                map.put("cUserId", "");
            }
            if (app.gettUtime() != null) {
                map.put("uTime", app.gettUtime());
            } else {
                map.put("uTime", "");
            }
            if (app.getiUuserid() != null) {
                map.put("uUserId", String.valueOf(app.getiUuserid()));
            } else {
                map.put("uUserId", "");
            }
            if (app.getiStatus() != null) {
                map.put("status", app.getiStatus());
            } else {
                map.put("status", "");
            }
            if (app.getSchoolNum() != null) {
                map.put("schoolNum", app.getSchoolNum());
            } else {
                map.put("schoolNum", "");
            }
            appsNew.add(map);
        }
        LOGGER.info("queryResult->newMap==" + appsNew);
        return appsNew;
    }

    public int add(AppRj appRj) {
        int k = appRjMapper.insertSelective(appRj);
        if (k > 0) {
            return 1;
        }
        return 0;
    }

    public int update(AppRj appRj) {
        int k = appRjMapper.updateByPrimaryKeySelective(appRj);
        if (k > 0) {
            return 1;
        }
        return 0;
    }

    /*public AppVersion getAppNewVersion(Long appId, String schoolId, String versionCode) {
        AppVersion appVersion = new AppVersion();
        if (schoolId == null || schoolId.equals("")) {
            appVersion = appVersionMapper.selectByAppId(appId, versionCode);
        } else {
            appVersion = appVersionMapper.selectByAppIdAndSchoolId(appId, Long.valueOf(schoolId), versionCode);
        }
        return appVersion;
    }*/

    public AppVersion getAppNewVersionMax(Long appId, String schoolId, String versionCode) {
        AppVersion appVersion = new AppVersion();
        if (schoolId == null || schoolId.equals("")) {
            appVersion = appVersionMapper.selectByAppIdMax(appId, versionCode);
        } else {
            appVersion = appVersionMapper.selectByAppIdAndSchoolIdMax(appId, Long.valueOf(schoolId), versionCode);
        }
        return appVersion;
    }

    public AppVersion getAppVersion(Long appId, String schoolId, String versionCode, String checkFlg) {
        AppVersion appVersion = new AppVersion();
        if (checkFlg.equals("0")) {
            appVersion = appVersionMapper.queryByAppIdAndVersionId(appId, versionCode);
        } else if (checkFlg.equals("1")) {
            if (schoolId == null || schoolId.equals("")) {
                appVersion = appVersionMapper.selectByAppIdAndVersionId(appId, versionCode);
            } else {
                appVersion = appVersionMapper.selectByAppIdAndVersionIdAndSchoolId(appId, versionCode, Long.valueOf(schoolId));
            }
        }
        return appVersion;
    }

    //queryByAppId
    public List<Map<String, Object>> getAppVersionList(Long appId) {
        List<AppVersion> appVersionList = appVersionMapper.queryByAppId(appId);
        List<Map<String, Object>> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (AppVersion av : appVersionList) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (av.getiAppid() != null) {
                map.put("appId", String.valueOf(av.getiAppid()));
            } else {
                map.put("appId", "");
            }
            if (av.getiVersionid() != null) {
                map.put("versionId", String.valueOf(av.getiVersionid()));
            } else {
                map.put("versionId", "");
            }
            //C_VersionRemark
            if (av.getcVersionremark() != null) {
                map.put("versionRemark", av.getcVersionremark());
            } else {
                map.put("versionRemark", "");
            }
            if (av.gettReleasetime() != null) {
                Date date = DateFromat.parse(av.gettReleasetime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                map.put("releaseTime", sdf.format(date));//date);
            } else {
                map.put("releaseTime", "");
            }
            AppRj appRj = av.getAppRj();
            if (appRj != null) {
                map.put("appName", appRj.getcName());
            } else {
                map.put("appName", "");
            }
            if (av.getcVersion() != null) {
                map.put("version", av.getcVersion());
            } else {
                map.put("version", "");
            }
            if (av.getcVersionCode() != null) {
                map.put("versionCode", av.getcVersionCode());
            } else {
                map.put("versionCode", "");
            }
            if (av.getcDownurl() != null) {
                map.put("downUrl", av.getcDownurl());
            } else {
                map.put("downUrl", "");
            }
            if (av.gettCtime() != null) {
                Date date = DateFromat.parse(av.gettCtime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                map.put("createTime", sdf.format(date));
            } else {
                map.put("createTime", "");
            }
            if (av.getiStatus() != null) {
                map.put("status", av.getiStatus());
            } else {
                map.put("status", "");
            }
            resultList.add(map);
        }
        return resultList;
    }

    public List<Map<String, Object>> queryListByAppId(Long appId) {
        List<School> schools = schoolMapper.queryListByAppId(appId);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (schools != null && !schools.isEmpty()) {
            for (School school : schools) {
                Map<String, Object> map = new HashMap<>();
                if (school.getiSchoolid() != null) {
                    map.put("schoolId", String.valueOf(school.getiSchoolid()));
                } else {
                    map.put("schoolId", "");
                }
                if (school.getcName() != null) {
                    map.put("schoolName", school.getcName());
                } else {
                    map.put("schoolName", "");
                }
                AppVersion appVersion = school.getAppVersion();
                if (appVersion != null) {
                    map.put("version", appVersion.getcVersion());
                    map.put("versionCode", appVersion.getcVersionCode());
                } else {
                    map.put("version", "");
                    map.put("versionCode", "");
                }
                resultList.add(map);
            }
        }
        return resultList;
    }

    //updateByAppIdAndSchoolId
    public int updateXuApp(Long schoolId, Long appId, Long versionId) {
        int k = appMapper.updateByAppIdAndSchoolId(appId, schoolId, versionId, new Date());
        if (k > 0) {
            return 1;
        }
        return 0;
    }

    public List<Map<String, Object>> getIntallByAppId(String pName, Integer isPage, Integer pNum, Integer pLine) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<AppInstall> list = appInstallMapper.selectByAppId(pName, isPage, pNum, pLine);
        if (list != null && !list.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (AppInstall app : list) {
                Map<String, Object> map = new HashMap<>();
                if (app.getiPadid() != null) {
                    map.put("padId", String.valueOf(app.getiPadid()));
                } else {
                    map.put("padId", "");
                }
                if (app.getcMac() != null) {
                    map.put("mac", app.getcMac());
                } else {
                    map.put("mac", "");
                }
                if (app.getcVersion() != null) {
                    map.put("version", app.getcVersion());
                } else {
                    map.put("version", "");
                }
                if (app.getcVersioncode() != null) {
                    map.put("versionCode", app.getcVersioncode());
                } else {
                    map.put("versionCode", "");
                }
                if (app.gettFtime() != null) {
                    map.put("time", sdf.format(app.gettFtime()));
                } else {
                    map.put("time", "");
                }
                resultList.add(map);
            }
        }
        return resultList;
    }

    public AppInstall selectAppUsers(Long appId, Long versionId) {
        AppInstall appInstall = appInstallMapper.selectAppUsers(appId, versionId);
        return appInstall;
    }

    public AppVersion selectThisVersion(Long appId){
        AppVersion a = appVersionMapper.selectThisVersion(appId);
        return a;
    }

    public String getDownCount(String pName){
        return appInstallLogMapper.getDownCount(pName);
    }
}
