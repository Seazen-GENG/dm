package com.fuhui.entity.taz;

import java.io.Serializable;
import java.util.Date;

public class PadauthorizePadlist implements Serializable {
    private Long iIdx;

    private Long iAuzid;

    private Long iPadid;

    private Date tFtime;

    private String cMac;

    private String cIp;

    private static final long serialVersionUID = 1L;

    public String getcMac() {
        return cMac;
    }

    public String getcIp() {
        return cIp;
    }

    public void setcMac(String cMac) {
        this.cMac = cMac;
    }

    public void setcIp(String cIp) {
        this.cIp = cIp;
    }

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public Long getiAuzid() {
        return iAuzid;
    }

    public void setiAuzid(Long iAuzid) {
        this.iAuzid = iAuzid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public Date gettFtime() {
        return tFtime;
    }

    public void settFtime(Date tFtime) {
        this.tFtime = tFtime;
    }
}