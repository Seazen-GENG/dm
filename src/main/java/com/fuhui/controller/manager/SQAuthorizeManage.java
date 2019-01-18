package com.fuhui.controller.manager;

import com.fuhui.util.xmlUtil;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.Element;;
import java.io.*;
import java.util.Map;

@Service
public class SQAuthorizeManage {

    private final static Logger LOGGER = LoggerFactory.getLogger(SQAuthorizeManage.class);

    public Map<String, Object> authorizePad(String padMac, String serialNo) {
        LOGGER.info("授权start==发出授权请求");
        Map<String, Object> map = new HashMap<>();
        String strXML = xmlUtil.postXml(serialNo, padMac);
        LOGGER.info("接收授权result==" + strXML);
        if (strXML != null) {
            try {
                LOGGER.info("写入授权信息");
                String filePath = new ClassPathResource("po.xml").getFile().getAbsolutePath();
                LOGGER.info("授权信息po.xml==" + filePath);
                File readerXml = new File(filePath);
                /*Resource resource = new ClassPathResource("po.xml");
                File inputXml = resource.getFile();*/
                // 创建saxReader对象
                SAXReader reader = new SAXReader();
                LOGGER.info("写入授权信息success");
                // 通过read方法读取一个文件 转换成Document对象
                Document document = reader.read(readerXml);
                //获取根节点元素对象
                Element node = document.getRootElement();
                map = elementMethod(node);
                LOGGER.info("读取授权信息success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * Pc端授权
     * @param padMac
     * @param serialNo
     * @return
     */
    public Map<String, Object> authorizePc(String padMac, String serialNo) {
        LOGGER.info("授权start==发出授权请求");
        Map<String, Object> map = new HashMap<>();
        String strXML = xmlUtil.postXmlPc(serialNo, padMac);
        LOGGER.info("接收授权result==" + strXML);
        if (strXML != null) {
            try {
                LOGGER.info("写入授权信息");
                String filePath = new ClassPathResource("po.xml").getFile().getAbsolutePath();
                LOGGER.info("授权信息po.xml==" + filePath);
                File readerXml = new File(filePath);
                /*Resource resource = new ClassPathResource("po.xml");
                File inputXml = resource.getFile();*/
                // 创建saxReader对象
                SAXReader reader = new SAXReader();
                LOGGER.info("写入授权信息success");
                // 通过read方法读取一个文件 转换成Document对象
                Document document = reader.read(readerXml);
                //获取根节点元素对象
                Element node = document.getRootElement();
                map = elementMethod(node);
                LOGGER.info("读取授权信息success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public Map<String, Object> elementMethod(Element node) {
        // 获取node节点中，子节点的元素名称为supercars的元素节点。
        Element AuthResult = node.element("AuthResult");
        System.out.println("AuthResult==" + AuthResult.getText());
        Element AuthTimes = node.element("AuthTimes");
        Element AuthValidDate = node.element("AuthValidDate");
        System.out.println("AuthTimes==" + AuthTimes.getText() + "***AuthValidDate**" + AuthValidDate.getText());
        Map<String, Object> map = new HashMap<>();
        map.put("AuthResult", AuthResult.getText());
        map.put("AuthTimes", AuthTimes.getText());
        map.put("AuthValidDate", AuthValidDate.getText());
        return map;
    }


}
