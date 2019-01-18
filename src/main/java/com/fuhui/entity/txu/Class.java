package com.fuhui.entity.txu;

import java.io.Serializable;
import java.util.Date;

public class Class implements Serializable {
    private Long iClassid;

    private String cName;

    private String cCode;

    private String cStname;

    private Long iSchoolid;

    private Long iGradeid;

    private Long iXgradeid;

    private Integer iType;

    private Long iYearid;

    private Integer iStudentnum;

    private Integer iOrderno;

    private Long iRoomid;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private long iHeadId;

    private Grade grade;

    private Year year;

    private static final long serialVersionUID = 1L;

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public long getiHeadId() {
        return iHeadId;
    }

    public void setiHeadId(long iHeadId) {
        this.iHeadId = iHeadId;
    }

    public Long getiClassid() {
        return iClassid;
    }

    public void setiClassid(Long iClassid) {
        this.iClassid = iClassid;
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

    public String getcStname() {
        return cStname;
    }

    public void setcStname(String cStname) {
        this.cStname = cStname == null ? null : cStname.trim();
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
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

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    public Long getiYearid() {
        return iYearid;
    }

    public void setiYearid(Long iYearid) {
        this.iYearid = iYearid;
    }

    public Integer getiStudentnum() {
        return iStudentnum;
    }

    public void setiStudentnum(Integer iStudentnum) {
        this.iStudentnum = iStudentnum;
    }

    public Integer getiOrderno() {
        return iOrderno;
    }

    public void setiOrderno(Integer iOrderno) {
        this.iOrderno = iOrderno;
    }

    public Long getiRoomid() {
        return iRoomid;
    }

    public void setiRoomid(Long iRoomid) {
        this.iRoomid = iRoomid;
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

    @Override
    public String toString() {
        return "Class{" +
                "iClassid=" + iClassid +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", cStname='" + cStname + '\'' +
                ", iSchoolid=" + iSchoolid +
                ", iGradeid=" + iGradeid +
                ", iXgradeid=" + iXgradeid +
                ", iType=" + iType +
                ", iYearid=" + iYearid +
                ", iStudentnum=" + iStudentnum +
                ", iOrderno=" + iOrderno +
                ", iRoomid=" + iRoomid +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", iHeadId=" + iHeadId +
                ", grade=" + grade +
                '}';
    }
}