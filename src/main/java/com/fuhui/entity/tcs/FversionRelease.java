package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class FversionRelease implements Serializable {
    private Long iReleaseid;

    private Long iFdeviceid;

    private Long iFversionid;

    private Long iSchoolid;

    private Integer iRange;

    private String cRangeids;

    private String cDownurl;

    private Date tReleasetime;

    private Date tCtime;

    private Long iCuserid;

    private String cRemark;

    private Integer iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiReleaseid() {
        return iReleaseid;
    }

    public void setiReleaseid(Long iReleaseid) {
        this.iReleaseid = iReleaseid;
    }

    public Long getiFdeviceid() {
        return iFdeviceid;
    }

    public void setiFdeviceid(Long iFdeviceid) {
        this.iFdeviceid = iFdeviceid;
    }

    public Long getiFversionid() {
        return iFversionid;
    }

    public void setiFversionid(Long iFversionid) {
        this.iFversionid = iFversionid;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Integer getiRange() {
        return iRange;
    }

    public void setiRange(Integer iRange) {
        this.iRange = iRange;
    }

    public String getcRangeids() {
        return cRangeids;
    }

    public void setcRangeids(String cRangeids) {
        this.cRangeids = cRangeids == null ? null : cRangeids.trim();
    }

    public String getcDownurl() {
        return cDownurl;
    }

    public void setcDownurl(String cDownurl) {
        this.cDownurl = cDownurl == null ? null : cDownurl.trim();
    }

    public Date gettReleasetime() {
        return tReleasetime;
    }

    public void settReleasetime(Date tReleasetime) {
        this.tReleasetime = tReleasetime;
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
        return "FversionRelease{" +
                "iReleaseid=" + iReleaseid +
                ", iFdeviceid=" + iFdeviceid +
                ", iFversionid=" + iFversionid +
                ", iSchoolid=" + iSchoolid +
                ", iRange=" + iRange +
                ", cRangeids='" + cRangeids + '\'' +
                ", cDownurl='" + cDownurl + '\'' +
                ", tReleasetime=" + tReleasetime +
                ", tCtime=" + tCtime +
                ", iCuserid=" + iCuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                '}';
    }
}