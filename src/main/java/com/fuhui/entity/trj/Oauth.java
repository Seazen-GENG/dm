package com.fuhui.entity.trj;

import java.io.Serializable;
import java.util.Date;

public class Oauth implements Serializable {
    private Long iAppid;

    private String cKey;

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

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
    }

    public String getcKey() {
        return cKey;
    }

    public void setcKey(String cKey) {
        this.cKey = cKey == null ? null : cKey.trim();
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
        return "Oauth{" +
                "iAppid=" + iAppid +
                ", cKey='" + cKey + '\'' +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", iIsreview=" + iIsreview +
                ", tRtime=" + tRtime +
                ", iRuserid=" + iRuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                '}';
    }
}