package com.fuhui.entity.txu;

import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.AppVersion;

import java.io.Serializable;
import java.math.BigDecimal;

public class App implements Serializable {
    private Long iIdx;

    private Long iAppid;

    private Long iSchoolid;

    private Integer iType;

    private Long iVersionid;

    private String cDownurl;

    private Byte iIscompel;

    private BigDecimal nPrice;

    private String tCtime;

    private Long iCuserid;

    private String tUtime;

    private Long iUuserid;

    private Integer iStatus;

    private Integer iUway;

    private AppRj appRj;

    private AppVersion appVersion;

    private static final long serialVersionUID = 1L;

    private Integer allApp;

    private Integer sysCount;

    private Integer extCount;

    public Integer getAllApp() {
        return allApp;
    }

    public Integer getSysCount() {
        return sysCount;
    }

    public Integer getExtCount() {
        return extCount;
    }

    public void setAllApp(Integer allApp) {
        this.allApp = allApp;
    }

    public void setSysCount(Integer sysCount) {
        this.sysCount = sysCount;
    }

    public void setExtCount(Integer extCount) {
        this.extCount = extCount;
    }

    public AppRj getAppRj() {
        return appRj;
    }

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppRj(AppRj appRj) {
        this.appRj = appRj;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    public Long getiVersionid() {
        return iVersionid;
    }

    public void setiVersionid(Long iVersionid) {
        this.iVersionid = iVersionid;
    }

    public String getcDownurl() {
        return cDownurl;
    }

    public void setcDownurl(String cDownurl) {
        this.cDownurl = cDownurl == null ? null : cDownurl.trim();
    }

    public Byte getiIscompel() {
        return iIscompel;
    }

    public void setiIscompel(Byte iIscompel) {
        this.iIscompel = iIscompel;
    }

    public BigDecimal getnPrice() {
        return nPrice;
    }

    public void setnPrice(BigDecimal nPrice) {
        this.nPrice = nPrice;
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

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public Integer getiUway() {
        return iUway;
    }

    public void setiUway(Integer iUway) {
        this.iUway = iUway;
    }

    @Override
    public String toString() {
        return "App{" +
                "iIdx=" + iIdx +
                ", iAppid=" + iAppid +
                ", iSchoolid=" + iSchoolid +
                ", iType=" + iType +
                ", iVersionid=" + iVersionid +
                ", cDownurl='" + cDownurl + '\'' +
                ", iIscompel=" + iIscompel +
                ", nPrice=" + nPrice +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", tUtime='" + tUtime + '\'' +
                ", iUuserid=" + iUuserid +
                ", iStatus=" + iStatus +
                ", iUway=" + iUway +
                ", appRj=" + appRj +
                ", appVersion=" + appVersion +
                '}';
    }
}