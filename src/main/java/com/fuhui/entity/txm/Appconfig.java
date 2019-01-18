package com.fuhui.entity.txm;

import com.fuhui.entity.trj.AppRj;
import com.fuhui.entity.txu.App;

import java.io.Serializable;
import java.util.List;

public class Appconfig implements Serializable {
    private long iConfigid;

    private String cName;

    private long iSchoolid;

    private Integer iRange;

    private String cRangeids;

    private String tCtime;

    private long iCuserid;

    private String tUtime;

    private long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private List<AppconfigApplist> applist;

    private AppConfigconPadList appConfigconPadList;

    private App app;

    private AppRj appRj;

    private static final long serialVersionUID = 1L;

    public AppRj getAppRj() {
        return appRj;
    }

    public void setAppRj(AppRj appRj) {
        this.appRj = appRj;
    }

    public AppConfigconPadList getAppConfigconPadList() {
        return appConfigconPadList;
    }

    public void setAppConfigconPadList(AppConfigconPadList appConfigconPadList) {
        this.appConfigconPadList = appConfigconPadList;
    }

    public List<AppconfigApplist> getApplist() {
        return applist;
    }

    public App getApp() {
        return app;
    }

    public void setApplist(List<AppconfigApplist> applist) {
        this.applist = applist;
    }

    public void setApp(App app) {
        this.app = app;
    }


    public long getiConfigid() {
        return iConfigid;
    }

    public void setiConfigid(long iConfigid) {
        this.iConfigid = iConfigid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Integer getiRange() {
        return iRange;
    }

    public void setiRange(Integer iRange) {
        this.iRange = iRange;
    }

    public String getcRangeids() {
        return cRangeids;
    }

    public void setcRangeids(String cRangeids) {
        this.cRangeids = cRangeids == null ? null : cRangeids.trim();
    }

    public String gettCtime() {
        return tCtime;
    }

    public void settCtime(String tCtime) {
        this.tCtime = tCtime;
    }

    public long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(long iCuserid) {
        this.iCuserid = iCuserid;
    }

    public String gettUtime() {
        return tUtime;
    }

    public void settUtime(String tUtime) {
        this.tUtime = tUtime;
    }

    public long getiUuserid() {
        return iUuserid;
    }

    public void setiUuserid(long iUuserid) {
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

    public Appconfig() {
    }

    public Appconfig(long iConfigid, String cName, long iSchoolid, Integer iRange, String cRangeids, String tCtime, long iCuserid, String tUtime, long iUuserid, String cRemark, Integer iStatus) {
        this.iConfigid = iConfigid;
        this.cName = cName;
        this.iSchoolid = iSchoolid;
        this.iRange = iRange;
        this.cRangeids = cRangeids;
        this.tCtime = tCtime;
        this.iCuserid = iCuserid;
        this.tUtime = tUtime;
        this.iUuserid = iUuserid;
        this.cRemark = cRemark;
        this.iStatus = iStatus;
    }

    @Override
    public String toString() {
        return "Appconfig{" +
                "iConfigid=" + iConfigid +
                ", cName='" + cName + '\'' +
                ", iSchoolid=" + iSchoolid +
                ", iRange=" + iRange +
                ", cRangeids='" + cRangeids + '\'' +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", tUtime='" + tUtime + '\'' +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", applist=" + applist +
                ", appConfigconPadList=" + appConfigconPadList +
                ", app=" + app +
                ", appRj=" + appRj +
                '}';
    }
}