package com.fuhui.entity.model;

public class DeviceList {

    private String devId;
    private String releaseId;
    private String factoryId;
    private String versionId;
    private String name;
    private String verName;
    private Integer type;
    private String model;
    private String version;
    private String releaseTime;
    private Integer status;
    private String spec;

    public String getDevId() {
        return devId;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public String getVersionId() {
        return versionId;
    }

    public String getName() {
        return name;
    }

    public String getVerName() {
        return verName;
    }

    public Integer getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "DeviceList{" +
                "devId='" + devId + '\'' +
                ", releaseId='" + releaseId + '\'' +
                ", factoryId='" + factoryId + '\'' +
                ", versionId='" + versionId + '\'' +
                ", name='" + name + '\'' +
                ", verName='" + verName + '\'' +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", status=" + status +
                ", spec='" + spec + '\'' +
                '}';
    }
}
