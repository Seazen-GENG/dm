package com.fuhui.entity.trj;

import java.io.Serializable;
import java.util.Date;

public class AppInstallLog implements Serializable {
    private Long iIdx;

    private String cMac;

    private Long iAppid;

    private Long iVersionid;

    private Integer iWay;

    private Integer iStatus;

    private Date tTime;

    private Date tCtime;

    private Long iPadid;

    private Integer iIssys;

    private String pName;

    private String cAppname;

    private String cVersion;

    private String cVersioncode;

    private static final long serialVersionUID = 1L;

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

    public String getcVersion() {
        return cVersion;
    }

    public void setcVersion(String cVersion) {
        this.cVersion = cVersion == null ? null : cVersion.trim();
    }

    public String getcVersioncode() {
        return cVersioncode;
    }

    public void setcVersioncode(String cVersioncode) {
        this.cVersioncode = cVersioncode == null ? null : cVersioncode.trim();
    }
}