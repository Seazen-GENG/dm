package com.fuhui.entity.trj;

import java.io.Serializable;
import java.util.Date;

public class AppInstall implements Serializable {
    private Long iIdx;

    private String cMac;

    private Long iAppid;

    private Long iVersionid;

    private Date tFtime;

    private Date tUtime;

    private Integer iWay;

    private Integer iStatus;

    private Date tEtime;

    private Long iPadid;

    private Integer iIssys;

    private String pName;

    private String cAppname;

    private String cVersioncode;

    private String cVersion;

    private String allUsers;

    private String versionUsers;

    private static final long serialVersionUID = 1L;

    public void setAllUsers(String allUsers) {
        this.allUsers = allUsers;
    }

    public void setVersionUsers(String versionUsers) {
        this.versionUsers = versionUsers;
    }

    public String getAllUsers() {
        return allUsers;
    }

    public String getVersionUsers() {
        return versionUsers;
    }

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public String getcMac() {
        return cMac;
    }

    public void setcMac(String cMac) {
        this.cMac = cMac == null ? null : cMac.trim();
    }

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
    }

    public Long getiVersionid() {
        return iVersionid;
    }

    public void setiVersionid(Long iVersionid) {
        this.iVersionid = iVersionid;
    }

    public Date gettFtime() {
        return tFtime;
    }

    public void settFtime(Date tFtime) {
        this.tFtime = tFtime;
    }

    public Date gettUtime() {
        return tUtime;
    }

    public void settUtime(Date tUtime) {
        this.tUtime = tUtime;
    }

    public Integer getiWay() {
        return iWay;
    }

    public void setiWay(Integer iWay) {
        this.iWay = iWay;
    }

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public Date gettEtime() {
        return tEtime;
    }

    public void settEtime(Date tEtime) {
        this.tEtime = tEtime;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public Integer getiIssys() {
        return iIssys;
    }

    public void setiIssys(Integer iIssys) {
        this.iIssys = iIssys;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getcAppname() {
        return cAppname;
    }

    public void setcAppname(String cAppname) {
        this.cAppname = cAppname == null ? null : cAppname.trim();
    }

    public String getcVersioncode() {
        return cVersioncode;
    }

    public void setcVersioncode(String cVersioncode) {
        this.cVersioncode = cVersioncode == null ? null : cVersioncode.trim();
    }

    public String getcVersion() {
        return cVersion;
    }

    public void setcVersion(String cVersion) {
        this.cVersion = cVersion == null ? null : cVersion.trim();
    }

    @Override
    public String toString() {
        return "AppInstall{" +
                "iIdx=" + iIdx +
                ", cMac='" + cMac + '\'' +
                ", iAppid=" + iAppid +
                ", iVersionid=" + iVersionid +
                ", tFtime=" + tFtime +
                ", tUtime=" + tUtime +
                ", iWay=" + iWay +
                ", iStatus=" + iStatus +
                ", tEtime=" + tEtime +
                ", iPadid=" + iPadid +
                ", iIssys=" + iIssys +
                ", pName='" + pName + '\'' +
                ", cAppname='" + cAppname + '\'' +
                ", cVersioncode='" + cVersioncode + '\'' +
                ", cVersion='" + cVersion + '\'' +
                ", allUsers='" + allUsers + '\'' +
                ", versionUsers='" + versionUsers + '\'' +
                '}';
    }
}