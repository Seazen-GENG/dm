package com.fuhui.entity.model;

public class PadInstallApp {

    private String appId;
    private String type;
    private String uWay;
    private String pgName;
    private String appName;
    private String curTime;
    private String curVersionId;
    private String curVersion;
    private String curVersionCode;
    private String newVersion;
    private String newVersionCode;
    private String newDownUrl;
    private String isNew;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppId() {
        return appId;
    }

    public String getType() {
        return type;
    }

    public String getuWay() {
        return uWay;
    }

    public String getPgName() {
        return pgName;
    }

    public String getAppName() {
        return appName;
    }

    public String getCurTime() {
        return curTime;
    }

    public String getCurVersionId() {
        return curVersionId;
    }

    public String getCurVersionCode() {
        return curVersionCode;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getNewVersionCode() {
        return newVersionCode;
    }

    public String getNewDownUrl() {
        return newDownUrl;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setuWay(String uWay) {
        this.uWay = uWay;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public void setCurVersionId(String curVersionId) {
        this.curVersionId = curVersionId;
    }

    public void setCurVersionCode(String curVersionCode) {
        this.curVersionCode = curVersionCode;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public void setNewVersionCode(String newVersionCode) {
        this.newVersionCode = newVersionCode;
    }

    public void setNewDownUrl(String newDownUrl) {
        this.newDownUrl = newDownUrl;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getCurVersion() {
        return curVersion;
    }

    public void setCurVersion(String curVersion) {
        this.curVersion = curVersion;
    }

    @Override
    public String toString() {
        return "PadInstallApp{" +
                "appId='" + appId + '\'' +
                ", type='" + type + '\'' +
                ", uWay='" + uWay + '\'' +
                ", pgName='" + pgName + '\'' +
                ", appName='" + appName + '\'' +
                ", curTime='" + curTime + '\'' +
                ", curVersionId='" + curVersionId + '\'' +
                ", curVersion='" + curVersion + '\'' +
                ", curVersionCode='" + curVersionCode + '\'' +
                ", newVersion='" + newVersion + '\'' +
                ", newVersionCode='" + newVersionCode + '\'' +
                ", newDownUrl='" + newDownUrl + '\'' +
                ", isNew='" + isNew + '\'' +
                '}';
    }
}
