package com.fuhui.entity.tjc;

import java.io.Serializable;
import java.util.Date;

public class Stage implements Serializable {
    private Long iStageid;

    private String cName;

    private Long iCuserid;

    private String tCtime;

    private Long iUuserid;

    private String tUtime;

    private String cRemark;

    private Byte iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiStageid() {
        return iStageid;
    }

    public void setiStageid(Long iStageid) {
        this.iStageid = iStageid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public Long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(Long iCuserid) {
        this.iCuserid = iCuserid;
    }

    public String gettCtime() {
        return tCtime;
    }

    public void settCtime(String tCtime) {
        this.tCtime = tCtime;
    }

    public Long getiUuserid() {
        return iUuserid;
    }

    public void setiUuserid(Long iUuserid) {
        this.iUuserid = iUuserid;
    }

    public String gettUtime() {
        return tUtime;
    }

    public void settUtime(String tUtime) {
        this.tUtime = tUtime;
    }

    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark == null ? null : cRemark.trim();
    }

    public Byte getiStatus() {
        return iStatus;
    }

    public void setiStatus(Byte iStatus) {
        this.iStatus = iStatus;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "iStageid=" + iStageid +
                ", cName='" + cName + '\'' +
                ", iCuserid=" + iCuserid +
                ", tCtime='" + tCtime + '\'' +
                ", iUuserid=" + iUuserid +
                ", tUtime='" + tUtime + '\'' +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                '}';
    }
}