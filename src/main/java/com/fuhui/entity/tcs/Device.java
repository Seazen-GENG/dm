package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {
    private Long iFdeviceid;

    private Long iFactoryid;

    private Integer iDtype;

    private String cName;

    private String cImg;

    private String cModel;

    private String cSpec;

    private Long iFversionid;

    private Date tFutime;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiFdeviceid() {
        return iFdeviceid;
    }

    public void setiFdeviceid(Long iFdeviceid) {
        this.iFdeviceid = iFdeviceid;
    }

    public Long getiFactoryid() {
        return iFactoryid;
    }

    public void setiFactoryid(Long iFactoryid) {
        this.iFactoryid = iFactoryid;
    }

    public Integer getiDtype() {
        return iDtype;
    }

    public void setiDtype(Integer iDtype) {
        this.iDtype = iDtype;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcImg() {
        return cImg;
    }

    public void setcImg(String cImg) {
        this.cImg = cImg == null ? null : cImg.trim();
    }

    public String getcModel() {
        return cModel;
    }

    public void setcModel(String cModel) {
        this.cModel = cModel == null ? null : cModel.trim();
    }

    public String getcSpec() {
        return cSpec;
    }

    public void setcSpec(String cSpec) {
        this.cSpec = cSpec == null ? null : cSpec.trim();
    }

    public Long getiFversionid() {
        return iFversionid;
    }

    public void setiFversionid(Long iFversionid) {
        this.iFversionid = iFversionid;
    }

    public Date gettFutime() {
        return tFutime;
    }

    public void settFutime(Date tFutime) {
        this.tFutime = tFutime;
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
        return "Device{" +
                "iFdeviceid=" + iFdeviceid +
                ", iFactoryid=" + iFactoryid +
                ", iDtype=" + iDtype +
                ", cName='" + cName + '\'' +
                ", cImg='" + cImg + '\'' +
                ", cModel='" + cModel + '\'' +
                ", cSpec='" + cSpec + '\'' +
                ", iFversionid=" + iFversionid +
                ", tFutime=" + tFutime +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", tUtime=" + tUtime +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                '}';
    }
}