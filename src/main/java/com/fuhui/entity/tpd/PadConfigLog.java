package com.fuhui.entity.tpd;

import java.io.Serializable;
import java.util.Date;

public class PadConfigLog implements Serializable {
    private Long iLogid;

    private Long iPadid;

    private Long iConfigid;

    private String cConfig;

    private String cExtend1;

    private Date tTime;

    private Date tCtime;

    private Integer iType;

    private static final long serialVersionUID = 1L;

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
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

    public Long getiConfigid() {
        return iConfigid;
    }

    public void setiConfigid(Long iConfigid) {
        this.iConfigid = iConfigid;
    }

    public String getcConfig() {
        return cConfig;
    }

    public void setcConfig(String cConfig) {
        this.cConfig = cConfig;
    }

    public String getcExtend1() {
        return cExtend1;
    }

    public void setcExtend1(String cExtend1) {
        this.cExtend1 = cExtend1 == null ? null : cExtend1.trim();
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

    @Override
    public String toString() {
        return "PadConfigLog{" +
                "iLogid=" + iLogid +
                ", iPadid=" + iPadid +
                ", iConfigid=" + iConfigid +
                ", cConfig='" + cConfig + '\'' +
                ", cExtend1='" + cExtend1 + '\'' +
                ", tTime=" + tTime +
                ", tCtime=" + tCtime +
                ", iType=" + iType +
                '}';
    }
}