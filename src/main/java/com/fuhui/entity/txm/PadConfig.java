package com.fuhui.entity.txm;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PadConfig implements Serializable {
    private Long iConfigid;

    private String cName;

    private Long iSchoolid;

    private Integer iType;

    private Integer iRange;

    private String cRangeids;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Integer iStatus;

    private List<PadConfigList> padConfigList;

    private List<PadConfigconfigList> padConfigconfigLists;

    private PadconfigPadlist padlist;

    private static final long serialVersionUID = 1L;

    public List<PadConfigconfigList> getPadConfigconfigLists() {
        return padConfigconfigLists;
    }

    public void setPadConfigconfigLists(List<PadConfigconfigList> padConfigconfigLists) {
        this.padConfigconfigLists = padConfigconfigLists;
    }

    public PadconfigPadlist getPadlist() {
        return padlist;
    }

    public void setPadlist(PadconfigPadlist padlist) {
        this.padlist = padlist;
    }

    public List<PadConfigList> getPadConfigList() {
        return padConfigList;
    }

    public void setPadConfigList(List<PadConfigList> padConfigList) {
        this.padConfigList = padConfigList;
    }

    public Long getiConfigid() {
        return iConfigid;
    }

    public void setiConfigid(Long iConfigid) {
        this.iConfigid = iConfigid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
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

    public Date gettCtime() {
        return this.tCtime;
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
        return this.tUtime;
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
        return "PadConfig{" +
                "iConfigid=" + iConfigid +
                ", cName='" + cName + '\'' +
                ", iSchoolid=" + iSchoolid +
                ", iType=" + iType +
                ", iRange=" + iRange +
                ", cRangeids='" + cRangeids + '\'' +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", tUtime='" + tUtime + '\'' +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
//                ", padConfigList=" + padConfigList +
                '}';
    }
}