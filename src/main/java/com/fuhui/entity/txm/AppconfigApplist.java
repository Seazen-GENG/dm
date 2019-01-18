package com.fuhui.entity.txm;

import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.trj.AppVersion;
import com.fuhui.entity.txu.App;

import java.io.Serializable;
import java.util.Date;

public class AppconfigApplist implements Serializable {
    private Long iIdx;

    private Long iConfigid;

    private Long iAppid;

    private String tCtime;

    private Long iCuserid;

    private String isDet;

    private App app;

    private AppRj appRj;

    private AppVersion appVersion;

    private static final long serialVersionUID = 1L;

    public void setApp(App app) {
        this.app = app;
    }

    public void setAppRj(AppRj appRj) {
        this.appRj = appRj;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public App getApp() {
        return app;
    }

    public AppRj getAppRj() {
        return appRj;
    }

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public Long getiConfigid() {
        return iConfigid;
    }

    public void setiConfigid(Long iConfigid) {
        this.iConfigid = iConfigid;
    }

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
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

    public String getIsDet() {
        return isDet;
    }

    public void setIsDet(String isDet) {
        this.isDet = isDet;
    }

    @Override
    public String toString() {
        return "AppconfigApplist{" +
                "iIdx=" + iIdx +
                ", iConfigid=" + iConfigid +
                ", iAppid=" + iAppid +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", isDet='" + isDet + '\'' +
                ", app=" + app +
                ", appRj=" + appRj +
                ", appVersion=" + appVersion +
                '}';
    }
}