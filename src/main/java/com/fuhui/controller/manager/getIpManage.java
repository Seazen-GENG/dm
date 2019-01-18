package com.fuhui.controller.manager;

import com.fuhui.util.HTTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class getIpManage {

    @Autowired
    private Param param;

    private final static Logger LOGGER = LoggerFactory.getLogger(getIpManage.class);

    public String getIp() {
        String url1 = param.getGetIp();
        LOGGER.info("请求外网");
        String jsonAdd = HTTPUtils.doPost(url1);
        LOGGER.info("请求外网success");
        String success = jsonAdd.substring(19);
        LOGGER.info("从外网获取的ip地址==" + success);
        String tempStr = success.substring(success.indexOf("{") + 1, success.lastIndexOf("}"));
        tempStr = "{" + tempStr + "}";
        LOGGER.info("组织json==" + tempStr);
        return tempStr;
    }

    public String getTrueIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
            LOGGER.info("ip1==" + ipAddress);
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
            LOGGER.info("ip2==" + ipAddress);
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            LOGGER.info("ip3==" + ipAddress);
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    LOGGER.info("ip4==" + inet);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
                LOGGER.info("ip5==" + ipAddress);
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                LOGGER.info("ip6==" + ipAddress);
            }
        }
        return ipAddress;
    }

}
