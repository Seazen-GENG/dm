package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class Factory implements Serializable {
    private Long iFactoryid;

    private String cName;

    private String cCode;

    private String cAddress;

    private String cPhone;

    private String cFax;

    private Long iRegionid;

    private Long iRegionpid;

    private Long iRegioncid;

    private Integer iType;

    private String cDtype;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    ///联系人
    private String contName;
    //厂商设备个数
    private Integer deivecNum;

    private String contPhone;

    private static final long serialVersionUID = 1L;

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }

    public String getContPhone() {
        return contPhone;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public void setDeivecNum(Integer deivecNum) {
        this.deivecNum = deivecNum;
    }

    public String getContName() {
        return contName;
    }

    public Integer getDeivecNum() {
        return deivecNum;
    }

    public Long getiFactoryid() {
        return iFactoryid;
    }

    public void setiFactoryid(Long iFactoryid) {
        this.iFactoryid = iFactoryid;
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

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress == null ? null : cAddress.trim();
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone == null ? null : cPhone.trim();
    }

    public String getcFax() {
        return cFax;
    }

    public void setcFax(String cFax) {
        this.cFax = cFax == null ? null : cFax.trim();
    }

    public Long getiRegionid() {
        return iRegionid;
    }

    public void setiRegionid(Long iRegionid) {
        this.iRegionid = iRegionid;
    }

    public Long getiRegionpid() {
        return iRegionpid;
    }

    public void setiRegionpid(Long iRegionpid) {
        this.iRegionpid = iRegionpid;
    }

    public Long getiRegioncid() {
        return iRegioncid;
    }

    public void setiRegioncid(Long iRegioncid) {
        this.iRegioncid = iRegioncid;
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    public String getcDtype() {
        return cDtype;
    }

    public void setcDtype(String cDtype) {
        this.cDtype = cDtype == null ? null : cDtype.trim();
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
}