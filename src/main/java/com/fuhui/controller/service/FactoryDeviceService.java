package com.fuhui.controller.service;

import com.fuhui.controller.manager.FactoryDeviceManage;
import com.fuhui.entity.model.DeviceList;
import com.fuhui.entity.tcs.Device;
import com.fuhui.entity.tcs.Factory;
import com.fuhui.entity.tcs.Fversion;
import com.fuhui.entity.tcs.FversionRelease;
import com.fuhui.util.DateFromat;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("deviceService")
public class FactoryDeviceService {

    @Autowired
    private FactoryDeviceManage factoryDeviceManage;

    private final static Logger logger = LoggerFactory.getLogger(FactoryDeviceService.class);

    /************
     *  单一指定学校时，调用
     * ***************/
    @RequestMapping("getConfigListBySchoolId")
    public Object getConfigListBySchoolId(String factoryIds, String schoolId, String name, String model, String deviceType, String status,
                                          String isPage, String pNum, String pLine) {
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        Integer statu = null;
        if (status != null && !status.equals("")) {
            statu = Integer.parseInt(status);
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List<DeviceList> list = new ArrayList<>();
            if (isPage == null || isPage.equals("") || !isPage.equals("1")) {
                list = factoryDeviceManage.getList(factoryIds, Long.valueOf(schoolId), name, model, deviceType, statu, null, null, null);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码与行数不能为空!");
                }
                Integer pageNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                if (pageNum < 0) {
                    pageNum = 0;
                }
                list = factoryDeviceManage.getList(factoryIds, Long.valueOf(schoolId), name, model, deviceType, statu, Integer.parseInt(isPage), pageNum, Integer.parseInt(pLine));
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", factoryDeviceManage.getCount(factoryIds, Long.valueOf(schoolId), name, model, deviceType, statu));
                map.put("page", pageMap);
            }
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            //return ReturnJson.returnFail("系统异常!");
        }
        return ReturnJson.returnFail();
    }

    /************
     *  超级管理员使用，拥有全部权限
     * ***************/
    @RequestMapping("getConfigList")
    public Object getConfigList(String factoryIds, String schoolId, String name, String model, String deviceType, String status,
                                String isPage, String pNum, String pLine) {
        Long trueId = null;
        if (schoolId != null && !schoolId.equals("")) {
            trueId = Long.valueOf(schoolId);
        }
        Integer statu = null;
        if (status != null && !status.equals("")) {
            statu = Integer.parseInt(status);
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List<DeviceList> list = new ArrayList<>();
            if (isPage == null || isPage.equals("") || !isPage.equals("1")) {
                list = factoryDeviceManage.getList(factoryIds, trueId, name, model, deviceType, statu, null, null, null);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码与行数不能为空!");
                }
                Integer pageNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                if (pageNum < 0) {
                    pageNum = 0;
                }
                list = factoryDeviceManage.getList(factoryIds, trueId, name, model, deviceType, statu, Integer.parseInt(isPage), pageNum, Integer.parseInt(pLine));
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", factoryDeviceManage.getCount(factoryIds, trueId, name, model, deviceType, statu));
                map.put("page", pageMap);
            }
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            //return ReturnJson.returnFail("系统异常!");
        }
        return ReturnJson.returnFail();
    }

    /******
     * 获取厂商设备信息
     * **/
    @RequestMapping("getDeviceInfoById")
    public Object getDeviceInfoById(String deviceId) {
        if (deviceId == null || deviceId.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        try {
            Map<String, Object> map = factoryDeviceManage.getInfo(Long.valueOf(deviceId));
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /***
     * 根据设备ID获取设备固件版本列表
     */
    @RequestMapping("getVersionListByDeviceId")
    public Object getVersionListByDeviceId(String deviceId, String status, String isPage, String pNum, String pLine) {
        if (deviceId == null || deviceId.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        Integer statu = null;
        if (status != null && !status.equals("")) {
            statu = Integer.parseInt(status);
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> list = new ArrayList<>();
            if (isPage == null || isPage.equals("") || !isPage.equals("1")) {
                list = factoryDeviceManage.getVersionListByDeviceId(Long.valueOf(deviceId), statu, null, null, null);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码与行数不能为空!");
                }
                Integer pageNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                if (pageNum < 0) {
                    pageNum = 0;
                }
                list = factoryDeviceManage.getVersionListByDeviceId(Long.valueOf(deviceId), statu, Integer.parseInt(isPage), pageNum, Integer.parseInt(pLine));
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", factoryDeviceManage.getVersionListByDeviceIdCount(Long.valueOf(deviceId), statu));
                map.put("page", pageMap);
            }
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            //return ReturnJson.returnFail("系统异常!");
        }
        return ReturnJson.returnFail();
    }

    /**
     * 根据设备id查询应用学校列表
     */
    @RequestMapping("getSchoolListByDeviceId")
    public Object getSchoolListByDeviceId(String deviceId, String deviceType, String schoolStatus, String isPage, String pNum
            , String pLine, String showVersion) {
        if (deviceId == null || deviceId.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        Integer type = null;
        if (deviceType != null) {
            type = Integer.parseInt(deviceType);
        }
        Integer statu = null;
        if (schoolStatus != null) {
            statu = Integer.parseInt(schoolStatus);
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List list = new ArrayList<>();
            if (isPage == null || isPage.equals("") || !isPage.equals("1")) {
                list = factoryDeviceManage.getSchoolListByDeviceId(Long.valueOf(deviceId), type, statu, null, null, null);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码与行数不能为空!");
                }
                Integer pageNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                if (pageNum < 0) {
                    pageNum = 0;
                }
                list = factoryDeviceManage.getSchoolListByDeviceId(Long.valueOf(deviceId), type, statu, Integer.parseInt(isPage), pageNum, Integer.parseInt(pLine));
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", factoryDeviceManage.getSchoolListByDeviceIdCount(Long.valueOf(deviceId), type, statu));
                map.put("page", pageMap);
            }
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /*********
     * 根据设置MAC获取最新固件信息
     * @param mac
     * @param versionCode
     * @param deviceModel
     * @return
     */
    @RequestMapping("getNewVersionByMac")
    public Object getNewVersionByMac(String mac, String versionCode, String deviceModel) {
        if (mac == null || mac.equals("") || versionCode == null || versionCode.equals("") || deviceModel == null || deviceModel.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        Fversion fversion = factoryDeviceManage.getNewVersionByMac(mac, null);
        try {
            if (fversion != null) {
                Map<String, Object> map = new HashMap<>();
                String newVersion = fversion.getcVersionCode();
                map.put("schoolId", String.valueOf(fversion.getSchoolId()));
                map.put("fDeviceId", String.valueOf(fversion.getiFdeviceid()));
                Map<String, Object> crutMap = new HashMap<>();
                if (newVersion.equals(versionCode)) {
                    map.put("isNew", 1);
                } else {
                    {
                        //当前版本
                        Fversion fversion1 = factoryDeviceManage.getCurVersion(versionCode);
                        if (fversion1 != null) {
                            crutMap.put("fVersionId", String.valueOf(fversion1.getiFversionid()));
                            crutMap.put("version", fversion1.getcVersion());
                            crutMap.put("versionCode", fversion1.getcVersionCode());
                        }
                        map.put("currentVersion", crutMap);
                    }
                    map.put("isNew", 0);
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("fVersionId", String.valueOf(fversion.getiFversionid()));
                    if (fversion.getcVersion() != null) {
                        newMap.put("version", fversion.getcVersion());
                    } else {
                        newMap.put("version", "");
                    }
                    if (fversion.getcVersionremark() != null) {
                        newMap.put("versionRemark", fversion.getcVersionremark());
                    } else {
                        newMap.put("versionRemark", "");
                    }
                    if (fversion.getcDownurl() != null) {
                        newMap.put("downUrl", fversion.getcDownurl());
                    } else {
                        newMap.put("downUrl", "");
                    }
                    if (fversion.getcMD5() != null) {
                        newMap.put("md5", fversion.getcMD5());
                    } else {
                        newMap.put("md5", "");
                    }
                    if (fversion.getcVersionCode() != null) {
                        newMap.put("versionCode", fversion.getcVersionCode());
                    } else {
                        newMap.put("versionCode", "");
                    }
                    map.put("newVersion", newMap);
                }
                return ReturnJson.returnSuccessMap(map);
            } else {
                return ReturnJson.returnFail("没有找到对应的数据!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /*******
     * 获取设备固件版本安装记录列表
     * ******/
    @RequestMapping("getFVersionInstallList")
    public Object getFVersionInstallList(String schoolId, String padId, String mac, String fdeviceId, String fversionId, String version, String sTime, String eTime, String isPage, String pNum, String pLine) {
        Long trueSchoolId = null;
        if (schoolId != null && !schoolId.equals("")) {
            trueSchoolId = Long.valueOf(schoolId);
        }
        Long truePadId = null;
        if (padId != null && !padId.equals("")) {
            truePadId = Long.valueOf(padId);
        }
        Long trueFdeviceId = null;
        if (fdeviceId != null && !fdeviceId.equals("")) {
            trueFdeviceId = Long.valueOf(fdeviceId);
        }
        Long trueFversionId = null;
        if (fversionId != null && !fversionId.equals("")) {
            trueFversionId = Long.valueOf(fversionId);
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List list = new ArrayList<>();
            if (isPage == null || isPage.equals("") || !isPage.equals("1")) {
                list = factoryDeviceManage.getFVersionInstallLog(trueSchoolId, truePadId, mac, trueFdeviceId, trueFversionId, version, sTime, eTime, null, null, null);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码与行数不能为空!");
                }
                Integer pageNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                if (pageNum < 0) {
                    pageNum = 0;
                }
                list = factoryDeviceManage.getFVersionInstallLog(trueSchoolId, truePadId, mac, trueFdeviceId, trueFversionId, version, sTime, eTime, Integer.parseInt(isPage), pageNum, Integer.parseInt(pLine));
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", factoryDeviceManage.selectByParamCount(trueSchoolId, truePadId, mac, trueFdeviceId, trueFversionId, version, sTime, eTime));
                map.put("page", pageMap);
            }
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }


    /***
     * 保存固件版本
     * **/
    @RequestMapping("saveDeviceVersion")
    public Object saveDeviceVersion(String deviceId, String versionId, String factoryId, String version, String vRemark, String ufId
            , String downUrl, String md5, String releaseTime, String isReview, String remark, String status, HttpServletRequest request, String versionCode) {
        if (deviceId == null || deviceId.equals("") || factoryId == null || factoryId.equals("") || version == null || version.equals("")
                || vRemark == null || vRemark.equals("") || downUrl == null || downUrl.equals("") || isReview == null || isReview.equals("")
                || status == null || status.equals("") || versionCode == null || versionCode.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        Long trueUfid = null;
        if (ufId != null && !ufId.equals("")) {
            trueUfid = Long.valueOf(ufId);
        }
        int k = factoryDeviceManage.addDeviceVersion(Long.valueOf(deviceId), Long.valueOf(factoryId), version, vRemark, trueUfid, downUrl, md5, releaseTime, Integer.parseInt(isReview), remark, Integer.parseInt(status), request, versionCode);
        if (k > 0) {
            return ReturnJson.returnSuccessMap();
        }
        return ReturnJson.returnFail();
    }

    /*******新增、更新厂商设备信息**********/
    @RequestMapping("saveDevice")
    public Object saveDevice(String deviceId, String name, String factoryId, String deviceType, String model, String img,
                             String spec, String remark, String status, HttpServletRequest request) {
       /* name	rk3368H
        model	D14B
        spec	123456
        deviceType	2
        status	1
        deviceId	267290171907784704*/

        if (name == null || name.equals("") || factoryId == null || factoryId.equals("") || deviceType == null
                || deviceType.equals("") || model == null || model.equals("")) {
            return ReturnJson.returnFail("参数有误，请检查后重试!");
        }
        Device device = new Device();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        {
            device.setcName(name);
            device.setiFactoryid(Long.valueOf(factoryId));
            device.setiDtype(Integer.parseInt(deviceType));
            device.setcModel(model);
            if (remark != null && !remark.equals("")) {
                device.setcRemark(remark);
            }
            if (img != null && !img.equals("")) {
                device.setcImg(img);
            }
            if (status == null || status.equals("")) {
                device.setiStatus(1);
            } else {
                device.setiStatus(Integer.parseInt(status));
            }
            if (spec != null && !spec.equals("")) {
                device.setcSpec(spec);
            }
            //额外的必填参数
            device.settUtime(new Date());
            device.setiUuserid(Long.valueOf(userId));
        }
        try {
            int k = 0;
            Long trueDeviceId = null;
            if (deviceId == null || deviceId.equals("")) {
                //添加
                SnowFlake snowFlake = new SnowFlake(2, 4);
                trueDeviceId = snowFlake.nextId();
                device.settCtime(new Date());
                device.setiCuserid(Long.valueOf(userId));
                device.setiFdeviceid(trueDeviceId);
                k = factoryDeviceManage.add(device);
            } else {
                //更新
                trueDeviceId = Long.valueOf(deviceId);
                device.setiFdeviceid(trueDeviceId);
                k = factoryDeviceManage.update(device);
            }
            Map<String, Object> map = new HashMap<>();
            if (k > 0) {
                map.put("deviceId", String.valueOf(trueDeviceId));
                return ReturnJson.returnSuccessMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /*****************&&&&&&&&&&&&&&&厂商管理（增删改查）**************/
    @RequestMapping("getFactoryList")
    public Object getFactoryList(String factoryId, String isPage, String pNum, String pLine) {
        Long trueFactoryId = null;
        if (factoryId != null && !factoryId.equals("")) {
            trueFactoryId = Long.valueOf(factoryId);
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List list = new ArrayList<>();
            if (isPage == null || isPage.equals("") || !isPage.equals("1")) {
                list = factoryDeviceManage.getFactoryList(trueFactoryId, null, null, null);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail("分页时，页码与行数不能为空!");
                }
                Integer pageNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                if (pageNum < 0) {
                    pageNum = 0;
                }
                list = factoryDeviceManage.getFactoryList(trueFactoryId, Integer.parseInt(isPage), pageNum, Integer.parseInt(pLine));
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", factoryDeviceManage.getFactoryListCount(trueFactoryId));
                map.put("page", pageMap);
            }
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /********根据factoryId获取对应pad的数量*****************/
    @RequestMapping("getDeviceNum")
    public Object getDeviceNum(String factoryId) {
        if (factoryId != null && !factoryId.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put("padNum", factoryDeviceManage.getDeviceNum(Long.valueOf(factoryId)));
            return ReturnJson.returnSuccessMap(map);
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("saveFactory")
    public Object saveFactory(String factoryId, String name, String code, String address, String phone, String fax, String type, String Dtype, String remark, String status, HttpServletRequest request) {
        if (name == null || name.equals("") || address == null || address.equals("") || type == null || type.equals("") || status == null || status.equals("")) {
            return ReturnJson.returnFail("参数有误请重试");
        }
        Factory factory = new Factory();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        {
            //必填
            factory.setcName(name);
            factory.setcAddress(address);
            factory.setiType(Integer.parseInt(type));
            factory.setiStatus(Integer.parseInt(status));
            //选填
            if (code != null && !code.equals("")) {
                factory.setcCode(code);
            }
            if (fax != null && !fax.equals("")) {
                factory.setcFax(fax);
            }
            if (phone != null && !phone.equals("")) {
                factory.setcPhone(phone);
            }
            if (Dtype != null && !Dtype.equals("")) {
                factory.setcDtype(Dtype);
            }
            if (remark != null && !remark.equals("")) {
                factory.setcRemark(remark);
            }
            factory.settUtime(new Date());
            factory.setiUuserid(Long.valueOf(userId));
        }
        if (factoryId != null && !factoryId.equals("")) {
            //更新
            factory.setiFactoryid(Long.valueOf(factoryId));
            int k = factoryDeviceManage.updateFactory(factory);
            if (k > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("factoryId", String.valueOf(factoryId));
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            //新增
            SnowFlake snowFlake = new SnowFlake(2, 4);
            Long finalFactory = snowFlake.nextId();
            factory.setiFactoryid(finalFactory);
            factory.settCtime(new Date());
            factory.setiCuserid(Long.valueOf(userId));
            int k = factoryDeviceManage.addFactory(factory);
            if (k > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("factoryId", String.valueOf(finalFactory));
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail();
    }

    ///发布固件更新（可以根据全校、指定pad发布固件）
    @RequestMapping("relseaVersion")
    public Object relseaVersion(String fdeviceId, String fversionId, String schoolId, String range, String rangeIds, String downUrl, String remark
            , String status, String releaseTime, HttpServletRequest request) {
        if (fdeviceId == null || fdeviceId.equals("") || fversionId == null || fversionId.equals("") || schoolId == null || schoolId.equals("") ||
                range == null || range.equals("") || releaseTime == null || releaseTime.equals("")) {
            return ReturnJson.returnFail("参数有误，请重试!");
        }
        FversionRelease frelease = new FversionRelease();
        List<FversionRelease> rec = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String date1 = sdf.format(new Date());
        String trueDate = releaseTime + " " + date1;
        System.out.println("time==" + date1);
        Date date = DateFromat.parse(trueDate, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Integer trueRange = Integer.parseInt(range);
        SnowFlake snowFlake = new SnowFlake(2, 4);
        Long releaseId = snowFlake.nextId();
        {
            //必填参数
            frelease.setiReleaseid(releaseId);
            frelease.setiFdeviceid(Long.valueOf(fdeviceId));
            frelease.setiFversionid(Long.valueOf(fversionId));
            frelease.setiSchoolid(Long.valueOf(schoolId));
            frelease.setiRange(trueRange);
            frelease.settCtime(new Date());
            frelease.settReleasetime(date);
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            frelease.setiCuserid(Long.valueOf(userId));
            //选填
            if (rangeIds != null && rangeIds.equals("")) {
                frelease.setcRangeids(rangeIds);
            }
            if (downUrl != null && downUrl.equals("")) {
                frelease.setcDownurl(downUrl);
            }
            if (remark != null && remark.equals("")) {
                frelease.setcRemark(remark);
            }
            if (status != null && status.equals("")) {
                frelease.setiStatus(Integer.parseInt(status));
            } else {
                frelease.setiStatus(1);
            }
            rec.add(frelease);
        }
        logger.info("发布固件版本时_合成批量添加的数组==" + rec);
        int k = factoryDeviceManage.addReleaseVersion(rec);
        Map<String, Object> map = new HashMap<>();
        if (k > 0) {
            switch (trueRange) {
                case 0:
                    map.put("releaseId", String.valueOf(releaseId));
                    return ReturnJson.returnSuccessMap(map);
                case 1:
                case 2:
                case 3:
                case 4:

            }
        }
        return ReturnJson.returnFail();
    }
}
