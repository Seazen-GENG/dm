package com.fuhui.entity.txu;

import com.fuhui.entity.tjc.Stage;

import java.io.Serializable;
public class Grade implements Serializable {
    private Long iXgradeid;

    private String cName;

    private String cCode;

    private Long iGradeid;

    private Long iStageid;

    private Long iSchoolid;

    private Integer iOrderno;

    private String tCtime;

    private Long iCuserid;

    private String tUtime;

    private Long iUuserid;

    private String cRemark;

    private Byte iStatus;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private static final long serialVersionUID = 1L;

    public Stage getStage() {
        return stage;
    }

    public Long getiXgradeid() {
        return iXgradeid;
    }

    public void setiXgradeid(Long iXgradeid) {
        this.iXgradeid = iXgradeid;
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

    public Long getiGradeid() {
        return iGradeid;
    }

    public void setiGradeid(Long iGradeid) {
        this.iGradeid = iGradeid;
    }

    public Long getiStageid() {
        return iStageid;
    }

    public void setiStageid(Long iStageid) {
        this.iStageid = iStageid;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Integer getiOrderno() {
        return iOrderno;
    }

    public void setiOrderno(Integer iOrderno) {
        this.iOrderno = iOrderno;
    }

    public String gettCtime() {
        return this.tCtime;
    }

    public void settCtime(String tCtime) {
        this.tCtime = tCtime;
    }

    public Long getiCuserid() {
        return iCuserid;
    }

    public void setiCuserid(Long iCuserid) {
        this.iCuserid = iCuserid;
    }

    public String gettUtime() {
        return tUtime;
    }

    public void settUtime(String tUtime) {
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

    public Byte getiStatus() {
        return iStatus;
    }

    public void setiStatus(Byte iStatus) {
        this.iStatus = iStatus;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "iXgradeid=" + iXgradeid +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", iGradeid=" + iGradeid +
                ", iStageid=" + iStageid +
                ", iSchoolid=" + iSchoolid +
                ", iOrderno=" + iOrderno +
                ", tCtime='" + tCtime + '\'' +
                ", iCuserid=" + iCuserid +
                ", tUtime='" + tUtime + '\'' +
                ", iUuserid=" + iUuserid +
                ", cRemark='" + cRemark + '\'' +
                ", iStatus=" + iStatus +
                ", stage=" + stage +
                '}';
    }
}