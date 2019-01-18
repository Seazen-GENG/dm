package com.fuhui.entity.txu;

import com.fuhui.entity.trj.AppVersion;

import java.io.Serializable;
import java.util.Date;

public class School implements Serializable {
    private Long iSchoolid;

    private String cName;

    private String cCode;

    private String cAddress;

    private Long iRegionid;

    private Long iRegionpid;

    private Long iRegioncid;

    private Long iCalendarid;

    private String tCtime;

    private Long iCuserid;

    private String tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private AppVersion appVersion;

    private String version;

    private static final long serialVersionUID = 1L;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode == null ? null : cCode.trim();
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress == null ? null : cAddress.trim();
    }

    public Long getiRegionid() {
        return iRegionid;
    }

    public void setiRegionid(Long iRegionid) {
        this.iRegionid = iRegionid;
    }

    public Long getiRegionpid() {
        return iRegionpid;
    }

    public void setiRegionpid(Long iRegionpid) {
        this.iRegionpid = iRegionpid;
    }

    public Long getiRegioncid() {
        return iRegioncid;
    }

    public void setiRegioncid(Long iRegioncid) {
        this.iRegioncid = iRegioncid;
    }

    public Long getiCalendarid() {
        return iCalendarid;
    }

    public void setiCalendarid(Long iCalendarid) {
        this.iCalendarid = iCalendarid;
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

    public String gettUtime() {
        return tUtime;
    }

    public void settUtime(String tUtime) {
        this.tUtime = tUtime;
    }

    public Long getiUuserid() {
        return iUuserid;
    }

    public void setiUuserid(Long iUuserid) {
        this.iUuserid = iUuserid;
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
        return "School{" +
                "iSchoolid=" + iSchoolid +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", cAddress='" + cAddress + '\'' +
                ", iRegionid=" + iRegionid +
                ", iRegionpid=" + iRegionpid +
                ", iRegioncid=" + iRegioncid +
                ", iCalendarid=" + iCalendarid +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", tUtime='" + tUtime + '\'' +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", appVersion=" + appVersion +
                ", version='" + version + '\'' +
                '}';
    }
}