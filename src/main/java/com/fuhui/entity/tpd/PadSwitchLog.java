package com.fuhui.entity.tpd;

import java.io.Serializable;
import java.util.Date;

public class PadSwitchLog implements Serializable {
    private Long iLogid;

    private Long iPadid;

    private Integer iType;

    private Date tTime;

    private Date tCtime;

    private String countAll;

    private static final long serialVersionUID = 1L;

    public String getCountAll() {
        return countAll;
    }

    public void setCountAll(String countAll) {
        this.countAll = countAll;
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

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
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
        return "PadSwitchLog{" +
                "iLogid=" + iLogid +
                ", iPadid=" + iPadid +
                ", iType=" + iType +
                ", tTime=" + tTime +
                ", tCtime=" + tCtime +
                ", countAll='" + countAll + '\'' +
                '}';
    }
}