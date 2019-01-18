package com.fuhui.entity.txu;

import java.io.Serializable;
import java.util.Date;

public class Year implements Serializable {
    private Long iYearid;

    private String cName;

    private String cCode;

    private Long iSchoolid;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private String cStname;

    private Date dSdate;

    private Date dEdate;

    private static final long serialVersionUID = 1L;

    public Long getiYearid() {
        return iYearid;
    }

    public void setiYearid(Long iYearid) {
        this.iYearid = iYearid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode == null ? null : cCode.trim();
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

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public String getcStname() {
        return cStname;
    }

    public void setcStname(String cStname) {
        this.cStname = cStname == null ? null : cStname.trim();
    }

    public Date getdSdate() {
        return dSdate;
    }

    public void setdSdate(Date dSdate) {
        this.dSdate = dSdate;
    }

    public Date getdEdate() {
        return dEdate;
    }

    public void setdEdate(Date dEdate) {
        this.dEdate = dEdate;
    }

    @Override
    public String toString() {
        return "Year{" +
                "iYearid=" + iYearid +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", iSchoolid=" + iSchoolid +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", cStname='" + cStname + '\'' +
                ", dSdate=" + dSdate +
                ", dEdate=" + dEdate +
                '}';
    }
}