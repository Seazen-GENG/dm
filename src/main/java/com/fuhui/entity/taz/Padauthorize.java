package com.fuhui.entity.taz;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
@Mapper
public class Padauthorize implements Serializable {
    private Long iAuzid;

    private String cSerialno;

    private Long iSchoolid;

    private Integer iTotal;

    private Integer iAcount;

    private Integer iType;

    private Date dSdate;

    private Date dEdate;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private Integer iAType;

    private Long iAppId;

    private static final long serialVersionUID = 1L;

    public void setiAType(Integer iAType) {
        this.iAType = iAType;
    }

    public void setiAppId(Long iAppId) {
        this.iAppId = iAppId;
    }

    public Integer getiAType() {
        return iAType;
    }

    public Long getiAppId() {
        return iAppId;
    }

    public Long getiAuzid() {
        return iAuzid;
    }

    public void setiAuzid(Long iAuzid) {
        this.iAuzid = iAuzid;
    }

    public String getcSerialno() {
        return cSerialno;
    }

    public void setcSerialno(String cSerialno) {
        this.cSerialno = cSerialno == null ? null : cSerialno.trim();
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Integer getiTotal() {
        return iTotal;
    }

    public void setiTotal(Integer iTotal) {
        this.iTotal = iTotal;
    }

    public Integer getiAcount() {
        return iAcount;
    }

    public void setiAcount(Integer iAcount) {
        this.iAcount = iAcount;
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
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
        return "Padauthorize{" +
                "iAuzid=" + iAuzid +
                ", cSerialno='" + cSerialno + '\'' +
                ", iSchoolid=" + iSchoolid +
                ", iTotal=" + iTotal +
                ", iAcount=" + iAcount +
                ", iType=" + iType +
                ", dSdate=" + dSdate +
                ", dEdate=" + dEdate +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", iAType=" + iAType +
                ", iAppId=" + iAppId +
                '}';
    }
}