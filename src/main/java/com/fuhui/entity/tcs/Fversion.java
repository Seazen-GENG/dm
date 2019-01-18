package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class Fversion implements Serializable {
    private Long iFversionid;

    private Long iFdeviceid;

    private Long iFactoryid;

    private String cVersion;

    private String cVersionremark;

    private String cDownurl;

    private Date tReleasetime;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private Integer iIsreview;

    private Date tRtime;

    private Long iRuserid;

    private String cRemark;

    private Integer iStatus;

    private static final long serialVersionUID = 1L;

    private Long iUFId;

    private String cMD5;

    private Long schoolId;

    private String cVersionCode;

    public String getcVersionCode() {
        return cVersionCode;
    }

    public void setcVersionCode(String cVersionCode) {
        this.cVersionCode = cVersionCode;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public void setiUFId(Long iUFId) {
        this.iUFId = iUFId;
    }

    public void setcMD5(String cMD5) {
        this.cMD5 = cMD5;
    }

    public Long getiUFId() {
        return iUFId;
    }

    public String getcMD5() {
        return cMD5;
    }

    public Long getiFversionid() {
        return iFversionid;
    }

    public void setiFversionid(Long iFversionid) {
        this.iFversionid = iFversionid;
    }

    public Long getiFdeviceid() {
        return iFdeviceid;
    }

    public void setiFdeviceid(Long iFdeviceid) {
        this.iFdeviceid = iFdeviceid;
    }

    public Long getiFactoryid() {
        return iFactoryid;
    }

    public void setiFactoryid(Long iFactoryid) {
        this.iFactoryid = iFactoryid;
    }

    public String getcVersion() {
        return cVersion;
    }

    public void setcVersion(String cVersion) {
        this.cVersion = cVersion == null ? null : cVersion.trim();
    }

    public String getcVersionremark() {
        return cVersionremark;
    }

    public void setcVersionremark(String cVersionremark) {
        this.cVersionremark = cVersionremark == null ? null : cVersionremark.trim();
    }

    public String getcDownurl() {
        return cDownurl;
    }

    public void setcDownurl(String cDownurl) {
        this.cDownurl = cDownurl == null ? null : cDownurl.trim();
    }

    public Date gettReleasetime() {
        return tReleasetime;
    }

    public void settReleasetime(Date tReleasetime) {
        this.tReleasetime = tReleasetime;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }

    public Long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(Long iCuserid) {
        this.iCuserid = iCuserid;
    }

    public Date gettUtime() {
        return tUtime;
    }

    public void settUtime(Date tUtime) {
        this.tUtime = tUtime;
    }

    public Long getiUuserid() {
        return iUuserid;
    }

    public void setiUuserid(Long iUuserid) {
        this.iUuserid = iUuserid;
    }

    public Integer getiIsreview() {
        return iIsreview;
    }

    public void setiIsreview(Integer iIsreview) {
        this.iIsreview = iIsreview;
    }

    public Date gettRtime() {
        return tRtime;
    }

    public void settRtime(Date tRtime) {
        this.tRtime = tRtime;
    }

    public Long getiRuserid() {
        return iRuserid;
    }

    public void setiRuserid(Long iRuserid) {
        this.iRuserid = iRuserid;
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
        return "Fversion{" +
                "iFversionid=" + iFversionid +
                ", iFdeviceid=" + iFdeviceid +
                ", iFactoryid=" + iFactoryid +
                ", cVersion='" + cVersion + '\'' +
                ", cVersionremark='" + cVersionremark + '\'' +
                ", cDownurl='" + cDownurl + '\'' +
                ", tReleasetime=" + tReleasetime +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", iIsreview=" + iIsreview +
                ", tRtime=" + tRtime +
                ", iRuserid=" + iRuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", iUFId=" + iUFId +
                ", cMD5='" + cMD5 + '\'' +
                ", schoolId=" + schoolId +
                ", cVersionCode='" + cVersionCode + '\'' +
                '}';
    }
}