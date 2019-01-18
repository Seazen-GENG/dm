package com.fuhui.mq;

import com.fuhui.dao.tpd.PadConfigLogMapper;
import com.fuhui.dao.tpd.PadSwitchLogMapper;
import com.fuhui.dao.trj.AppInstallLogMapper;
import com.fuhui.dao.trj.AppInstallMapper;
import com.fuhui.entity.model.AppInstallLogToic;
import com.fuhui.entity.tpd.PadConfigLog;
import com.fuhui.entity.tpd.PadSwitchLog;
import com.fuhui.entity.trj.AppInstall;
import com.fuhui.entity.trj.AppInstallLog;
import com.fuhui.util.DateFromat;
import com.fuhui.util.SnowFlake;
import net.sf.json.JSONObject;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;
@Component
public class ReceiveTopic {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReceiveTopic.class);

    @Autowired
    private AppInstallMapper appInstall;

    @Autowired
    private AppInstallLogMapper appInstallLogMapper;

    @Autowired
    private PadConfigLogMapper padConfigLogMapper;

    @Autowired
    private PadSwitchLogMapper padSwitchLogMapper;


    // pad配置日志
    @JmsListener(destination = "PadConfigLogTopic", containerFactory = "padTopicListener")
    public void receiveTopic(ActiveMQBytesMessage message) {
        LOGGER.info("pad配置日志");
        SnowFlake snowFlake = new SnowFlake(2, 3);
        if (null != message) {
            byte[] bytes = message.getContent().data;
            String content = new String(bytes);
            try {
                JSONObject jsonObject = JSONObject.fromObject(content);
                System.out.println("jsonArray==" + jsonObject);
                PadConfigLog padConfigLog = new PadConfigLog();
                {
                    padConfigLog.setiLogid(snowFlake.nextId());
                    if (!jsonObject.getString("config").equals("null")) {
                        padConfigLog.setcConfig(jsonObject.getString("config"));
                    }
                    if (!jsonObject.getString("padId").equals("null")) {
                        padConfigLog.setiPadid(Long.valueOf(jsonObject.getString("padId")));
                    }
                    if (!jsonObject.getString("configId").equals("null")) {
                        padConfigLog.setiConfigid(Long.valueOf(jsonObject.getString("configId")));
                    }
                    if (!jsonObject.getString("extend1").equals("null")) {
                        padConfigLog.setcExtend1(jsonObject.getString("extend1"));
                    }
                    padConfigLog.settCtime(new Date());
                    String time = jsonObject.getString("time");
                    if (!time.equals("null")) {
                        Date date1 = DateFromat.parse(time, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                        padConfigLog.settTime(date1);
                    }
                    if (!jsonObject.getString("type").equals("null")) {
                        padConfigLog.setiType(Integer.parseInt(jsonObject.getString("type")));
                    }
                }
                System.out.println("padConfigLog==" + padConfigLog);
                padConfigLogMapper.insertSelective(padConfigLog);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("存储失败!");
            }
        }
    }


    //app安装、卸载日志
    @JmsListener(destination = "AppInstallLogToic", containerFactory = "jmsTopicListener")
    public void receiveTopic2(ActiveMQBytesMessage message) {
        LOGGER.info("app安装、卸载日志");
        SnowFlake snowFlake = new SnowFlake(2, 3);
        if (null != message) {
            byte[] bytes = message.getContent().data;
            String content = new String(bytes);
            try {
                JSONObject jsonObject = JSONObject.fromObject(content);
                System.out.println("jsonArray(old)==" + jsonObject);
                /*String mac = jsonObject.getString("mac");
                String appId = jsonObject.getString("appId");
                String time = jsonObject.getString("time");
                String isSys = jsonObject.getString("isSys");
                String pName = jsonObject.getString("pName");
                String appName = jsonObject.getString("appName");
                String padId = jsonObject.getString("padId");
                String status = jsonObject.getString("status");
                String version = jsonObject.getString("version");
                String versionCode = jsonObject.getString("versionCode");
                String versionId = jsonObject.getString("versionId");
                String way = jsonObject.getString("way");*/
                AppInstallLogToic stu = (AppInstallLogToic) JSONObject.toBean(jsonObject, AppInstallLogToic.class);
                if (stu != null) {
                    AppInstall appInstall1 = new AppInstall();
                    {
                        if (stu.getMac() != null) {
                            appInstall1.setcMac(stu.getMac());
                        }
                        if (stu.getAppId() != null) {
                            appInstall1.setiAppid(Long.valueOf(stu.getAppId()));
                        }
                        if (stu.getIsSys() != null) {
                            appInstall1.setiIssys(Integer.parseInt(stu.getIsSys()));
                        }
                        if (stu.getAppName() != null) {
                            appInstall1.setcAppname(stu.getAppName());
                        }
                        if (stu.getVersion() != null) {
                            appInstall1.setcVersion(stu.getVersion());
                        }
                        if (stu.getVersionCode() != null) {
                            appInstall1.setcVersioncode(stu.getVersionCode());
                        }
                        if (stu.getVersionId() != null) {
                            appInstall1.setiVersionid(Long.valueOf(stu.getVersionId()));
                        }
                        if (stu.getPadId() != null) {
                            appInstall1.setiPadid(Long.valueOf(stu.getPadId()));
                        }
                        if (stu.getStatus() != null) {
                            appInstall1.setiStatus(Integer.parseInt(stu.getStatus()));
                        }
                        if (stu.getpName() != null) {
                            appInstall1.setpName(stu.getpName());
                        }
                        if (stu.getWay() != null) {
                            appInstall1.setiWay(Integer.parseInt(stu.getWay()));
                        }

                        if (stu.getTime() != null) {
                            Date date1 = DateFromat.parse(stu.getTime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                            appInstall1.settEtime(date1);
                        }
                        appInstall1.settUtime(new Date());
                    }
                    AppInstallLog appInstallLog = new AppInstallLog();
                    {
                        appInstallLog.setiIdx(snowFlake.nextId());
                        if (stu.getMac() != null) {
                            appInstallLog.setcMac(stu.getMac());
                        }
                        if (stu.getAppId() != null) {
                            appInstallLog.setiAppid(Long.valueOf(stu.getAppId()));
                        }
                        if (stu.getIsSys() != null) {
                            appInstallLog.setiIssys(Integer.parseInt(stu.getIsSys()));
                        }
                        if (stu.getAppName() != null) {
                            appInstallLog.setcAppname(stu.getAppName());
                        }
                        if (stu.getVersion() != null) {
                            appInstallLog.setcVersion(stu.getVersion());
                        }
                        if (stu.getVersionCode() != null) {
                            appInstallLog.setcVersioncode(stu.getVersionCode());
                        }
                        if (stu.getVersionId() != null) {
                            appInstallLog.setiVersionid(Long.valueOf(stu.getVersionId()));
                        }
                        if (stu.getPadId() != null) {
                            appInstallLog.setiPadid(Long.valueOf(stu.getPadId()));
                        }
                        if (stu.getStatus() != null) {
                            appInstallLog.setiStatus(Integer.parseInt(stu.getStatus()));
                        }
                        if (stu.getpName() != null) {
                            appInstallLog.setpName(stu.getpName());
                        }
                        if (stu.getWay() != null) {
                            appInstallLog.setiWay(Integer.parseInt(stu.getWay()));
                        }

                        if (stu.getTime() != null) {
                            Date date1 = DateFromat.parse(stu.getTime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                            appInstallLog.settTime(date1);
                        }
                        appInstallLog.settCtime(new Date());
                    }
                    AppInstall appInstalls = null;
                    if (stu.getAppId() == null) {
                        appInstalls = appInstall.selectByMac(stu.getMac(), Long.valueOf("-1"), stu.getpName());
                    } else {
                        appInstalls = appInstall.selectByMac(stu.getMac(), Long.valueOf(stu.getAppId()), null);
                    }
                    LOGGER.info("首次安装时间==" + stu.getTime());
                    Date date = DateFromat.parse(stu.getTime(), "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    if (appInstalls == null) {
                        appInstall1.setiIdx(snowFlake.nextId());
                        appInstall1.settFtime(date);
                        System.out.println("success**" + appInstall1);
                        int k = appInstall.insertSelective(appInstall1);
                        if (k > 0) {
                            appInstallLog.settTime(date);
                            appInstallLogMapper.insertSelective(appInstallLog);
                        }
                    } else {
                        appInstall1.setiIdx(appInstalls.getiIdx());
                        int jg = appInstall.updateByPrimaryKeySelective(appInstall1);
                        if (jg > 0) {
                            appInstallLog.settTime(date);
                            appInstallLogMapper.insertSelective(appInstallLog);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("转换失败");
            }
        }
    }

    @JmsListener(destination = "mvp.queue", containerFactory = "jmsListenerContainerQueue")
    public void reviceQueue(String text) {
        System.out.println("Queue Consumer:" + text);
    }


    //pad开关机记录
    @JmsListener(destination = "PadSwitchLogTopic", containerFactory = "PadSwitchTopicListener")
    public void receiveTopic3(ActiveMQBytesMessage message) {
        LOGGER.info("开关机记录");
        SnowFlake snowFlake = new SnowFlake(2, 3);
        if (null != message) {
            byte[] bytes = message.getContent().data;
            String content = new String(bytes);
            try {
                JSONObject jsonObject = JSONObject.fromObject(content);
                System.out.println("jsonArray==" + jsonObject);
                PadSwitchLog log = new PadSwitchLog();
                log.setiLogid(snowFlake.nextId());
                if (!jsonObject.getString("padId").equals("null")) {
                    log.setiPadid(Long.valueOf(jsonObject.getString("padId")));
                }
                if (!jsonObject.getString("type").equals("null")) {
                    log.setiType(Integer.parseInt(jsonObject.getString("type")));
                }
                String time = jsonObject.getString("time");
                Date date = DateFromat.parse(time, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                log.settTime(date);
                log.settCtime(new Date());
                padSwitchLogMapper.insertSelective(log);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("存储失败!");
            }
        }
    }
}
