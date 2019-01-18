package com.fuhui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class xmlUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(xmlUtil.class);

    //构建xml文件格式的参数
    private static String getXmlString(String auth_key, String mac_address, String device_type) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<ApplyAuth>\n");
        sb.append("<strAuthID>").append(auth_key).append("</strAuthID>\n");
        sb.append("<strMacAddr>").append(mac_address).append("</strMacAddr>\n");
        sb.append("<strSystemInfo>").append(device_type).append("</strSystemInfo>\n");
        sb.append("</ApplyAuth>");
        return sb.toString();
    }

    //发送前先保存xml格式文件
    private static void saveXmlFile(File file, String xmlText) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        BufferedWriter bw = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
            bw = new BufferedWriter(writer);
            bw.write(xmlText);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String doPost(String xmlInfo){
        try {
            LOGGER.info("请求授权！");
            String urlStr = "https://auth.fuhui.tech:7443/regmac.aspx";
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("x-API-Key", "7314BB96-8107-4B87-A68A-EC346832511E");
            con.setRequestProperty("User-Agent", "Fiddler");
            con.setRequestProperty("Content-Type", "image/gif");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("UTF-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String content = "";
            String track = "";
            while ((content = br.readLine()) != null) {
                track += content;
            }
            System.out.println(track);
            LOGGER.info("请求成功!");
            return track;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    public static String postXml(String auth_key, String mac_address) {
        try {
            String xmlStr = getXmlString(auth_key, mac_address, "Pad");
            Resource resource = new ClassPathResource("po.xml");
            File file = resource.getFile();
            String result = doPost(xmlStr);
            saveXmlFile(file, result);//将结果写入到文件
            LOGGER.info("保存成xml成功!");
            return result;
        }catch (Exception e){
            LOGGER.info("post请求失败!");
            e.printStackTrace();
        }
        return null;
    }

    //Pc端授权
    public static String postXmlPc(String auth_key, String mac_address) {
        try {
            String xmlStr = getXmlString(auth_key, mac_address, "PC");
            Resource resource = new ClassPathResource("po.xml");
            File file = resource.getFile();
            String result = doPost(xmlStr);
            saveXmlFile(file, result);//将结果写入到文件
            LOGGER.info("保存成xml成功!");
            return result;
        }catch (Exception e){
            LOGGER.info("post请求失败!");
            e.printStackTrace();
        }
        return null;
    }

}
