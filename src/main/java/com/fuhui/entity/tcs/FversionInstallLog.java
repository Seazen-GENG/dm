package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class FversionInstallLog implements Serializable {
    private Long iLogid;

    private Long iPadid;

    private String cMac;

    private Long iFdeviceid;

    private Long iFversionid;

    private String cVersion;

    private Byte iWay;

    private Byte iStatus;

    private Date tTime;

    private Date tCtime;

    private String schoolName;

    private static final long serialVersionUID = 1L;

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Long getiLogid() {
        return iLogid;
    }

    public void setiLogid(Long iLogid) {
        this.iLogid = iLogid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public String getcMac() {
        return cMac;
    }

    public void setcMac(String cMac) {
        this.cMac = cMac == null ? null : cMac.trim();
    }

    public Long getiFdeviceid() {
        return iFdeviceid;
    }

    public void setiFdeviceid(Long iFdeviceid) {
        this.iFdeviceid = iFdeviceid;
    }

    public Long getiFversionid() {
        return iFversionid;
    }

    public void setiFversionid(Long iFversionid) {
        this.iFversionid = iFversionid;
    }

    public String getcVersion() {
        return cVersion;
    }

    public void setcVersion(String cVersion) {
        this.cVersion = cVersion == null ? null : cVersion.trim();
    }

    public Byte getiWay() {
        return iWay;
    }

    public void setiWay(Byte iWay) {
        this.iWay = iWay;
    }

    public Byte getiStatus() {
        return iStatus;
    }

    public void setiStatus(Byte iStatus) {
        this.iStatus = iStatus;
    }

    public Date gettTime() {
        return tTime;
    }

    public void settTime(Date tTime) {
        this.tTime = tTime;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }
}