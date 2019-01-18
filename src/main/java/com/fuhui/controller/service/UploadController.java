package com.fuhui.controller.service;


import com.fuhui.controller.manager.UpLoadManage;
import com.fuhui.entity.model.Plupload;
import com.fuhui.entity.twj.WjFile;
import com.fuhui.entity.twj.WjUserFile;
import com.fuhui.fastdfs.FastDFSClient;
import com.fuhui.fastdfs.FastDFSFile;
import com.fuhui.util.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.csource.fastdfs.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UpLoadManage upLoadManage;

    @Autowired
    private uploadAction uploadAction;

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!", e);
        }
        if (fileAbsolutePath == null) {
            logger.error("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }

    @RequestMapping("upload/getFile")
    public Object getFile(String fileName) {
        try {
            String file = "M00/00/00/" + fileName;
            System.out.println("pathName=" + file);
            FileInfo fileInfo = FastDFSClient.getFile("uatfiles", file);
            System.out.println("对象==" + fileInfo);
            if (fileInfo != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("filePath", file);
                map.put("address", fileInfo.getSourceIpAddr());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("createTime", sdf.format(fileInfo.getCreateTimestamp()));
                map.put("size", fileInfo.getFileSize());
                map.put("option", "query");
                return ReturnJson.returnSuccessMap(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("upload/delete")
    public Object delete(String fileName) {
        try {
            String file = "M00/00/00/" + fileName;
            System.out.println("pathName=" + file);
            FastDFSClient.deleteFile("uatfiles", file);
            Map<String, Object> map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("option", "delete");
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    /****
     * 上传APK
     * @param file
     * @return
     */
    @RequestMapping("file/upload")
    public Object FileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ReturnJson.returnFail("Please select a file to upload");
        }
        File f = null;
        String fileName = "";
        String md5 = "";
        String path = "";
        try {
            fileName = file.getOriginalFilename();
            int start = fileName.lastIndexOf(".");
            String houZhui = fileName.substring(start + 1);
            System.out.println("houzhui==" + houZhui);
            /*if (!houZhui.equals("apk")) {
                return ReturnJson.returnFail("请选择后缀为apk的文件");
            }*/
            InputStream ins = file.getInputStream();
            f = new File(file.getOriginalFilename());
            inputStreamToFile(ins, f);
            File finalFile = new File("/platform/tomcat/logs/" + fileName);
            md5 = MD5.getFileMD5String(finalFile);
            logger.info("apk_md5加密后的数据==" + md5);
            ///存储文件记录
            WjFile wjFile1 = upLoadManage.getHaveFile(md5);
            if (wjFile1 != null) {
                Map<String, Object> map = new HashMap<>();
                logger.info("file==" + f.getPath());
                //解析apk文件
                if (!houZhui.equals("apk")) {
                    map.put("package", "");
                    map.put("versionName", "");
                    map.put("versionCode", "");
                }else {
                    Map<String, Object> mapApk = ReadUtil.readAPK("/platform/tomcat/logs/" + file.getOriginalFilename());
                    logger.info("fileMap==" + mapApk);
                    map.put("package", mapApk.get("package"));
                    map.put("versionName", mapApk.get("versionName"));
                    map.put("versionCode", mapApk.get("versionCode"));
                }
                map.put("fileName", fileName);
                map.put("filePath", wjFile1.getcPath());
                map.put("option", "Upload");
                return ReturnJson.returnSuccessMap(map);
            } else {
                path = saveFile(file);
                WjFile wjFile = new WjFile();
                SnowFlake snowFlake = new SnowFlake(2, 5);
                Long fileId = snowFlake.nextId();
                logger.info("apk_fileId==" + fileId);
                wjFile.setiFileid(fileId);
                wjFile.setcMd5(md5);
                wjFile.setcType(houZhui);
                wjFile.setcPath(path);
                wjFile.setiCplace(1);
                wjFile.setiStatus(1);
                wjFile.settCtime(new Date());
                wjFile.setiSize(file.getSize() / 1024);//单位KB
                logger.info("apk_obj==" + wjFile);
                upLoadManage.setFileToSql(wjFile);
                WjUserFile wjUserFile = new WjUserFile();
                wjUserFile.setiUfid(snowFlake.nextId());
                wjUserFile.setiFileid(fileId);
                wjUserFile.setcName(fileName);
                wjUserFile.setcType(houZhui);
                wjUserFile.settCtime(new Date());
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                wjUserFile.setiCuserid(Long.valueOf(userId));
                wjUserFile.setiStatus(1);
                wjUserFile.setiCplace(1);
                upLoadManage.setFileToSqls(wjUserFile);
            }
            Map<String, Object> map = new HashMap<>();
            logger.info("file==" + f.getPath());
            if (!houZhui.equals("apk")) {
                map.put("package", "");
                map.put("versionName", "");
                map.put("versionCode", "");
            }else {
                Map<String, Object> mapApk = ReadUtil.readAPK("/platform/tomcat/logs/" + file.getOriginalFilename());
                logger.info("fileMap==" + mapApk);
                map.put("package", mapApk.get("package"));
                map.put("versionName", mapApk.get("versionName"));
                map.put("versionCode", mapApk.get("versionCode"));
            }
            map.put("fileName", fileName);
            map.put("filePath", path);
            map.put("option", "Upload");
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            logger.error("upload file failed", e);
        } finally {
            File del = new File("/platform/tomcat/logs/" + fileName);
            del.delete();
        }
        return ReturnJson.returnFail();
    }

    /***
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("zip/upload")
    public Object zipFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ReturnJson.returnFail("Please select a file to upload");
        }
        File files = null;
        String fileName = "";
        String md5 = "";
        String path = "";
        try {
            fileName = file.getOriginalFilename();
            int start = fileName.lastIndexOf(".");
            String houZhui = fileName.substring(start + 1);
            System.out.println("houzhui==" + houZhui);
            if (!houZhui.equals("zip")) {
                return ReturnJson.returnFail("请选择后缀为zip的文件");
            }
            InputStream ins = file.getInputStream();
            files = new File(file.getOriginalFilename());
            inputStreamToFile(ins, files);
            File finalFile = new File("/platform/tomcat/logs/" + fileName);
            md5 = MD5.getFileMD5String(finalFile);
            logger.info("md5加密后的数据==" + md5);
            WjFile wjFile1 = upLoadManage.getHaveFile(md5);
            if (wjFile1 != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("fileName", fileName);
                map.put("filePath", wjFile1.getcPath());
                map.put("option", "Upload");
                return ReturnJson.returnSuccessMap(map);
            } else {
                path = saveFile(file);
                WjFile wjFile = new WjFile();
                SnowFlake snowFlake = new SnowFlake(2, 5);
                Long fileId = snowFlake.nextId();
                logger.info("apk_fileId==" + fileId);
                wjFile.setiFileid(fileId);
                wjFile.setcMd5(md5);
                wjFile.setcType(houZhui);
                wjFile.setcPath(path);
                wjFile.setiCplace(1);
                wjFile.setiStatus(1);
                wjFile.settCtime(new Date());
                wjFile.setiSize(file.getSize() / 1024);//单位KB
                logger.info("apk_obj==" + wjFile);
                upLoadManage.setFileToSql(wjFile);
                WjUserFile wjUserFile = new WjUserFile();
                wjUserFile.setiUfid(snowFlake.nextId());
                wjUserFile.setiFileid(fileId);
                wjUserFile.setcName(fileName);
                wjUserFile.setcType(houZhui);
                wjUserFile.settCtime(new Date());
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                wjUserFile.setiCuserid(Long.valueOf(userId));
                wjUserFile.setiStatus(1);
                wjUserFile.setiCplace(1);
                upLoadManage.setFileToSqls(wjUserFile);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("filePath", path);
            map.put("md5", md5);
            map.put("option", "Upload");
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
            logger.error("upload file failed", e);
        } finally {
            File del = new File("/platform/tomcat/logs/" + fileName);
            del.delete();
        }
        return ReturnJson.returnFail();
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream("/platform/tomcat/logs/" + file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/file/zip", method = RequestMethod.POST)
    public Object upload(Plupload plupload, HttpServletRequest request) {
        File file = null;
        try {
            String name = uploadAction.setUpload(plupload, request);
            file = new File("/platform/tomcat/logs/" + name);
            String md5 = MD5.getFileMD5String(file);
            logger.info("md5加密后的数据==" + md5);
            WjFile wjFile1 = upLoadManage.getHaveFile(md5);
            String path = "";
            if (wjFile1 != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("md5", md5);
                map.put("fileName", name);
                map.put("filePath", wjFile1.getcPath());
                map.put("option", "Upload");
                return ReturnJson.returnSuccessMap(map);
            } else {
                File pdfFile = new File("/platform/tomcat/logs/" + name);
                FileInputStream fileInputStream = new FileInputStream(pdfFile);
                MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                path = saveFile(multipartFile);
                WjFile wjFile = new WjFile();
                SnowFlake snowFlake = new SnowFlake(2, 5);
                Long fileId = snowFlake.nextId();
                logger.info("apk_fileId==" + fileId);
                wjFile.setiFileid(fileId);
                wjFile.setcMd5(md5);
                wjFile.setcType("zip");
                wjFile.setcPath(path);
                wjFile.setiCplace(1);
                wjFile.setiStatus(1);
                wjFile.settCtime(new Date());
                wjFile.setiSize(file.length() / 1024);//单位KB
                logger.info("apk_obj==" + wjFile);
                upLoadManage.setFileToSql(wjFile);
                WjUserFile wjUserFile = new WjUserFile();
                wjUserFile.setiUfid(snowFlake.nextId());
                wjUserFile.setiFileid(fileId);
                wjUserFile.setcName(name);
                wjUserFile.setcType("zip");
                wjUserFile.settCtime(new Date());
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                wjUserFile.setiCuserid(Long.valueOf(userId));
                wjUserFile.setiStatus(1);
                wjUserFile.setiCplace(1);
                upLoadManage.setFileToSqls(wjUserFile);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("fileName", name);
            map.put("filePath", path);
            map.put("md5", md5);
            map.put("option", "Upload");
            return ReturnJson.returnSuccessMap(map);
        } catch (Exception e) {
           // e.printStackTrace();
        }finally {
            file.delete();
        }
        return ReturnJson.returnFail();
    }

}