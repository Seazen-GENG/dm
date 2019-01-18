package com.fuhui.entity.twj;

import java.io.Serializable;
import java.util.Date;

public class WjFile implements Serializable {
    private Long iFileid;

    private String cType;

    private String cPath;

    private String cMd5;

    private Long iSize;

    private String cImgpath;

    private Date tCtime;

    private Integer iCplace;

    private String cRemark;

    private Integer iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiFileid() {
        return iFileid;
    }

    public void setiFileid(Long iFileid) {
        this.iFileid = iFileid;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
    }

    public String getcPath() {
        return cPath;
    }

    public void setcPath(String cPath) {
        this.cPath = cPath == null ? null : cPath.trim();
    }

    public String getcMd5() {
        return cMd5;
    }

    public void setcMd5(String cMd5) {
        this.cMd5 = cMd5 == null ? null : cMd5.trim();
    }

    public Long getiSize() {
        return iSize;
    }

    public void setiSize(Long iSize) {
        this.iSize = iSize;
    }

    public String getcImgpath() {
        return cImgpath;
    }

    public void setcImgpath(String cImgpath) {
        this.cImgpath = cImgpath == null ? null : cImgpath.trim();
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }

    public Integer getiCplace() {
        return iCplace;
    }

    public void setiCplace(Integer iCplace) {
        this.iCplace = iCplace;
    }

    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark == null ? null : cRemark.trim();
    }

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    @Override
    public String toString() {
        return "WjFile{" +
                "iFileid=" + iFileid +
                ", cType='" + cType + '\'' +
                ", cPath='" + cPath + '\'' +
                ", cMd5='" + cMd5 + '\'' +
                ", iSize=" + iSize +
                ", cImgpath='" + cImgpath + '\'' +
                ", tCtime=" + tCtime +
                ", iCplace=" + iCplace +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                '}';
    }
}