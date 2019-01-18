package com.fuhui.controller.service;


import com.fuhui.util.HTTPUtils;
import com.fuhui.util.ReturnJson;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class test {


    @RequestMapping("getIp")
    public Object cas() throws Exception {
        String url1 = "http://pv.sohu.com/cityjson?ie=utf-8";
        String jsonAdd = HTTPUtils.doPost(url1);
        String success = jsonAdd.substring(19);
        String tempStr = success.substring(success.indexOf("{") + 1, success.lastIndexOf("}"));
        tempStr = "{" + tempStr + "}";
        JSONObject jsonObject = JSONObject.fromObject(tempStr);
        System.out.println("万网ip地址信息==" + jsonObject);
        String ip = jsonObject.getString("cip");
        String address = jsonObject.getString("cname");
        return jsonObject;
    }

    /*@RequestMapping(value = "upLoad/getFile", method = RequestMethod.POST)
    public Object things(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("path==" + file.toString());
        String fileName = file.getOriginalFilename();
       // System.out.println("Name==" + fileName);
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
       // System.out.println("fileType==" + fileType);
        File fileDir = new File("D:\\Upload");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        String path = fileDir.getAbsolutePath();
       // System.out.println("filepath==" + path);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", file.getOriginalFilename());
        result.put("status", 200);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.put("time", sdf.format(new Date()));
        file.transferTo(new File(fileDir.getAbsolutePath(), fileName));
        String uploadPath = "D:\\Upload\\" + file.getOriginalFilename();
        // path = uploadPath.replaceAll("\\\\", "/");
        File file1 = new File(uploadPath);
        try {
            // fastDFSTest.ConnectFDS();
            System.out.println("path==" + uploadPath + "**name==" + file.getOriginalFilename() + "**length==" + file1.length());
            //String[] files = fastDFSTest.uploadFile(file1, file.getOriginalFilename(), file1.length());
            //System.out.println("filePath==" + Arrays.asList(files));
            //fastDFSTest.getFileInfo(files[0], files[1]);
            //List up = Arrays.asList(files);
            //System.out.println("0=="+ up.get(0) + "******1==" + up.get(1));
            //result.put("upLoadPath",up.get(1));
           // fastDFSTest.deleteFile(files[0], files[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnJson.returnSuccessMap();
        }
        return ReturnJson.returnSuccessMap(result);
    }

    @RequestMapping("cluster/getUser")
    public Object getUser(){

        return "";
    }*/

}
