package com.fuhui.entity.tpd;

import java.io.Serializable;
import java.util.Date;

public class JPush implements Serializable {

    private Long iJpushid;

    private Long iPadid;

    private String cRegistrationid;

    private String cAlias;

    private Date tCtime;

    private Date tUtime;

    private String cTags;

    private static final long serialVersionUID = 1L;

    public Long getiJpushid() {
        return iJpushid;
    }

    public void setiJpushid(Long iJpushid) {
        this.iJpushid = iJpushid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public String getcRegistrationid() {
        return cRegistrationid;
    }

    public void setcRegistrationid(String cRegistrationid) {
        this.cRegistrationid = cRegistrationid == null ? null : cRegistrationid.trim();
    }

    public String getcAlias() {
        return cAlias;
    }

    public void setcAlias(String cAlias) {
        this.cAlias = cAlias == null ? null : cAlias.trim();
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }

    public Date gettUtime() {
        return tUtime;
    }

    public void settUtime(Date tUtime) {
        this.tUtime = tUtime;
    }

    public String getcTags() {
        return cTags;
    }

    public void setcTags(String cTags) {
        this.cTags = cTags == null ? null : cTags.trim();
    }

    @Override
    public String toString() {
        return "JPush{" +
                "iJpushid=" + iJpushid +
                ", iPadid=" + iPadid +
                ", cRegistrationid='" + cRegistrationid + '\'' +
                ", cAlias='" + cAlias + '\'' +
                ", tCtime=" + tCtime +
                ", tUtime=" + tUtime +
                ", cTags='" + cTags + '\'' +
                '}';
    }
}