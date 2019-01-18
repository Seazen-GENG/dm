package com.fuhui.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(AddressUtils.class);

    /**
     * @param content 请求的参数 格式为：name=xxx&pwd=xxx
     *                服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用淘宝API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            LOGGER.info("(1) unicode转换成中文前的returnStr : " + returnStr);
            returnStr = decodeUnicode(returnStr);
            LOGGER.info("(2) unicode转换成中文后的returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return "0";//无效IP，局域网测试
            }
            return returnStr;
        }
        return null;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(5000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     * @author fanhui 2007-3-15
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public static String getTrueIpAddress(String ip){
        String address = "";
        String json_result = "";
        try {
            address = getAddresses("ip=" + ip, "utf-8");
            JSONObject json = JSONObject.fromObject(address);
            LOGGER.info("json数据： " + json);
            String country = JSONObject.fromObject(json.get("data")).get("country").toString();
            String region = JSONObject.fromObject(json.get("data")).get("region").toString();
            String city = JSONObject.fromObject(json.get("data")).get("city").toString();
            String county = JSONObject.fromObject(json.get("data")).get("county").toString();
            String isp = JSONObject.fromObject(json.get("data")).get("isp").toString();
            String area = JSONObject.fromObject(json.get("data")).get("area").toString();

            LOGGER.info("国家： " + country);
            LOGGER.info("地区： " + area);
            LOGGER.info("省份: " + region);
            LOGGER.info("城市： " + city);
            LOGGER.info("区/县： " + county);
            LOGGER.info("互联网服务提供商： " + isp);
            json_result = country + "/";
            json_result += region + "/";
            json_result += city + "/";
            json_result += county;
            LOGGER.info(json_result);
            return  json_result;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "未找到能够适配的地址!";
    }

    // 测试
    public static void main(String[] args) {
        AddressUtils addressUtils = new AddressUtils();
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        String ip = "10.14.86.57";
        String address = "";
        String json_result = "";
        try {
            address = addressUtils.getAddresses("ip=" + ip, "utf-8");
            JSONObject json = JSONObject.fromObject(address);
            LOGGER.info("json数据： " + json);
            String country = JSONObject.fromObject(json.get("data")).get("country").toString();
            String region = JSONObject.fromObject(json.get("data")).get("region").toString();
            String city = JSONObject.fromObject(json.get("data")).get("city").toString();
            String county = JSONObject.fromObject(json.get("data")).get("county").toString();
            String isp = JSONObject.fromObject(json.get("data")).get("isp").toString();
            String area = JSONObject.fromObject(json.get("data")).get("area").toString();
            LOGGER.info("国家： " + country);
            LOGGER.info("地区： " + area);
            LOGGER.info("省份: " + region);
            LOGGER.info("城市： " + city);
            LOGGER.info("区/县： " + county);
            LOGGER.info("互联网服务提供商： " + isp);
            json_result = country + "/";
            json_result += region + "/";
            json_result += city + "/";
            json_result += county;
            LOGGER.info(json_result);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
