package com.fuhui.controller.service;

import cn.jiguang.common.utils.StringUtils;
import com.fuhui.controller.manager.OauthManage;
import com.fuhui.controller.manager.PadManager;
import com.fuhui.controller.manager.SQAuthorizeManage;
import com.fuhui.controller.manager.getIpManage;
import com.fuhui.dao.taz.PadauthorizeMapper;
import com.fuhui.dao.taz.PadauthorizePadlistMapper;
import com.fuhui.dao.tcs.FversionMapper;
import com.fuhui.dao.tpd.JPushMapper;
import com.fuhui.dao.tpd.PadMapper;
import com.fuhui.dao.tpd.PadSwitchLogMapper;
import com.fuhui.dao.tut.IpAddressMapper;
import com.fuhui.dao.txu.SchoolMapper;
import com.fuhui.entity.model.PadInstallApp;
import com.fuhui.entity.model.padConfigModel;
import com.fuhui.entity.taz.Padauthorize;
import com.fuhui.entity.taz.PadauthorizePadlist;
import com.fuhui.entity.tcs.Fversion;
import com.fuhui.entity.tpd.JPush;
import com.fuhui.entity.tpd.Pad;
import com.fuhui.entity.tpd.PadBangDingLog;
import com.fuhui.entity.tpd.PadSwitchLog;
import com.fuhui.entity.tut.IpAddress;
import com.fuhui.entity.txu.School;
import com.fuhui.util.AddressUtils;
import com.fuhui.util.HTTPUtils;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("padService")
public class PadService {

    @Autowired
    private PadManager padManager;

    @Autowired
    private JPushMapper jPushMapper;

    @Autowired
    private SQAuthorizeManage sqAuthorizeManage;

    @Autowired
    private PadauthorizeMapper padauthorizeMapper;

    @Autowired
    private PadauthorizePadlistMapper padPadlistMapper;

    @Autowired
    private PadMapper padMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private IpAddressMapper ipAddressMapper;

    @Autowired
    private getIpManage getIpManage;

    @Autowired
    private OauthManage oauthManage;

    @Autowired
    private PadSwitchLogMapper padSwitchLogMapper;

    @Autowired
    private FversionMapper fversionMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(PadService.class);

    /**
     * 根据mac获取pad配置列表
     *
     * @param padMac
     * @param model
     * @param fVersion
     * @param fUTime
     * @return
     * @throws Exception
     */
    @RequestMapping("getPadIdByPadMac")
    public Object getInfo(String padMac, String model, String fVersion, String fUTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        Pad pad = padManager.getInfoByPadMac(padMac);
        if (pad == null || pad.equals("")) {
            SnowFlake snowFlake = new SnowFlake(2, 3);//使用雪花算法生成全局唯一ID
            Pad pad1 = new Pad();
            long iPadid = snowFlake.nextId();
            /*try {
                ///根据当前固件版本反查出设备ID及厂商ID
                Fversion fversion = fversionMapper.queryFactoryAndDevice(fVersion);
                if (fversion != null && !fversion.equals("")){
                    LOGGER.info("反查出设备ID及厂商ID——success");
                    pad1.setiFactoryid(fversion.getiFactoryid());
                    pad1.setiDeviceid(fversion.getiFdeviceid());
                }
            }catch (Exception e){
                LOGGER.info("反查出设备ID及厂商ID——FAIL");
                e.printStackTrace();
            }*/
            pad1.setiPadid(iPadid);
            pad1.setcMac(padMac);
            if (model != null) {
                pad1.setcModel(model);
            }
            if (fVersion != null) {
                pad1.setcFversion(fVersion);
            }
            if (fUTime != null) {
                pad1.settFutime(new Date());
            }
            //padConfig非空的默认参数
            pad1.setiType(0);
            Date date = new Date();
            pad1.settCtime(sdf.parse(sdf.format(date)));
            pad1.setiCplace(1);
            pad1.setiUplace(1);
            pad1.setiStatus(1);
            int result = padManager.add(pad1);
            if (result > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("padId", String.valueOf(iPadid));
                map.put("auzId", "");
                map.put("type", "");
                map.put("auzEDate", "");
                map.put("schoolId", "");
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            long padId = pad.getiPadid();
            Pad pad2 = new Pad();
            pad2.setiPadid(padId);
            pad2.setcMac(padMac);
            if (model != null) {
                pad2.setcModel(model);
            }
            if (fVersion != null) {
                pad2.setcFversion(fVersion);
            }
            if (fUTime != null) {
                pad2.settFutime(new Date());
            }

            int result = padManager.update(pad2);
            if (result > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("padId", String.valueOf(padId));
                Pad padAuthorize = padMapper.getAuthorizeByPadMac(padMac);
                System.out.println("授权表" + padAuthorize);
                if (padAuthorize == null) {
                    map.put("auzId", "");
                    map.put("type", "");
                    map.put("auzEDate", "");
                    map.put("schoolId", "");
                } else {
                    Padauthorize authorize = padAuthorize.getPadauthorize();//得到授权表，为了取出授权过期时间
                    System.out.println("authorize==" + authorize.getiAuzid());
                    if (authorize.getiAuzid() != null) {
                        map.put("auzId", String.valueOf(authorize.getiAuzid()));
                    } else {
                        map.put("auzId", "");
                    }
                    Padauthorize padauthorize = padauthorizeMapper.selectByPrimaryKey(authorize.getiAuzid());
                    if (padauthorize != null) {
                        map.put("type", padauthorize.getiType());
                    } else {
                        map.put("type", "");
                    }
                    if (padauthorize.getdEdate() != null) {
                        map.put("auzEDate", sdf.format(padauthorize.getdEdate()));
                    } else {
                        map.put("auzEDate", "");
                    }
                    if (padauthorize.getiSchoolid() != null) {
                        map.put("schoolId", String.valueOf(padauthorize.getiSchoolid()));
                    } else {
                        map.put("schoolId", "");
                    }
                }
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail();
    }

    /**
     * 保存极光信息
     *
     * @param padId
     * @param registrationId
     * @param padMac
     * @return
     */
    @RequestMapping("savePadJPush")
    public Object savePadJPush(String padId, String registrationId, String padMac) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (padId != null && registrationId != null && padMac != null) {
                int result = padManager.savePadJPush(padId, registrationId);
                if (result > 0) {
                    JPush jPush = jPushMapper.getPadJPushInfoByPadId(Long.valueOf(padId));
                    System.out.println("jPush**" + jPush);
                    map.put("padId", String.valueOf(jPush.getiPadid()));
                    map.put("registrationId", String.valueOf(jPush.getcRegistrationid()));
                    map.put("alias", jPush.getcAlias());
                    map.put("tags", jPush.getcTags());
                    return ReturnJson.returnSuccessMap(map);
                }
            }
        } catch (Exception e) {
            System.out.println("param_Exception");
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("v1/savePadJPush")
    public Object savePadJPush_Token(String padId, String registrationId, String padMac, String access_token, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String token = "";
            if (padId != null && registrationId != null && padMac != null) {
                try {
                    if (access_token == null || access_token.equals("")) {
                        token = request.getHeader("access_token");
                    } else {
                        token = access_token;
                    }
                    System.out.println("token==" + token);
                    if (token == null || token.equals("")) {
                        return ReturnJson.returnFail(102, "没有携带有效令牌!");
                    }
                    Map<String, Object> map1 = oauthManage.checkToken(token);
                    if (map1 == null || map1.isEmpty()) {
                        return ReturnJson.returnFail(102, "accessToken无效或已过期。");
                    }
                } catch (Exception e) {
                    return ReturnJson.returnFail(102, "accessToken无效或已过期。");
                }
                int result = padManager.savePadJPush(padId, registrationId);
                if (result > 0) {
                    JPush jPush = jPushMapper.getPadJPushInfoByPadId(Long.valueOf(padId));
                    System.out.println("jPush**" + jPush);
                    map.put("padId", String.valueOf(jPush.getiPadid()));
                    map.put("registrationId", String.valueOf(jPush.getcRegistrationid()));
                    map.put("alias", jPush.getcAlias());
                    map.put("tags", jPush.getcTags());
                    return ReturnJson.returnSuccessMap(map);
                }
            }
        } catch (Exception e) {
            System.out.println("param_Exception");
        }
        return ReturnJson.returnFail();
    }

    /**
     * 设备授权
     *
     * @param padMac
     * @param serialNo
     * @return
     * @throws Exception
     */
    @RequestMapping("authorizePad")
    public Object authorizePad(String padMac, String serialNo, HttpServletRequest request) throws Exception {
        if (padMac == null || padMac.equals("") || serialNo == null || serialNo.equals("")) {
            return ReturnJson.returnFail("参数异常");
        }
        SnowFlake snowFlake = new SnowFlake(2, 3);
        Pad pad1 = padManager.getInfoByPadMac(padMac);
        LOGGER.info("查询是否存在mac");
        if (pad1 == null) {
            LOGGER.info("无mac，相应正常报错信息！");
            return ReturnJson.returnFail(201, "设备未注册!");
        }
        LOGGER.info("查询授权码是否可用!");
        Padauthorize padauthorize3 = padauthorizeMapper.selectBySerialNo(serialNo);
        if (padauthorize3 == null) {
            LOGGER.info("在管控数据库中，没有该授权码！");
            return ReturnJson.returnFail(224, "该授权码不存在!");
        }
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOGGER.info("调取授权接口！");
        Map<String, Object> strMap = sqAuthorizeManage.authorizePad(padMac, serialNo);
        LOGGER.info("授权接口return==" + strMap);
        Integer type = -1;
        String AuthResult = (String) strMap.get("AuthResult");
        try {
            if (AuthResult.equals("4")) {
                type = 0;
            } else if (AuthResult.equals("5")) {
                type = 1;
            } else if (AuthResult.equals("1")) {
                return ReturnJson.returnFail(224, "此授权码无效！");
            } else if (AuthResult.equals("2")) {
                return ReturnJson.returnFail(222, "此授权码已过期！");
            } else if (AuthResult.equals("3")) {
                return ReturnJson.returnFail(225, "超过合用台数！");
            } else if (AuthResult.equals("6")) {
                return ReturnJson.returnFail(120, "系统异常，请联系管理员！");
            } else if (AuthResult.equals("7")) {
                return ReturnJson.returnFail(226, "此授权码与该设备类型不匹配！");
            } else {
                return ReturnJson.returnFail(220, "注册失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnJson.returnFail("注册失败！");
        }
        String AuthTimes = (String) strMap.get("AuthTimes");//存库
        String AuthValidDate = (String) strMap.get("AuthValidDate");
        map.put("type", type);
        if (AuthValidDate.equals("0") || AuthValidDate == null) {
            map.put("auzEDate", "");
        } else {
            map.put("auzEDate", AuthValidDate + " 00:00:00");
        }
        Padauthorize padize = null;
        try {
            padize = padauthorizeMapper.selectBySerialNo(serialNo);
        } catch (Exception e) {
            LOGGER.info("查询授权信息存在异常数据，即一个授权码不能两个学校同时使用!");
            return ReturnJson.returnFail("授权信息存在异常数据!");
        }
        LOGGER.info("查询授权信息是否存在==" + padize);
        LOGGER.info("空则添加，非空更新！");
        String ip = "";
        try {
            ip = getIpManage.getTrueIp(request);
            LOGGER.info("获取到真实的ip地址==" + ip);
            String address = AddressUtils.getTrueIpAddress(ip);
            IpAddress ipAddress1 = ipAddressMapper.selectByIpAndAddress(ip, address);
            LOGGER.info("查询ip地址表ipAddress==" + ipAddress1);
            if (ipAddress1 == null) {
                IpAddress ipAddress = new IpAddress();
                ipAddress.setiId(snowFlake.nextId());
                ipAddress.setcIp(ip);
                ipAddress.setcAddress(address);
                ipAddress.settCtime(new Date());
                ipAddressMapper.insertSelective(ipAddress);
                LOGGER.info("添加ip地址表success");
            }
            LOGGER.info("获取ip==" + ip);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("获取ip异常!");
            //return ReturnJson.returnFail("获取ip地址信息异常!");
        }
        Long school = null;
        if (padize != null) {
            //根据serialNo更新
            Padauthorize padauize = new Padauthorize();
            padauize.setiAuzid(padize.getiAuzid());
            padauize.setcSerialno(serialNo);
            padauize.setiTotal(Integer.parseInt(AuthTimes));
            padauize.setiAcount(Integer.parseInt(AuthTimes));
            padauize.setiType(type);
            padauize.settUtime(new Date());
            if (!AuthValidDate.equals("0")) {
                padauize.setdEdate(sdf.parse(AuthValidDate + " 00:00:00"));
            } else {
                padauize.setdEdate(null);
            }
            int k = padauthorizeMapper.update(padauize);
            if (k > 0) {
                LOGGER.info("更新pad表授权信息success");
                Pad pad = padManager.getInfoByPadMac(padMac);
                long padId = 0;
                if (pad != null) {
                    padId = pad.getiPadid();
                }
                System.out.println("授权码**" + padauize.getiAuzid());
                PadauthorizePadlist padlist = padPadlistMapper.selectBySerialNoAndPadId(padauize.getiAuzid(), padId);
                if (padlist != null) {
                    PadauthorizePadlist padlist1 = new PadauthorizePadlist();
                    padlist1.setiIdx(padlist.getiIdx());
                    padlist1.settFtime(new Date());
                    padlist1.setiPadid(padId);
                    int jg = padPadlistMapper.update(padlist1);
                    LOGGER.info("更新pad表授权信息success");
                    if (jg > 0) {
                        Padauthorize padizeUp = padauthorizeMapper.selectBySerialNo(serialNo);
                        if (padizeUp.getiSchoolid() != null) {
                            school = padizeUp.getiSchoolid();
                        }
                        updatePad(padMac, serialNo, padauize.getiAuzid(), school, pad1.getcFversion());
                        try {
                            //更新极光表中的tages
                            JPush jPush = jPushMapper.getPadJPushInfoByPadId(padId);
                            if (jPush != null) {
                                padManager.savePadJPush(String.valueOf(padId), String.valueOf(jPush.getcRegistrationid()));
                            }
                            LOGGER.info("更新极光表trage==success");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        map.put("auzId", String.valueOf(padauize.getiAuzid()));
                        map.put("schoolId", String.valueOf(school));
                        return ReturnJson.returnSuccessMap(map);
                    }
                } else {
                    long Id = snowFlake.nextId();
                    PadauthorizePadlist padlist3 = new PadauthorizePadlist();
                    padlist3.setiIdx(Id);
                    padlist3.setiAuzid(padauize.getiAuzid());
                    padlist3.setiPadid(pad1.getiPadid());//根据mac查询出padId然后添加到此表中
                    if (ip != null) {
                        padlist3.setcIp(ip);
                    }
                    padlist3.setcMac(pad1.getcMac());
                    padlist3.settFtime(new Date());
                    int jg = padPadlistMapper.add(padlist3);
                    if (jg > 0) {
                        LOGGER.info("添加pad表授权信息success");
                        Padauthorize padizeUp = padauthorizeMapper.selectBySerialNo(serialNo);
                        if (padizeUp.getiSchoolid() != null) {
                            school = padizeUp.getiSchoolid();
                        }
                        updatePad(padMac, serialNo, padauize.getiAuzid(), school, pad1.getcFversion());
                        map.put("auzId", String.valueOf(padauize.getiAuzid()));
                        //更新极光表中的tages
                       /* JPush jPush = jPushMapper.getPadJPushInfoByPadId(padId);
                        padManager.savePadJPush(String.valueOf(padId),String.valueOf(jPush.getcRegistrationid()));*/
                        map.put("schoolId", String.valueOf(school));
                        return ReturnJson.returnSuccessMap(map);
                    }
                }
            }
        } else {
            long auzid = snowFlake.nextId();
            Padauthorize padauize = new Padauthorize();
            padauize.setiAuzid(auzid);
            padauize.setcSerialno(serialNo);
            padauize.setiTotal(Integer.parseInt(AuthTimes));
            padauize.setiAcount(Integer.parseInt(AuthTimes));
            padauize.setiType(type);
            padauize.setiAType(2);
            padauize.settCtime(new Date());
            padauize.settUtime(new Date());
            padauize.setdSdate(new Date());
            if (!AuthValidDate.equals("0")) {
                padauize.setdEdate(sdf.parse(AuthValidDate + " 00:00:00"));
            }
            padauize.setiStatus(1);
//            padauize.setiCuserid();//创建人
//            padauize.setiUuserid();//更新人
            int k = padauthorizeMapper.add(padauize);
            if (k > 0) {
                LOGGER.info("添加授权信息success");
                //新增子表
                long Id = snowFlake.nextId();
                PadauthorizePadlist padlist = new PadauthorizePadlist();
                padlist.setiIdx(Id);
                padlist.setiAuzid(auzid);
                padlist.setcIp(ip);
                padlist.setcMac(pad1.getcMac());
                Pad pad = padManager.getInfoByPadMac(padMac);
                long padId = 0;
                if (pad != null) {
                    padId = pad.getiPadid();
                }
                padlist.setiPadid(padId);//根据mac查询出padId然后添加到此表中
                padlist.settFtime(new Date());
                int jg = padPadlistMapper.add(padlist);
                if (jg > 0) {
                    LOGGER.info("添加pad表授权信息success");
                    Padauthorize padizeUp = padauthorizeMapper.selectBySerialNo(serialNo);
                    if (padizeUp.getiSchoolid() != null) {
                        school = padizeUp.getiSchoolid();
                    }
                    updatePad(padMac, serialNo, padauize.getiAuzid(), school, pad1.getcFversion());
                    map.put("auzId", String.valueOf(auzid));
                    map.put("schoolId", String.valueOf(school));
                    return ReturnJson.returnSuccessMap(map);
                }
            }
        }
        LOGGER.info("添加授权表信息失败！");
        return ReturnJson.returnFail();
    }

    /**
     * Pc端授权
     * @param padMac
     * @param serialNo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("authorizePc")
    public Object authorizePc(String padMac, String serialNo, HttpServletRequest request) throws Exception {
        if (padMac == null || padMac.equals("") || serialNo == null || serialNo.equals("")) {
            return ReturnJson.returnFail("参数异常");
        }
        SnowFlake snowFlake = new SnowFlake(2, 3);
        Pad pad1 = padManager.getInfoByPadMac(padMac);
        LOGGER.info("查询是否存在mac");
        if (pad1 == null) {
            LOGGER.info("无mac，相应正常报错信息！");
            return ReturnJson.returnFail(201, "设备未注册!");
        }
        LOGGER.info("查询授权码是否可用!");
        Padauthorize padauthorize3 = padauthorizeMapper.selectBySerialNo(serialNo);
        if (padauthorize3 == null) {
            LOGGER.info("在管控数据库中，没有该授权码！");
            return ReturnJson.returnFail(224, "该授权码不存在!");
        }
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOGGER.info("调取授权接口！");
        Map<String, Object> strMap = sqAuthorizeManage.authorizePc(padMac, serialNo);
        LOGGER.info("授权接口return==" + strMap);
        Integer type = -1;
        String AuthResult = (String) strMap.get("AuthResult");
        try {
            if (AuthResult.equals("4")) {
                type = 0;
            } else if (AuthResult.equals("5")) {
                type = 1;
            } else if (AuthResult.equals("1")) {
                return ReturnJson.returnFail(224, "此授权码无效！");
            } else if (AuthResult.equals("2")) {
                return ReturnJson.returnFail(222, "此授权码已过期！");
            } else if (AuthResult.equals("3")) {
                return ReturnJson.returnFail(225, "超过合用台数！");
            } else if (AuthResult.equals("6")) {
                return ReturnJson.returnFail(120, "系统异常，请联系管理员！");
            } else if (AuthResult.equals("7")) {
                return ReturnJson.returnFail(226, "此授权码与该设备类型不匹配！");
            } else {
                return ReturnJson.returnFail(220, "注册失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnJson.returnFail("注册失败！");
        }
        String AuthTimes = (String) strMap.get("AuthTimes");//存库
        String AuthValidDate = (String) strMap.get("AuthValidDate");
        map.put("type", type);
        if (AuthValidDate.equals("0") || AuthValidDate == null) {
            map.put("auzEDate", "");
        } else {
            map.put("auzEDate", AuthValidDate + " 00:00:00");
        }
        Padauthorize padize = null;
        try {
            padize = padauthorizeMapper.selectBySerialNo(serialNo);
        } catch (Exception e) {
            LOGGER.info("查询授权信息存在异常数据，即一个授权码不能两个学校同时使用!");
            return ReturnJson.returnFail("授权信息存在异常数据!");
        }
        LOGGER.info("查询授权信息是否存在==" + padize);
        LOGGER.info("空则添加，非空更新！");
        String ip = "";
        try {
            ip = getIpManage.getTrueIp(request);
            LOGGER.info("获取到真实的ip地址==" + ip);
            String address = AddressUtils.getTrueIpAddress(ip);
            IpAddress ipAddress1 = ipAddressMapper.selectByIpAndAddress(ip, address);
            LOGGER.info("查询ip地址表ipAddress==" + ipAddress1);
            if (ipAddress1 == null) {
                IpAddress ipAddress = new IpAddress();
                ipAddress.setiId(snowFlake.nextId());
                ipAddress.setcIp(ip);
                ipAddress.setcAddress(address);
                ipAddress.settCtime(new Date());
                ipAddressMapper.insertSelective(ipAddress);
                LOGGER.info("添加ip地址表success");
            }
            LOGGER.info("获取ip==" + ip);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("获取ip异常!");
        }
        Long school = null;
        if (padize != null) {
            //根据serialNo更新
            Padauthorize padauize = new Padauthorize();
            padauize.setiAuzid(padize.getiAuzid());
            padauize.setcSerialno(serialNo);
            padauize.setiTotal(Integer.parseInt(AuthTimes));
            padauize.setiAcount(Integer.parseInt(AuthTimes));
            padauize.setiType(type);
            padauize.settUtime(new Date());
            if (!AuthValidDate.equals("0")) {
                padauize.setdEdate(sdf.parse(AuthValidDate + " 00:00:00"));
            } else {
                padauize.setdEdate(null);
            }
            int k = padauthorizeMapper.update(padauize);
            if (k > 0) {
                LOGGER.info("更新pad表授权信息success");
                Pad pad = padManager.getInfoByPadMac(padMac);
                long padId = 0;
                if (pad != null) {
                    padId = pad.getiPadid();
                }
                System.out.println("授权码**" + padauize.getiAuzid());
                PadauthorizePadlist padlist = padPadlistMapper.selectBySerialNoAndPadId(padauize.getiAuzid(), padId);
                if (padlist != null) {
                    PadauthorizePadlist padlist1 = new PadauthorizePadlist();
                    padlist1.setiIdx(padlist.getiIdx());
                    padlist1.settFtime(new Date());
                    padlist1.setiPadid(padId);
                    int jg = padPadlistMapper.update(padlist1);
                    LOGGER.info("更新pad表授权信息success");
                    if (jg > 0) {
                        Padauthorize padizeUp = padauthorizeMapper.selectBySerialNo(serialNo);
                        if (padizeUp.getiSchoolid() != null) {
                            school = padizeUp.getiSchoolid();
                        }
                        updatePad(padMac, serialNo, padauize.getiAuzid(), school, pad1.getcFversion());
                        try {
                            //更新极光表中的tages
                            JPush jPush = jPushMapper.getPadJPushInfoByPadId(padId);
                            if (jPush != null) {
                                padManager.savePadJPush(String.valueOf(padId), String.valueOf(jPush.getcRegistrationid()));
                            }
                            LOGGER.info("更新极光表trage==success");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        map.put("auzId", String.valueOf(padauize.getiAuzid()));
                        map.put("schoolId", String.valueOf(school));
                        return ReturnJson.returnSuccessMap(map);
                    }
                } else {
                    long Id = snowFlake.nextId();
                    PadauthorizePadlist padlist3 = new PadauthorizePadlist();
                    padlist3.setiIdx(Id);
                    padlist3.setiAuzid(padauize.getiAuzid());
                    padlist3.setiPadid(pad1.getiPadid());//根据mac查询出padId然后添加到此表中
                    if (ip != null) {
                        padlist3.setcIp(ip);
                    }
                    padlist3.setcMac(pad1.getcMac());
                    padlist3.settFtime(new Date());
                    int jg = padPadlistMapper.add(padlist3);
                    if (jg > 0) {
                        LOGGER.info("添加pad表授权信息success");
                        Padauthorize padizeUp = padauthorizeMapper.selectBySerialNo(serialNo);
                        if (padizeUp.getiSchoolid() != null) {
                            school = padizeUp.getiSchoolid();
                        }
                        updatePad(padMac, serialNo, padauize.getiAuzid(), school, pad1.getcFversion());
                        map.put("auzId", String.valueOf(padauize.getiAuzid()));
                        //更新极光表中的tages
                       /* JPush jPush = jPushMapper.getPadJPushInfoByPadId(padId);
                        padManager.savePadJPush(String.valueOf(padId),String.valueOf(jPush.getcRegistrationid()));*/
                        map.put("schoolId", String.valueOf(school));
                        return ReturnJson.returnSuccessMap(map);
                    }
                }
            }
        } else {
            long auzid = snowFlake.nextId();
            Padauthorize padauize = new Padauthorize();
            padauize.setiAuzid(auzid);
            padauize.setcSerialno(serialNo);
            padauize.setiTotal(Integer.parseInt(AuthTimes));
            padauize.setiAcount(Integer.parseInt(AuthTimes));
            padauize.setiType(type);
            padauize.setiAType(2);
            padauize.settCtime(new Date());
            padauize.settUtime(new Date());
            padauize.setdSdate(new Date());
            if (!AuthValidDate.equals("0")) {
                padauize.setdEdate(sdf.parse(AuthValidDate + " 00:00:00"));
            }
            padauize.setiStatus(1);
//            padauize.setiCuserid();//创建人
//            padauize.setiUuserid();//更新人
            int k = padauthorizeMapper.add(padauize);
            if (k > 0) {
                LOGGER.info("添加授权信息success");
                //新增子表
                long Id = snowFlake.nextId();
                PadauthorizePadlist padlist = new PadauthorizePadlist();
                padlist.setiIdx(Id);
                padlist.setiAuzid(auzid);
                padlist.setcIp(ip);
                padlist.setcMac(pad1.getcMac());
                Pad pad = padManager.getInfoByPadMac(padMac);
                long padId = 0;
                if (pad != null) {
                    padId = pad.getiPadid();
                }
                padlist.setiPadid(padId);//根据mac查询出padId然后添加到此表中
                padlist.settFtime(new Date());
                int jg = padPadlistMapper.add(padlist);
                if (jg > 0) {
                    LOGGER.info("添加pad表授权信息success");
                    Padauthorize padizeUp = padauthorizeMapper.selectBySerialNo(serialNo);
                    if (padizeUp.getiSchoolid() != null) {
                        school = padizeUp.getiSchoolid();
                    }
                    updatePad(padMac, serialNo, padauize.getiAuzid(), school, pad1.getcFversion());
                    map.put("auzId", String.valueOf(auzid));
                    map.put("schoolId", String.valueOf(school));
                    return ReturnJson.returnSuccessMap(map);
                }
            }
        }
        LOGGER.info("添加授权表信息失败！");
        return ReturnJson.returnFail();
    }

    /***
     * 根据学校ID获取所有的pad列表信息
     * @param schoolIds
     * @param padMac
     * @param classId
     * @param factoryId
     * @param deviceId
     * @param model
     * @param fVersion
     * @param type
     * @param status
     * @param isPage
     * @param pNum
     * @param pLine
     * @return
     */
    @RequestMapping("getPadListBySchoolId")
    public Object getPadListBySchoolId(String schoolIds, String padMac, String classId, String factoryId,
                                       String deviceId, String model, String fVersion, String type,
                                       String status, String isPage, String pNum, String pLine) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long facId = -1;
        if (factoryId != null && !factoryId.equals("")) {
            facId = Long.valueOf(factoryId);
        }
        long deviceId1 = -1;
        if (deviceId != null) {
            deviceId1 = Long.valueOf(deviceId);
        }
        Integer type1 = -1;
        if (type != null) {
            type1 = Integer.parseInt(type);
        }
        Integer status1 = 1;
        if (status != null) {
            status1 = Integer.parseInt(status);
        }
        String trueMac = null;
        if (padMac != null && !padMac.equals("")) {
            trueMac = padMac;
        }
        String trueSchoolIds = null;
        if (schoolIds != null && !schoolIds.equals("")) {
            trueSchoolIds = schoolIds;
        }
        System.out.println("finally_mac*****" + trueMac);
        List<Pad> padList = new ArrayList<Pad>();
        if (isPage == null || isPage.equals("0")) {
            padList = padManager.getList(trueSchoolIds, trueMac, classId, facId, deviceId1, model, fVersion, type1, status1, 0, 0, 0);
        } else if (isPage.equals("1")) {
            if (pNum == null || pLine == null || pNum.equals("") || pLine.equals("")) {
                return ReturnJson.returnFail();
            }
            Integer isPage1 = Integer.parseInt(isPage);
            Integer pNum1 = Integer.parseInt(pNum);
            Integer pLine1 = Integer.parseInt(pLine);
            Map<String, Object> pages = new HashMap<>();
            padList = padManager.getList(trueSchoolIds, trueMac, classId, facId, deviceId1, model, fVersion, type1, status1, isPage1, (pNum1 - 1) * pLine1, pLine1);
            pages.put("pageNum", pNum);
            pages.put("pageLine ", pLine);
            pages.put("countAll", padManager.countPad(trueSchoolIds, trueMac, classId, facId, deviceId1, model, fVersion, type1, status1));
            resultMap.put("page", pages);
        }
        System.out.println("queryPad==" + padList);
        //构造结果集
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Pad pa : padList) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (pa.getiPadid() != null) {
                map.put("padId", String.valueOf(pa.getiPadid()));
            } else {
                map.put("padId", "");
            }
            if (pa.getcMac() != null) {
                map.put("padMac", pa.getcMac());
            } else {
                map.put("padMac", "");
            }
            if (pa.getiSchoolid() != null) {
                map.put("schoolId", String.valueOf(pa.getiSchoolid()));
            } else {
                map.put("schoolId", "");
            }
            School school = schoolMapper.selectByPrimaryKey(pa.getiSchoolid());
            if (school != null) {
                map.put("schoolName", school.getcName());
            } else {
                map.put("schoolName", "");
            }
            if (pa.getcModel() != null) {
                map.put("model", pa.getcModel());
            } else {
                map.put("model", "");
            }
            if (pa.getiType() != null) {
                map.put("type", pa.getiType());
            } else {
                map.put("type", "");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (pa.gettStime() != null) {
                map.put("sTime", sdf.format(pa.gettStime()));
            } else {
                map.put("sTime", "");
            }
            if (pa.gettFutime() != null) {
                map.put("uTime", sdf.format(pa.gettFutime()));
            } else {
                map.put("uTime", "");
            }
            if (pa.getiStatus() != null) {
                map.put("status", pa.getiStatus());
            } else {
                map.put("status", "");
            }
            if (pa.getcFversion() != null) {
                map.put("fversion", pa.getcFversion());
            } else {
                map.put("fversion", "");
            }
            mapList.add(map);
        }
        resultMap.put("list", mapList);
        return ReturnJson.returnSuccessMap(resultMap);
    }

    /**
     * 更新pad授权信息
     *
     * @param padMac
     * @param serialNo
     * @param auzId
     * @param schooldId
     */
    public void updatePad(String padMac, String serialNo, long auzId, Long schooldId, String fVersion) {
        {
            Pad update = new Pad();
            try {
                ///根据当前固件版本反查出设备ID及厂商ID
                Fversion fversion = fversionMapper.queryFactoryAndDeviceByCodeAndSchoolId(fVersion, schooldId);
                if (fversion != null && !fversion.equals("")) {
                    LOGGER.info("反查出设备ID及厂商ID——success");
                    update.setiFactoryid(fversion.getiFactoryid());
                    update.setiDeviceid(fversion.getiFdeviceid());
                }
            } catch (Exception e) {
                LOGGER.info("反查出设备ID及厂商ID——FAIL");
                e.printStackTrace();
            }
            update.setcMac(padMac);
            update.setcSerialno(serialNo);
            update.setiAuzid(auzId);
            update.setiSchoolid(schooldId);
            update.settFutime(new Date());
            padMapper.updatePadauz(update);
        }
    }

    /***
     * 获取pad的详细信息
     * @param padMac
     * @return
     */
    @RequestMapping("getPadInfo")
    public Object getPadInfo(String padMac) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        Pad pad = padManager.getInfoByPadMac(padMac);
        if (pad != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mac", pad.getcMac());
            if (pad.getcSerialno() != null) {
                map.put("serialno", pad.getcSerialno());
            } else {
                map.put("serialno", "");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (pad.gettFutime() != null) {
                map.put("endAuTime", sdf.format(pad.gettFutime()));
            } else {
                map.put("endAuTime", "");
            }
            if (pad.gettCtime() != null) {
                map.put("startAuTime", sdf.format(pad.gettCtime()));
            } else {
                map.put("startAuTime", "");
            }
            return ReturnJson.returnSuccessMap(map);
        }
        return ReturnJson.returnFail();
    }

    /***
     * 获取开关机记录列表
     * @param padMac
     * @return
     */
    @RequestMapping("getPadSwitchLog")
    public Object getPadSwitchLog(String padMac, String isPage, String pNum, String pLine) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        Integer truePage = -1;
        Integer trueNum = -1;
        Integer trueLine = -1;
        Map<String, Object> pageMap = new HashMap<>();
        Map<String, Object> mapRe = new HashMap<>();
        if (isPage == null || isPage.equals("")) {
            truePage = 0;
        } else {
            truePage = 1;
            if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                return ReturnJson.returnFail("分页时，页码和行数不能为空!");
            }
            trueNum = Integer.parseInt(pNum);
            trueLine = Integer.parseInt(pLine);
            String count = padSwitchLogMapper.selectCountAll(padMac);
            pageMap.put("countAll", count);
            pageMap.put("pNum", pNum);
            pageMap.put("pLine", pLine);
            mapRe.put("page", pageMap);
        }
        List<PadSwitchLog> padSwitchLog = padSwitchLogMapper.selectByPadMac(padMac, truePage, (trueNum - 1) * trueLine, trueLine);
        List<Map<String, Object>> mapList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (PadSwitchLog pads : padSwitchLog) {
                Map<String, Object> map = new HashMap<>();
                map.put("padLogId", pads.getiLogid());
                if (pads.gettTime() != null) {
                    map.put("opTime", sdf.format(pads.gettTime()));
                } else {
                    map.put("opTime", "");
                }
                map.put("type", pads.getiType());
                map.put("padId", String.valueOf(pads.getiPadid()));
                mapList.add(map);
            }
            mapRe.put("list", mapList);
            return ReturnJson.returnSuccessMap(mapRe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /***
     * 获取最后一次的开关机记录
     * @param padMac
     * @return
     */
    @RequestMapping("getPadSwitchLogFinally")
    public Object getPadSwitchLogFinally(String padMac) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        PadSwitchLog padSwitchLog = padSwitchLogMapper.selectByMacFinal(padMac);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, Object> map = new HashMap<>();
            if (padSwitchLog != null) {
                map.put("padLogId", padSwitchLog.getiLogid());
                if (padSwitchLog.gettTime() != null) {
                    map.put("opTime", sdf.format(padSwitchLog.gettTime()));
                } else {
                    map.put("opTime", "");
                }
                map.put("type", padSwitchLog.getiType());
                map.put("padId", String.valueOf(padSwitchLog.getiPadid()));
            }
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /**
     * 根据padId获取pad的安装应用
     *
     * @param padMac
     * @return
     */
    @RequestMapping("getInstallAppList")
    public Object getInstallAppList(String padMac, String isPage, String pNum, String pLine) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            List<PadInstallApp> list = new ArrayList<>();
            if (isPage != null && !isPage.equals("")) {
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine", pLine);
                pageMap.put("countAll", padManager.getInstallAppListCount(padMac));
                map.put("page", pageMap);
                Integer trueNum = (Integer.parseInt(pNum) - 1) * Integer.parseInt(pLine);
                list = padManager.getInstallAppList(padMac, isPage, trueNum, Integer.parseInt(pLine));
            } else {
                list = padManager.getInstallAppList(padMac, isPage, null, null);
            }
            if (list != null && !list.isEmpty()) {
                map.put("list", list);
            }
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /**
     * 根据padId获取pad的安装应用
     *
     * @param padMac
     * @return
     */
    @RequestMapping("getPadAppList")
    public Object getPadAppList(String padMac) {
        if (padMac == null || padMac.equals("")) {
            return ReturnJson.returnFail();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            List<PadInstallApp> list = padManager.getPadAppList(padMac);
            if (list != null && !list.isEmpty()) {
                map.put("list", list);
            }
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /**
     * 239737711500734464  254960306365935600
     *
     * @param padId
     * @return
     */
    @RequestMapping("getConfigList")
    public Object getConfigList(String padId) {
        if (padId == null || padId.equals("")) {
            return ReturnJson.returnFail("参数有误!");
        }
        try {
            Map<String, Object> map = new HashMap<>();
            List<padConfigModel> list = padManager.getConfigList(padId);
            map.put("list", list);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /**
     * 给设备绑定用户
     *
     * @param padId
     * @param userId
     * @param padType
     * @param place
     * @return
     */
    @RequestMapping("bindingPadUser")
    public Object bindingPadUser(String padId, String userId, String padType, String place, String gradeId, String classId) {
        if (padId == null || padId.equals("") || padType == null || padType.equals("") || place == null || place.equals("")) {
            return ReturnJson.returnFail("参数异常，请重试！");
        }
        Pad pad1 = padMapper.selectByPrimaryKey(Long.valueOf(padId));
        if (pad1 == null) {
            return ReturnJson.returnFail("设备不存在，请检查传入数据！");
        }
        SnowFlake snowFlake = new SnowFlake(2, 3);
        List<PadBangDingLog> bangdingList = new ArrayList<>();
        PadBangDingLog bangDingLog = new PadBangDingLog();
        Long bangdingId = snowFlake.nextId();
        {
            //查询出pad在使用注册码注册pad时的学校id
            bangDingLog.setiSchoolid(pad1.getiSchoolid());
            bangDingLog.setiLogid(bangdingId);
            bangDingLog.setiPadid(Long.valueOf(padId));
            if (userId != null && !userId.equals("")) {
                bangDingLog.setiUserid(Long.valueOf(userId));
                bangDingLog.setiUuserid(Long.valueOf(userId));
            }
            bangDingLog.settTime(new Date());
            bangDingLog.setiType(Integer.parseInt(padType));
            bangDingLog.setiUplace(Integer.parseInt(place));
            if (gradeId != null && !gradeId.equals("")) {
                bangDingLog.setiGradeid(Long.valueOf(gradeId));
            }
            if (classId != null && !classId.equals("")) {
                bangDingLog.setiClassid(Long.valueOf(classId));
            }
            bangdingList.add(bangDingLog);
        }
        try {
            int k = padManager.savePadBinding(bangdingList);
            if (k > 0) {
                Pad pad = new Pad();
                {
                    Integer type = Integer.parseInt(padType);
                    pad.setiPadid(Long.valueOf(padId));
                    pad.settUtime(new Date());
                    pad.setiUplace(Integer.parseInt(place));
                    pad.setiType(type);
                    if (userId != null && !userId.equals("")) {
                        pad.setiUserid(Long.valueOf(userId));
                    }
                    if (gradeId != null && !gradeId.equals("")) {
                        pad.setiGradeid(Long.valueOf(gradeId));
                    }
                    if (classId != null && !classId.equals("")) {
                        pad.setiGradeid(Long.valueOf(gradeId));
                    }
                }
                int result = padMapper.updatePadBangdingByPrimaryKey(pad);
                if (result > 0) {
                    return ReturnJson.returnSuccessMap();
                }
            }
        } catch (Exception e) {
            padManager.deletePadBindingLog(bangdingId);
            LOGGER.info("操作失败！异常输出==" + e);
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getPadNumBySchool")
    public Object getPadNumBySchool(String schoolId) {
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            String countPad = padMapper.getPadNumBySchool(Long.valueOf(schoolId));
            map.put("countPad", countPad);
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getAuthBySchoolId")
    public Object getAuthBySchoolId(String schoolId) {
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        try {
            List<Padauthorize> pau = padauthorizeMapper.selectBySchoolId(Long.valueOf(schoolId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (pau != null && !pau.isEmpty()) {
                Map<String, Object> listMap = new HashMap<>();
                List<Map<String, Object>> mapList = new ArrayList<>();
                for (Padauthorize pa : pau
                        ) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("auzId", String.valueOf(pa.getiAuzid()));
                    if (pa.getdSdate() != null) {
                        map.put("sDate", sdf.format(pa.getdSdate()));
                    } else {
                        map.put("sDate", "");
                    }
                    if (pa.getcSerialno() != null) {
                        map.put("serialNo", pa.getcSerialno());
                    } else {
                        map.put("serialNo", "");
                    }
                    if (pa.getdEdate() != null) {
                        map.put("eDate", sdf.format(pa.getdEdate()));
                    } else {
                        map.put("eDate", "");
                    }
                    if (pa.getdSdate() != null && pa.getdEdate() != null) {
                        map.put("useTime", sdf.format(pa.getdSdate()) + " 至 " + sdf.format(pa.getdEdate()));
                    } else {
                        map.put("useTime", "暂无数据");
                    }
                    if (pa.getiTotal() != null) {
                        map.put("total", pa.getiTotal());
                    } else {
                        map.put("total", "");
                    }
                    if (pa.getiAcount() != null) {
                        map.put("acount", pa.getiAcount());
                    } else {
                        map.put("acount", "");
                    }
                    mapList.add(map);
                }
                listMap.put("list", mapList);
                return ReturnJson.returnSuccessMap(listMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnJson.returnFail("此学校授权信息有误，请联系管理人员。");
        }
        return ReturnJson.returnFail("暂无授权信息。");
    }

    /**
     * 根据schoolId获取学校安装应用的情况
     *
     * @param schoolId
     * @return
     */
    @RequestMapping("getAppInfoBySchool")
    public Object getAppInfoBySchool(String schoolId) {
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            List<PadInstallApp> list = padManager.getAppInfoBySchool(Long.valueOf(schoolId));
            if (list != null && !list.isEmpty()) {
                map.put("list", list);
            }
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }
}
