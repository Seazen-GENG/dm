package com.fuhui.entity.tpd;

import java.io.Serializable;
import java.util.Date;

public class PadBangDingLog implements Serializable {
    private Long iLogid;

    private Long iPadid;

    private Integer iType;

    private Long iSchoolid;

    private Integer iUtype;

    private Long iUserid;

    private Long iGradeid;

    private Long iClassid;

    private Integer iUplace;

    private Long iUuserid;

    private Date tTime;

    private static final long serialVersionUID = 1L;

    public Long getiLogid() {
        return iLogid;
    }

    public void setiLogid(Long iLogid) {
        this.iLogid = iLogid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
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

    public Integer getiUtype() {
        return iUtype;
    }

    public void setiUtype(Integer iUtype) {
        this.iUtype = iUtype;
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }

    public Long getiGradeid() {
        return iGradeid;
    }

    public void setiGradeid(Long iGradeid) {
        this.iGradeid = iGradeid;
    }

    public Long getiClassid() {
        return iClassid;
    }

    public void setiClassid(Long iClassid) {
        this.iClassid = iClassid;
    }

    public Integer getiUplace() {
        return iUplace;
    }

    public void setiUplace(Integer iUplace) {
        this.iUplace = iUplace;
    }

    public Long getiUuserid() {
        return iUuserid;
    }

    public void setiUuserid(Long iUuserid) {
        this.iUuserid = iUuserid;
    }

    public Date gettTime() {
        return tTime;
    }

    public void settTime(Date tTime) {
        this.tTime = tTime;
    }
}