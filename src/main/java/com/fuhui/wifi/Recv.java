package com.fuhui.wifi;


import com.fuhui.dao.twf.WfLogMapper;
import com.fuhui.entity.twf.WfLog;
import com.fuhui.util.DateFromat;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Recv {

    @Autowired
    private WfLogMapper wfLogMapper;

    public static final int DEFAULT_PORT = 9900;//
    public static final int MAX_MSG_LEN = 2600; //

    public static ExecutorService dataHandlePool = Executors
            .newFixedThreadPool(64);


    public static void start(int port) throws Exception {
        try {
            DatagramSocket udp = new DatagramSocket(port);
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
                /*Map<String, Object> map = new HashMap<>();
                map.put("RSSI", RSSI);
                map.put("type", type);
                map.put("time", time);
                map.put("wifiMac", wifiMac);
                map.put("devMac", devMac);*/
                WfLog wfLog = new WfLog();
                wfLog.setcDmac(wifiMac);
                wfLog.setcSmac(devMac);
                wfLog.setiStrong(Integer.parseInt(RSSI));
                wfLog.settCtime(new Date());
                Date date = DateFromat.parse(time, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                wfLog.settTime(date);
                wfLog.setcType(type);
                System.out.println("paramWif==" + wfLog.toString());
                //System.out.println("time==" + time);
                InetAddress addr = dPacket.getAddress();
                dPacket = new DatagramPacket(echo, echo.length);
                dPacket.setAddress(addr);
                udp.send(dPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            if (args != null && args.length == 1) {
                start(Integer.parseInt(args[0]));
            } else {
                start(DEFAULT_PORT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
