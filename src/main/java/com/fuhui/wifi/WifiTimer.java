package com.fuhui.wifi;

import com.fuhui.dao.twf.WfLogMapper;
import com.fuhui.entity.twf.WfLog;
import com.fuhui.util.DateFromat;
import com.fuhui.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Locale;

@Configuration          //证明这个类是一个配置文件
@EnableScheduling       //打开quartz定时器总开关
public class WifiTimer {

    public static final int MAX_MSG_LEN = 2600; //

    @Autowired
    private WfLogMapper wfLogMapper;

    @Value("${fuhui.wifi.prot}")
    private Integer prot;


    //@Scheduled(cron = "0/10 * * * * *")
    public void start() throws Exception {
        SnowFlake snowFlake = new SnowFlake(2,3);
        try {
            DatagramSocket udp = new DatagramSocket(prot);
            DatagramPacket dPacket;
            byte[] echo = new byte[1];
            echo[0] = (byte) 1;
            while (true) {
                dPacket = new DatagramPacket(new byte[MAX_MSG_LEN], MAX_MSG_LEN);
                udp.receive(dPacket);
                String result = new String(dPacket.getData(), 0, dPacket.getLength());
                System.out.println(result);
                String wifiMac = result.substring(0, 12);
                String devMac = result.substring(13, 25);
                String RSSI = result.substring(26, 29);
                String type = result.substring(30, 32);
                String time = result.substring(33, result.length());
                WfLog wfLog = new WfLog();
                wfLog.setiLogid(snowFlake.nextId());
                wfLog.setcDmac(wifiMac);
                wfLog.setcSmac(devMac);
                wfLog.setiStrong(Integer.parseInt(RSSI));
                wfLog.settCtime(new Date());
                Date date = DateFromat.parse(time, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                wfLog.settTime(date);
                wfLog.setcType(type);
                System.out.println("paramWif==" + wfLog.toString());
                wfLogMapper.insertSelective(wfLog);
                InetAddress addr = dPacket.getAddress();
                dPacket = new DatagramPacket(echo, echo.length);
                dPacket.setAddress(addr);
                udp.send(dPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
