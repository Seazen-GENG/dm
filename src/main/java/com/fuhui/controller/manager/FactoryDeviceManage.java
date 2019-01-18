package com.fuhui.controller.manager;

import com.fuhui.dao.model.DeviceListMapper;
import com.fuhui.dao.tcs.*;
import com.fuhui.entity.model.DevSchoolList;
import com.fuhui.entity.model.DeviceList;
import com.fuhui.entity.tcs.*;
import com.fuhui.util.DateFromat;
import com.fuhui.util.SnowFlake;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FactoryDeviceManage {

    @Autowired
    private DeviceListMapper deviceListMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private FversionMapper fversionMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private FversionInstallLogMapper fvInstallLogMapper;

    @Autowired
    private FversionReleaseMapper releaseMapper;

    public List<DeviceList> getList(String factoryIds, Long schoolId, String name, String model, String deviceType, Integer status,
                                    Integer isPage, Integer pNum, Integer pLine) {
        List<DeviceList> list = deviceListMapper.selectListByParam(factoryIds, schoolId, name, model, deviceType, status, isPage, pNum, pLine);
        return list;
    }

    public String getCount(String factoryIds, Long schoolId, String name, String model, String deviceType, Integer status) {
        String list = deviceListMapper.selectListByParamCount(factoryIds, schoolId, name, model, deviceType, status);
        return list;
    }

    public Map<String, Object> getInfo(Long deviceId) {
        Map<String, Object> map = new HashMap<>();
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        if (device != null) {
            map.put("deviceId", String.valueOf(device.getiFdeviceid()));
            map.put("deviceType", device.getiDtype());
            map.put("factoryId", String.valueOf(device.getiFactoryid()));
            map.put("fversionId", String.valueOf(device.getiFversionid()));
            map.put("model", device.getcModel());
            map.put("status", device.getiStatus());
            if (device.getcImg() != null) {
                map.put("img", device.getcImg());
            } else {
                map.put("img", "");
            }
            if (device.getcSpec() != null) {
                map.put("spec", device.getcSpec());
            } else {
                map.put("spec", "");
            }
            if (device.getcSpec() != null) {
                map.put("spec", device.getcSpec());
            } else {
                map.put("spec", "");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (device.gettCtime() != null) {
                map.put("cTime", sdf.format(device.gettCtime()));
            } else {
                map.put("cTime", "");
            }
            if (device.gettUtime() != null) {
                map.put("uTime", sdf.format(device.gettUtime()));
            } else {
                map.put("uTime", "");
            }
            ////获取厂商信息
            /*Factory factory = factoryMapper.selectByPrimaryKey(device.getiFactoryid());
            if (factory != null) {
                map.put("factoryName", factory.getcName());
            }else {
                map.put("factoryName", "");
            }*/
            ////获取版本信息
            Fversion fversion = fversionMapper.selectByPrimaryKey(device.getiFversionid());
            if (fversion != null) {
                map.put("fversion", fversion.getcVersion());
                map.put("fversionTime", sdf.format(fversion.gettReleasetime()));
            } else {
                map.put("fversion", "");
                map.put("fversionTime", "");
            }
        }
        return map;
    }

    public List<Map<String, Object>> getVersionListByDeviceId(Long deviceId, Integer status, Integer isPage, Integer pNum, Integer pLine) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Fversion> fvList = fversionMapper.selectListByDeviceId(deviceId, status, isPage, pNum, pLine);
        if (fvList != null && !fvList.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Fversion f : fvList) {
                Map<String, Object> map = new HashMap<>();
                map.put("fVersionId", String.valueOf(f.getiFversionid()));
                map.put("version", f.getcVersion());
                map.put("downUrl", f.getcDownurl());
                if (f.gettReleasetime() != null) {
                    map.put("releaseTime", sdf.format(f.gettReleasetime()));
                } else {
                    map.put("releaseTime", "");
                }
                map.put("cTime", sdf.format(f.gettCtime()));
                map.put("uTime", sdf.format(f.gettUtime()));
                map.put("status", f.getiStatus());
                list.add(map);
            }
        }
        return list;
    }

    public List<DevSchoolList> getSchoolListByDeviceId(Long deviceId, Integer deviceType, Integer schoolStatus, Integer isPage, Integer pNum
            , Integer pLine) {
        return fversionMapper.getSchoolListByDeviceId(deviceId, deviceType, schoolStatus, isPage, pNum, pLine);
    }

    public String getSchoolListByDeviceIdCount(Long deviceId, Integer deviceType, Integer schoolStatus) {
        String count = fversionMapper.getSchoolListByDeviceIdCount(deviceId, deviceType, schoolStatus);
        if (count == null) {
            count = "0";
        }
        return count;
    }

    public String getVersionListByDeviceIdCount(Long deviceId, Integer status) {
        String count = fversionMapper.selectListByDeviceIdCount(deviceId, status);
        if (count == null) {
            count = "0";
        }
        return count;
    }

    public Fversion getNewVersionByMac(String mac, String configTime) {
        Fversion fversion = fversionMapper.getNewVersionByMac(mac, configTime);
        return fversion;
    }

    public Fversion getCurVersion(String versionCode) {
        Fversion fversion = fversionMapper.queryFactoryAndDevice(versionCode);
        return fversion;
    }


    public List<Map<String, Object>> getFVersionInstallLog(Long schoolId, Long padId, String mac, Long fdeviceId, Long fversionId, String version, String sTime, String eTime, Integer isPage, Integer pNum, Integer pLine) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<FversionInstallLog> fvList = fvInstallLogMapper.selectByParam(schoolId, padId, mac, fdeviceId, fversionId, version, sTime, eTime, isPage, pNum, pLine);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (fvList != null && !fvList.isEmpty()) {
            for (FversionInstallLog f : fvList) {
                Map<String, Object> map = new HashMap<>();
                map.put("logId", String.valueOf(f.getiLogid()));
                if (f.getSchoolName() != null) {
                    map.put("schoolName", f.getSchoolName());
                } else {
                    map.put("schoolName", "");
                }
                map.put("version", f.getcVersion());
                map.put("mac", f.getcMac());
                map.put("installTime", sdf.format(f.gettTime()));
                list.add(map);
            }
        }
        return list;
    }

    public String selectByParamCount(Long schoolId, Long padId, String mac, Long fdeviceId, Long fversionId, String version, String sTime, String eTime) {
        String count = fvInstallLogMapper.selectByParamCount(schoolId, padId, mac, fdeviceId, fversionId, version, sTime, eTime);
        if (count == null) {
            count = "0";
        }
        return count;
    }

    public int addDeviceVersion(Long deviceId, Long factoryId, String version, String vRemark, Long ufId
            , String downUrl, String md5, String releaseTime, Integer isReview, String remark, Integer status, HttpServletRequest request,String versionCode) {
        Fversion fa = fversionMapper.selectRepeatVersion(deviceId, versionCode);
        int k = 0;
        if (fa != null) {
            return 0;
        } else {
            Fversion fv = new Fversion();
            SnowFlake snowFlake = new SnowFlake(2, 3);
            ///选填字段
            if (ufId != null) {
                fv.setiUFId(ufId);
            }
            if (md5 != null && !md5.equals("")) {
                fv.setcMD5(md5);
            }
            if (releaseTime != null && !releaseTime.equals("")) {
                //将字符串时间转换为date格式
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String date = sdf.format(new Date());
                String trueDate = releaseTime + " " + date;
                System.out.println("time==" + date);
                fv.settReleasetime(DateFromat.parse(trueDate, "yyyy-MM-dd HH:mm:ss", Locale.CHINA));
            }
            if (remark != null && !remark.equals("")) {
                fv.setcRemark(remark);
            }
            ///必填字段
            Long fversionId = snowFlake.nextId();
            fv.setiFversionid(fversionId);
            fv.setiFactoryid(factoryId);
            fv.setiFdeviceid(deviceId);
            fv.setcVersionCode(versionCode);
            fv.setcVersion(version);
            fv.setcDownurl(downUrl);
            fv.setiIsreview(isReview);
            fv.setiStatus(status);
            if (status == 1) {
                //deviceId 添加版本_状态为可发布时，更新device表
                Device device = new Device();
                device.setiFdeviceid(deviceId);
                device.setiFversionid(fversionId);
                device.settFutime(new Date());
                device.settUtime(new Date());
                deviceMapper.updateByPrimaryKeySelective(device);
                fv.settReleasetime(new Date());
            }
            fv.setcVersionremark(vRemark);
            ////隐藏字段
            fv.settCtime(new Date());
            fv.settUtime(new Date());
            ///根据session获取用户id
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            fv.setiCuserid(Long.valueOf(userId));
            fv.setiUuserid(Long.valueOf(userId));
            k = fversionMapper.insertSelective(fv);
        }
        return k;
    }

    public int add(Device device) {
        return deviceMapper.insertSelective(device);
    }

    public int update(Device device) {
        return deviceMapper.updateByPrimaryKeySelective(device);
    }

    ///厂商管理
    public int addFactory(Factory factory) {
        return factoryMapper.insertSelective(factory);
    }

    public int updateFactory(Factory factory) {
        return factoryMapper.updateByPrimaryKeySelective(factory);
    }

    ////分页查询
    public List<Map<String, Object>> getFactoryList(Long factoryId, Integer isPage, Integer pNum, Integer pLine) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Factory> fList = factoryMapper.getFactoryList(factoryId, isPage, pNum, pLine);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (fList != null && !fList.isEmpty()) {
            for (Factory f : fList) {
                Map<String, Object> map = new HashMap<>();
                map.put("factoryId", String.valueOf(f.getiFactoryid()));
                map.put("name", f.getcName());
                map.put("address", f.getcAddress());
                map.put("createTime", sdf.format(f.gettCtime()));
                map.put("updateTime", sdf.format(f.gettUtime()));
                map.put("status", f.getiStatus());
                map.put("type", f.getiType());
                if (f.getcDtype() != null) {
                    String dType = f.getcDtype();
                    String finalyType = "";
                    if (dType.indexOf("1") != -1) {
                        finalyType += "/路由器";
                    }
                    if (dType.indexOf("2") != -1) {
                        finalyType += "/平板";
                    }
                    if (dType.indexOf("3") != -1) {
                        finalyType += "/手写板";
                    }
                    map.put("dType", finalyType);
                } else {
                    map.put("dType", "");
                }
                if (f.getContName() != null) {
                    map.put("contName", f.getContName());
                } else {
                    map.put("contName", "");
                }
                if (f.getDeivecNum() != null) {
                    map.put("devcieNum", f.getDeivecNum());
                } else {
                    map.put("devcieNum", 0);
                }
                if (f.getcPhone() != null) {
                    map.put("phone", f.getcPhone());
                } else {
                    map.put("phone", "");
                }
                if (f.getcFax() != null) {
                    map.put("fax", f.getcFax());
                } else {
                    map.put("fax", "");
                }
                if (f.getContPhone() != null) {
                    map.put("contPhone", f.getContPhone());
                } else {
                    map.put("contPhone", "");
                }
                list.add(map);
            }
        }
        return list;
    }

    public String getFactoryListCount(Long factoryId) {
        return factoryMapper.getFactoryListCount(factoryId);
    }

    public String getDeviceNum(Long factoryId) {
        return factoryMapper.getDeviceNum(factoryId);
    }

    public int addReleaseVersion(List<FversionRelease> frelease) {
        try {
            for (FversionRelease f : frelease) {
                releaseMapper.insertSelective(f);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
