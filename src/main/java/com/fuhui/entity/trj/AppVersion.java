package com.fuhui.entity.trj;

import java.io.Serializable;
import java.util.Date;

public class AppVersion implements Serializable {
    private Long iVersionid;

    private Long iAppid;

    private String cVersion;

    private String cVersionremark;

    private Integer iIscompel;

    private String cDownurl;

    private String tReleasetime;

    private String tCtime;

    private Long iCuserid;

    private String tRtime;

    private Long iRuserid;

    private String cRemark;

    private Integer iStatus;

    private String cVersionCode;

    private Integer isReview;

    private Integer isNew;

    private Long newVersionId;

    private AppRj appRj;

    private static final long serialVersionUID = 1L;

    public AppRj getAppRj() {
        return appRj;
    }

    public void setAppRj(AppRj appRj) {
        this.appRj = appRj;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public void setNewVersionId(Long newVersionId) {
        this.newVersionId = newVersionId;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public Long getNewVersionId() {
        return newVersionId;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getcVersionCode() {
        return cVersionCode;
    }

    public void setcVersionCode(String cVersionCode) {
        this.cVersionCode = cVersionCode;
    }

    public Long getiVersionid() {
        return iVersionid;
    }

    public void setiVersionid(Long iVersionid) {
        this.iVersionid = iVersionid;
    }

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
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

    public Integer getiIscompel() {
        return iIscompel;
    }

    public void setiIscompel(Integer iIscompel) {
        this.iIscompel = iIscompel;
    }

    public String getcDownurl() {
        return cDownurl;
    }

    public void setcDownurl(String cDownurl) {
        this.cDownurl = cDownurl == null ? null : cDownurl.trim();
    }

    public String gettReleasetime() {
        return tReleasetime;
    }

    public void settReleasetime(String tReleasetime) {
        this.tReleasetime = tReleasetime;
    }

    public String gettCtime() {
        return tCtime;
    }

    public void settCtime(String tCtime) {
        this.tCtime = tCtime;
    }

    public Long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(Long iCuserid) {
        this.iCuserid = iCuserid;
    }

    public String gettRtime() {
        return tRtime;
    }

    public void settRtime(String tRtime) {
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
        return "AppVersion{" +
                "iVersionid=" + iVersionid +
                ", iAppid=" + iAppid +
                ", cVersion='" + cVersion + '\'' +
                ", cVersionremark='" + cVersionremark + '\'' +
                ", iIscompel=" + iIscompel +
                ", cDownurl='" + cDownurl + '\'' +
                ", tReleasetime='" + tReleasetime + '\'' +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", tRtime='" + tRtime + '\'' +
                ", iRuserid=" + iRuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", cVersionCode='" + cVersionCode + '\'' +
                ", isReview=" + isReview +
                ", isNew=" + isNew +
                ", newVersionId=" + newVersionId +
                '}';
    }
}