package com.fuhui.entity.txm;

import java.io.Serializable;
import java.util.Date;

public class PadconfigPadlist implements Serializable {
    private Long iIdx;

    private Long iConfigid;

    private Long iPadid;

    private Date tCtime;

    private static final long serialVersionUID = 1L;

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public Long getiConfigid() {
        return iConfigid;
    }

    public void setiConfigid(Long iConfigid) {
        this.iConfigid = iConfigid;
    }

    public Long getiPadid() {
        return iPadid;
    }

    public void setiPadid(Long iPadid) {
        this.iPadid = iPadid;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }
}