package com.fuhui.entity.twf;

import java.io.Serializable;
import java.util.Date;

public class WfLog implements Serializable {
    private Long iLogid;

    private String cDmac;

    private String cSmac;

    private String cGmac;

    private Integer iStrong;

    private String cType;

    private Date tTime;

    private Date tCtime;

    private static final long serialVersionUID = 1L;

    public Long getiLogid() {
        return iLogid;
    }

    public void setiLogid(Long iLogid) {
        this.iLogid = iLogid;
    }

    public String getcDmac() {
        return cDmac;
    }

    public void setcDmac(String cDmac) {
        this.cDmac = cDmac == null ? null : cDmac.trim();
    }

    public String getcSmac() {
        return cSmac;
    }

    public void setcSmac(String cSmac) {
        this.cSmac = cSmac == null ? null : cSmac.trim();
    }

    public String getcGmac() {
        return cGmac;
    }

    public void setcGmac(String cGmac) {
        this.cGmac = cGmac == null ? null : cGmac.trim();
    }

    public Integer getiStrong() {
        return iStrong;
    }

    public void setiStrong(Integer iStrong) {
        this.iStrong = iStrong;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
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
        return "WfLog{" +
                "iLogid=" + iLogid +
                ", cDmac='" + cDmac + '\'' +
                ", cSmac='" + cSmac + '\'' +
                ", cGmac='" + cGmac + '\'' +
                ", iStrong=" + iStrong +
                ", cType='" + cType + '\'' +
                ", tTime=" + tTime +
                ", tCtime=" + tCtime +
                '}';
    }
}