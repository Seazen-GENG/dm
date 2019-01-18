package com.fuhui.entity.cluster;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private Integer userid;

    private String usercode;

    private String username;

    private String userpwd;

    private String identitycard;

    private String birthday;

    private String email;

    private String telephone;

    private Byte jobtitle;

    private Long fileid;

    private Byte gender;

    private Integer schoolid;

    private Integer createby;

    private String createtime;

    private Integer updateby;

    private String updatetime;

    private String accessplatform;

    private String linkaddress;

    private String qq;

    private String usertypecode;

    private Byte usestatus;

    private Long usertypeid;

    private Long subjectid;

    private Long classid;

    private String studentnumber;

    private String classcode;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard == null ? null : identitycard.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Byte getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(Byte jobtitle) {
        this.jobtitle = jobtitle;
    }

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getAccessplatform() {
        return accessplatform;
    }

    public void setAccessplatform(String accessplatform) {
        this.accessplatform = accessplatform == null ? null : accessplatform.trim();
    }

    public String getLinkaddress() {
        return linkaddress;
    }

    public void setLinkaddress(String linkaddress) {
        this.linkaddress = linkaddress == null ? null : linkaddress.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getUsertypecode() {
        return usertypecode;
    }

    public void setUsertypecode(String usertypecode) {
        this.usertypecode = usertypecode == null ? null : usertypecode.trim();
    }

    public Byte getUsestatus() {
        return usestatus;
    }

    public void setUsestatus(Byte usestatus) {
        this.usestatus = usestatus;
    }

    public Long getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(Long usertypeid) {
        this.usertypeid = usertypeid;
    }

    public Long getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Long subjectid) {
        this.subjectid = subjectid;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber == null ? null : studentnumber.trim();
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode == null ? null : classcode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", identitycard='" + identitycard + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", jobtitle=" + jobtitle +
                ", fileid=" + fileid +
                ", gender=" + gender +
                ", schoolid=" + schoolid +
                ", createby=" + createby +
                ", createtime='" + createtime + '\'' +
                ", updateby=" + updateby +
                ", updatetime='" + updatetime + '\'' +
                ", accessplatform='" + accessplatform + '\'' +
                ", linkaddress='" + linkaddress + '\'' +
                ", qq='" + qq + '\'' +
                ", usertypecode='" + usertypecode + '\'' +
                ", usestatus=" + usestatus +
                ", usertypeid=" + usertypeid +
                ", subjectid=" + subjectid +
                ", classid=" + classid +
                ", studentnumber='" + studentnumber + '\'' +
                ", classcode='" + classcode + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}