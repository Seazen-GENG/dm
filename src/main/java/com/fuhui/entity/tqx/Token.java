package com.fuhui.entity.tqx;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {
    private Long iTokenid;

    private Long iConfigid;

    private Long iPadid;

    private Long iCuserid;

    private Date tCtime;

    private Date tOtime;

    private Date tUtime;

    private Long iUuserid;

    private String token;

    private static final long serialVersionUID = 1L;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getiTokenid() {
        return iTokenid;
    }

    public void setiTokenid(Long iTokenid) {
        this.iTokenid = iTokenid;
    }

    public Long getiConfigid() {
        return iConfigid;
    }

    public void setiConfigid(Long iConfigid) {
        this.iConfigid = iConfigid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public Long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(Long iCuserid) {
        this.iCuserid = iCuserid;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }

    public Date gettOtime() {
        return tOtime;
    }

    public void settOtime(Date tOtime) {
        this.tOtime = tOtime;
    }

    public Date gettUtime() {
        return tUtime;
    }

    public void settUtime(Date tUtime) {
        this.tUtime = tUtime;
    }

    public Long getiUuserid() {
        return iUuserid;
    }

    public void setiUuserid(Long iUuserid) {
        this.iUuserid = iUuserid;
    }

    @Override
    public String toString() {
        return "Token{" +
                "iTokenid=" + iTokenid +
                ", iConfigid=" + iConfigid +
                ", iPadid=" + iPadid +
                ", iCuserid=" + iCuserid +
                ", tCtime=" + tCtime +
                ", tOtime=" + tOtime +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", token='" + token + '\'' +
                '}';
    }
}