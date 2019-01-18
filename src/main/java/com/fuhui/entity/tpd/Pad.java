package com.fuhui.entity.tpd;

import com.fuhui.entity.taz.Padauthorize;
import com.fuhui.entity.taz.PadauthorizePadlist;
import com.fuhui.entity.txm.*;
import com.fuhui.entity.txu.Class;
import com.fuhui.entity.txu.Grade;
import com.fuhui.entity.txu.School;

import java.io.Serializable;
import java.util.Date;

public class Pad implements Serializable {
    private Long iPadid;

    private String cMac;

    private String cCode;

    private String cName;

    private Long iAuzid;

    private String cSerialno;

    private Long iFactoryid;

    private Long iDeviceid;

    private String cModel;

    private String cFversion;

    private Date tFutime;

    private Integer iType;

    private Long iSchoolid;

    private Long iGradeid;

    private Long iUserid;

    private Long iClassid;

    private Date tStime;

    private Date tCtime;

    private Long iCuserid;

    private Integer iCplace;

    private Date tUtime;

    private Long iUuserid;

    private Integer iUplace;

    private String cRemark;

    private Integer iStatus;

    private JPush jPush;

    private Appconfig appconfig;

    private String Tags;

    private AppConfigconPadList appConfigconPadList;

    private PadauthorizePadlist padauthorizePadlist;

    private Padauthorize padauthorize;

    private Class aClass;

    private String aName;

    private String clName;

    private String gName;

    private String sName;

    private String countAll;

    private Integer iUtype;

    private static final long serialVersionUID = 1L;

    public Integer getiUtype() {
        return iUtype;
    }

    public void setiUtype(Integer iUtype) {
        this.iUtype = iUtype;
    }

    public String getaName() {
        return aName;
    }

    public String getClName() {
        return clName;
    }

    public String getgName() {
        return gName;
    }

    public String getsName() {
        return sName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setClName(String clName) {
        this.clName = clName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Long getiGradeid() {
        return iGradeid;
    }

    public void setiGradeid(Long iGradeid) {
        this.iGradeid = iGradeid;
    }

    public Padauthorize getPadauthorize() {
        return padauthorize;
    }

    public void setPadauthorize(Padauthorize padauthorize) {
        this.padauthorize = padauthorize;
    }

    public PadauthorizePadlist getPadauthorizePadlist() {
        return padauthorizePadlist;
    }

    public void setPadauthorizePadlist(PadauthorizePadlist padauthorizePadlist) {
        this.padauthorizePadlist = padauthorizePadlist;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public void setjPush(JPush jPush) {
        this.jPush = jPush;
    }

    public void setAppconfig(Appconfig appconfig) {
        this.appconfig = appconfig;
    }

    public void setAppConfigconPadList(AppConfigconPadList appConfigconPadList) {
        this.appConfigconPadList = appConfigconPadList;
    }

    public JPush getjPush() {
        return jPush;
    }

    public Appconfig getAppconfig() {
        return appconfig;
    }

    public AppConfigconPadList getAppConfigconPadList() {
        return appConfigconPadList;
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

    public Long getiFactoryid() {
        return iFactoryid;
    }

    public void setiFactoryid(Long iFactoryid) {
        this.iFactoryid = iFactoryid;
    }

    public Long getiDeviceid() {
        return iDeviceid;
    }

    public void setiDeviceid(Long iDeviceid) {
        this.iDeviceid = iDeviceid;
    }

    public String getcModel() {
        return cModel;
    }

    public void setcModel(String cModel) {
        this.cModel = cModel == null ? null : cModel.trim();
    }

    public String getcFversion() {
        return cFversion;
    }

    public void setcFversion(String cFversion) {
        this.cFversion = cFversion == null ? null : cFversion.trim();
    }

    public Date gettFutime() {
        return tFutime;
    }

    public void settFutime(Date tFutime) {
        this.tFutime = tFutime;
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }

    public Long getiClassid() {
        return iClassid;
    }

    public void setiClassid(Long iClassid) {
        this.iClassid = iClassid;
    }

    public Date gettStime() {
        return tStime;
    }

    public void settStime(Date tStime) {
        this.tStime = tStime;
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

    public Integer getiCplace() {
        return iCplace;
    }

    public void setiCplace(Integer iCplace) {
        this.iCplace = iCplace;
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

    public Integer getiUplace() {
        return iUplace;
    }

    public void setiUplace(Integer iUplace) {
        this.iUplace = iUplace;
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

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setCountAll(String countAll) {
        this.countAll = countAll;
    }

    public String getCountAll() {
        return countAll;
    }

    @Override
    public String toString() {
        return "Pad{" +
                "iPadid=" + iPadid +
                ", cMac='" + cMac + '\'' +
                ", cCode='" + cCode + '\'' +
                ", cName='" + cName + '\'' +
                ", iAuzid=" + iAuzid +
                ", cSerialno='" + cSerialno + '\'' +
                ", iFactoryid=" + iFactoryid +
                ", iDeviceid=" + iDeviceid +
                ", cModel='" + cModel + '\'' +
                ", cFversion='" + cFversion + '\'' +
                ", tFutime=" + tFutime +
                ", iType=" + iType +
                ", iSchoolid=" + iSchoolid +
                ", iGradeid=" + iGradeid +
                ", iUserid=" + iUserid +
                ", iClassid=" + iClassid +
                ", tStime=" + tStime +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", iCplace=" + iCplace +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", iUplace=" + iUplace +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", jPush=" + jPush +
                ", appconfig=" + appconfig +
                ", Tags='" + Tags + '\'' +
                ", appConfigconPadList=" + appConfigconPadList +
                ", padauthorizePadlist=" + padauthorizePadlist +
                ", padauthorize=" + padauthorize +
                ", aClass=" + aClass +
                ", aName='" + aName + '\'' +
                ", clName='" + clName + '\'' +
                ", gName='" + gName + '\'' +
                ", sName='" + sName + '\'' +
                ", countAll='" + countAll + '\'' +
                ", iUtype=" + iUtype +
                '}';
    }
}