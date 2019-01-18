package com.fuhui.entity.tms;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Long iMsgid;

    private String cPlatform;

    private String cAudience;

    private Integer iType;

    private String cTitle;

    private String cMessage;

    private Date tStime;

    private Long iSuserid;

    private Integer iStatus;

    private String iJpushmsgid;

    private Integer iJstatus;

    private static final long serialVersionUID = 1L;

    public Long getiMsgid() {
        return iMsgid;
    }

    public void setiMsgid(Long iMsgid) {
        this.iMsgid = iMsgid;
    }

    public String getcPlatform() {
        return cPlatform;
    }

    public void setcPlatform(String cPlatform) {
        this.cPlatform = cPlatform == null ? null : cPlatform.trim();
    }

    public String getcAudience() {
        return cAudience;
    }

    public void setcAudience(String cAudience) {
        this.cAudience = cAudience == null ? null : cAudience.trim();
    }

    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle == null ? null : cTitle.trim();
    }

    public String getcMessage() {
        return cMessage;
    }

    public void setcMessage(String cMessage) {
        this.cMessage = cMessage == null ? null : cMessage.trim();
    }

    public Date gettStime() {
        return tStime;
    }

    public void settStime(Date tStime) {
        this.tStime = tStime;
    }

    public Long getiSuserid() {
        return iSuserid;
    }

    public void setiSuserid(Long iSuserid) {
        this.iSuserid = iSuserid;
    }

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public String getiJpushmsgid() {
        return iJpushmsgid;
    }

    public void setiJpushmsgid(String iJpushmsgid) {
        this.iJpushmsgid = iJpushmsgid == null ? null : iJpushmsgid.trim();
    }

    public Integer getiJstatus() {
        return iJstatus;
    }

    public void setiJstatus(Integer iJstatus) {
        this.iJstatus = iJstatus;
    }
}