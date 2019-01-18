package com.fuhui.entity.twj;

import java.io.Serializable;
import java.util.Date;

public class WjUserFile implements Serializable {
    private Long iUfid;

    private Long iFileid;

    private String cName;

    private String cType;

    private Date tCtime;

    private Long iCuserid;

    private Integer iCplace;

    private Long iRelationid;

    private String cRemark;

    private Integer iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiUfid() {
        return iUfid;
    }

    public void setiUfid(Long iUfid) {
        this.iUfid = iUfid;
    }

    public Long getiFileid() {
        return iFileid;
    }

    public void setiFileid(Long iFileid) {
        this.iFileid = iFileid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
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

    public Long getiRelationid() {
        return iRelationid;
    }

    public void setiRelationid(Long iRelationid) {
        this.iRelationid = iRelationid;
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