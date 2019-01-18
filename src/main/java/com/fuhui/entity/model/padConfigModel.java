package com.fuhui.entity.model;

/***pad配置  指定配置+学校配置***/
public class padConfigModel {

    private String typeName;
    private String curConfig;
    private String curExtend;
    private String curTime;
    private String newConfig;
    private String newExtend;
    private String isNew;

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setCurConfig(String curConfig) {
        this.curConfig = curConfig;
    }

    public void setCurExtend(String curExtend) {
        this.curExtend = curExtend;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public void setNewConfig(String newConfig) {
        this.newConfig = newConfig;
    }

    public void setNewExtend(String newExtend) {
        this.newExtend = newExtend;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getCurConfig() {
        return curConfig;
    }

    public String getCurExtend() {
        return curExtend;
    }

    public String getCurTime() {
        return curTime;
    }

    public String getNewConfig() {
        return newConfig;
    }

    public String getNewExtend() {
        return newExtend;
    }

    public String getIsNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "padConfigModel{" +
                "typeName='" + typeName + '\'' +
                ", curConfig='" + curConfig + '\'' +
                ", curExtend='" + curExtend + '\'' +
                ", curTime='" + curTime + '\'' +
                ", newConfig='" + newConfig + '\'' +
                ", newExtend='" + newExtend + '\'' +
                ", isNew='" + isNew + '\'' +
                '}';
    }
}
