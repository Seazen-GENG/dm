package com.fuhui.controller.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import com.fuhui.entity.model.Plupload;
import com.fuhui.util.PluploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Service
public class uploadAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(uploadAction.class);
    /**
     * 上传处理方法
     */
    /*@RequestMapping(value = "/plupload/upload", method = RequestMethod.POST)
    public Object upload(Plupload plupload, HttpServletRequest request) {
        plupload.setRequest(request);
        //文件存储路径
        File dir = new File("/platform/tomcat/uploadFile/");
        LOGGER.info(dir.getPath());
        try {
            //上传文件
            PluploadUtil.upload(plupload, dir);
            //判断文件是否上传成功（被分成块的文件是否全部上传完成）
            if (PluploadUtil.isUploadFinish(plupload)) {
                LOGGER.info("----" + PluploadUtil.successFileName + "----");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PluploadUtil.successFileName;
    }*/

    public String setUpload(Plupload plupload, HttpServletRequest request){
        plupload.setRequest(request);
        //文件存储路径
        File dir = new File("/platform/tomcat/logs/");
       // LOGGER.info(dir.getPath());
        try {
            //上传文件
            PluploadUtil.upload(plupload, dir);
            //判断文件是否上传成功（被分成块的文件是否全部上传完成）
            if (PluploadUtil.isUploadFinish(plupload)) {
                LOGGER.info("----" + PluploadUtil.successFileName + "----");
            }
        } catch (IllegalStateException e) {
           // e.printStackTrace();
        } catch (IOException e) {
           // e.printStackTrace();
        }
        return PluploadUtil.successFileName;
    }
}
