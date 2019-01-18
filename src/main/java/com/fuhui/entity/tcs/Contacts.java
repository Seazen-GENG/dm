package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class Contacts implements Serializable {
    private Long iFcontactsid;

    private Long iFactoryid;

    private String cContact;

    private String cPost;

    private String cPhone;

    private String cEmail;

    private Date tCtime;

    private Long iCuserid;

    private Date tUtime;

    private Long iUuserid;

    private String cRemark;

    private Byte iStatus;

    private static final long serialVersionUID = 1L;

    public Long getiFcontactsid() {
        return iFcontactsid;
    }

    public void setiFcontactsid(Long iFcontactsid) {
        this.iFcontactsid = iFcontactsid;
    }

    public Long getiFactoryid() {
        return iFactoryid;
    }

    public void setiFactoryid(Long iFactoryid) {
        this.iFactoryid = iFactoryid;
    }

    public String getcContact() {
        return cContact;
    }

    public void setcContact(String cContact) {
        this.cContact = cContact == null ? null : cContact.trim();
    }

    public String getcPost() {
        return cPost;
    }

    public void setcPost(String cPost) {
        this.cPost = cPost == null ? null : cPost.trim();
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone == null ? null : cPhone.trim();
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail == null ? null : cEmail.trim();
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

    public Byte getiStatus() {
        return iStatus;
    }

    public void setiStatus(Byte iStatus) {
        this.iStatus = iStatus;
    }
}