package com.fuhui.entity.tut;

import java.io.Serializable;
import java.util.Date;

public class IpAddress implements Serializable {
    private Long iId;

    private String cIp;

    private String cAddress;

    private Date tCtime;

    private static final long serialVersionUID = 1L;

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public String getcIp() {
        return cIp;
    }

    public void setcIp(String cIp) {
        this.cIp = cIp == null ? null : cIp.trim();
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress == null ? null : cAddress.trim();
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }
}