package com.fuhui.entity.tyh;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long iUserid;

    private String cCode;

    private String cName;

    private String cPwd;

    private Byte iType;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Byte iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode == null ? null : cCode.trim();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcPwd() {
        return cPwd;
    }

    public void setcPwd(String cPwd) {
        this.cPwd = cPwd == null ? null : cPwd.trim();
    }

    public Byte getiType() {
        return iType;
    }

    public void setiType(Byte iType) {
        this.iType = iType;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }

    public Long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(Long iCuserid) {
        this.iCuserid = iCuserid;
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

    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark == null ? null : cRemark.trim();
    }

    public Byte getiStatus() {
        return iStatus;
    }

    public void setiStatus(Byte iStatus) {
        this.iStatus = iStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "iUserid=" + iUserid +
                ", cCode='" + cCode + '\'' +
                ", cName='" + cName + '\'' +
                ", cPwd='" + cPwd + '\'' +
                ", iType=" + iType +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                '}';
    }
}