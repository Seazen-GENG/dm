package com.fuhui.controller.manager;

import com.fuhui.dao.twj.WjFileMapper;
import com.fuhui.dao.twj.WjUserFileMapper;
import com.fuhui.entity.twj.WjFile;
import com.fuhui.entity.twj.WjUserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpLoadManage {

    @Autowired
    private WjFileMapper wjFileMapper;

    @Autowired
    private WjUserFileMapper wjUserFileMapper;


    public void setFileToSql(WjFile wjFile) {
        try {
            wjFileMapper.insertSelective(wjFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFileToSqls(WjUserFile wjUserFile) {
        try {
            wjUserFileMapper.insertSelective(wjUserFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WjFile getHaveFile(String md5) {
        WjFile wjFile1 = wjFileMapper.selectFileByMd5(md5);
        return wjFile1;
    }


}
