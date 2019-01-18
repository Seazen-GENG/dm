package com.fuhui.entity.tut;

import java.io.Serializable;
import java.util.Date;

public class OauthToken implements Serializable {
    private Long iId;

    private String cToken;

    private Long iAppid;

    private Long iPadid;

    private String cMac;

    private Long iUserid;

    private Byte iType;

    private Long iGradeid;

    private Long iXgradeid;

    private Long iClassid;

    private Long iSchoolid;

    private Date tCtime;

    private Date tUtime;

    private static final long serialVersionUID = 1L;

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public String getcToken() {
        return cToken;
    }

    public void setcToken(String cToken) {
        this.cToken = cToken == null ? null : cToken.trim();
    }

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public String getcMac() {
        return cMac;
    }

    public void setcMac(String cMac) {
        this.cMac = cMac == null ? null : cMac.trim();
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }

    public Byte getiType() {
        return iType;
    }

    public void setiType(Byte iType) {
        this.iType = iType;
    }

    public Long getiGradeid() {
        return iGradeid;
    }

    public void setiGradeid(Long iGradeid) {
        this.iGradeid = iGradeid;
    }

    public Long getiXgradeid() {
        return iXgradeid;
    }

    public void setiXgradeid(Long iXgradeid) {
        this.iXgradeid = iXgradeid;
    }

    public Long getiClassid() {
        return iClassid;
    }

    public void setiClassid(Long iClassid) {
        this.iClassid = iClassid;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }

    public Date gettUtime() {
        return tUtime;
    }

    public void settUtime(Date tUtime) {
        this.tUtime = tUtime;
    }
}