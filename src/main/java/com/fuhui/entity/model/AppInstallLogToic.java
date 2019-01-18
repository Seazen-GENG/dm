package com.fuhui.entity.model;

public class AppInstallLogToic {
    private String mac;
    private String appId;
    private String time;
    private String isSys;
    private String pName;
    private String appName;
    private String padId;
    private String status;
    private String version;
    private String versionCode;
    private String versionId;
    private String way;

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setPadId(String padId) {
        this.padId = padId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getMac() {
        return mac;
    }

    public String getAppId() {
        return appId;
    }

    public String getTime() {
        return time;
    }

    public String getIsSys() {
        return isSys;
    }

    public String getpName() {
        return pName;
    }

    public String getAppName() {
        return appName;
    }

    public String getPadId() {
        return padId;
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getVersionId() {
        return versionId;
    }

    public String getWay() {
        return way;
    }

    @Override
    public String toString() {
        return "AppInstallLogToic{" +
                "mac='" + mac + '\'' +
                ", appId='" + appId + '\'' +
                ", time='" + time + '\'' +
                ", isSys='" + isSys + '\'' +
                ", pName='" + pName + '\'' +
                ", appName='" + appName + '\'' +
                ", padId='" + padId + '\'' +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionId='" + versionId + '\'' +
                ", way='" + way + '\'' +
                '}';
    }
}
