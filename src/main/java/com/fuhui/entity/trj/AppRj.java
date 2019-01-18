package com.fuhui.entity.trj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppRj implements Serializable {
    private Long iAppid;

    private Long iProductid;

    private String cName;

    private String cPackage;

    private Integer iPlace;

    private String cPlaceremark;

    private Integer iType;

    private Integer iIsfree;

    private BigDecimal nPrice;

    private Long iVersionid;

    private Date tFrtime;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cDesc;

    private String cRemark;

    private Integer iStatus;

    private String cImgpath;

    private Integer iIsshare;

    private Integer iSort;

    private Long iSchoolid;

    private String cShortdesc;

    private Integer schoolNum;

    private Integer iUserType;

    private static final long serialVersionUID = 1L;

    public Integer getiUserType() {
        return iUserType;
    }

    public void setiUserType(Integer iUserType) {
        this.iUserType = iUserType;
    }

    public Integer getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(Integer schoolNum) {
        this.schoolNum = schoolNum;
    }

    public Long getiAppid() {
        return iAppid;
    }

    public void setiAppid(Long iAppid) {
        this.iAppid = iAppid;
    }

    public Long getiProductid() {
        return iProductid;
    }

    public void setiProductid(Long iProductid) {
        this.iProductid = iProductid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcPackage() {
        return cPackage;
    }

    public void setcPackage(String cPackage) {
        this.cPackage = cPackage == null ? null : cPackage.trim();
    }

    public Integer getiPlace() {
        return iPlace;
    }

    public void setiPlace(Integer iPlace) {
        this.iPlace = iPlace;
    }

    public String getcPlaceremark() {
        return cPlaceremark;
    }

    public void setcPlaceremark(String cPlaceremark) {
        this.cPlaceremark = cPlaceremark == null ? null : cPlaceremark.trim();
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    public Integer getiIsfree() {
        return iIsfree;
    }

    public void setiIsfree(Integer iIsfree) {
        this.iIsfree = iIsfree;
    }

    public BigDecimal getnPrice() {
        return nPrice;
    }

    public void setnPrice(BigDecimal nPrice) {
        this.nPrice = nPrice;
    }

    public Long getiVersionid() {
        return iVersionid;
    }

    public void setiVersionid(Long iVersionid) {
        this.iVersionid = iVersionid;
    }

    public Date gettFrtime() {
        return tFrtime;
    }

    public void settFrtime(Date tFrtime) {
        this.tFrtime = tFrtime;
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

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc == null ? null : cDesc.trim();
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

    public String getcImgpath() {
        return cImgpath;
    }

    public void setcImgpath(String cImgpath) {
        this.cImgpath = cImgpath == null ? null : cImgpath.trim();
    }

    public Integer getiIsshare() {
        return iIsshare;
    }

    public void setiIsshare(Integer iIsshare) {
        this.iIsshare = iIsshare;
    }

    public Integer getiSort() {
        return iSort;
    }

    public void setiSort(Integer iSort) {
        this.iSort = iSort;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public String getcShortdesc() {
        return cShortdesc;
    }

    public void setcShortdesc(String cShortdesc) {
        this.cShortdesc = cShortdesc == null ? null : cShortdesc.trim();
    }

    @Override
    public String toString() {
        return "AppRj{" +
                "iAppid=" + iAppid +
                ", iProductid=" + iProductid +
                ", cName='" + cName + '\'' +
                ", cPackage='" + cPackage + '\'' +
                ", iPlace=" + iPlace +
                ", cPlaceremark='" + cPlaceremark + '\'' +
                ", iType=" + iType +
                ", iIsfree=" + iIsfree +
                ", nPrice=" + nPrice +
                ", iVersionid=" + iVersionid +
                ", tFrtime=" + tFrtime +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", cDesc='" + cDesc + '\'' +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", cImgpath='" + cImgpath + '\'' +
                ", iIsshare=" + iIsshare +
                ", iSort=" + iSort +
                ", iSchoolid=" + iSchoolid +
                ", cShortdesc='" + cShortdesc + '\'' +
                '}';
    }
}