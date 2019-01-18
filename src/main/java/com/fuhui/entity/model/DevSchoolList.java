package com.fuhui.entity.model;

public class DevSchoolList {

    private Long schoolId;
    private String name;
    private Integer deviceTotal;
    private Long fversionId;
    private String fversion;
    private Integer fversionCount;
    private Integer status;

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceTotal(Integer deviceTotal) {
        this.deviceTotal = deviceTotal;
    }

    public void setFversionId(Long fversionId) {
        this.fversionId = fversionId;
    }

    public void setFversion(String fversion) {
        this.fversion = fversion;
    }

    public void setFversionCount(Integer fversionCount) {
        this.fversionCount = fversionCount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public String getName() {
        return name;
    }

    public Integer getDeviceTotal() {
        return deviceTotal;
    }

    public Long getFversionId() {
        return fversionId;
    }

    public String getFversion() {
        return fversion;
    }

    public Integer getFversionCount() {
        return fversionCount;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DevSchoolList{" +
                "schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", deviceTotal=" + deviceTotal +
                ", fversionId=" + fversionId +
                ", fversion='" + fversion + '\'' +
                ", fversionCount=" + fversionCount +
                ", status=" + status +
                '}';
    }
}
