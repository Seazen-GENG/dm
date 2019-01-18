package com.fuhui.entity.tcs;

import java.io.Serializable;
import java.util.Date;

public class FversionReleasePadList implements Serializable {
    private Long iIdx;

    private Long iReleaseid;

    private Long iDeviceid;

    private Date tCtime;

    private static final long serialVersionUID = 1L;

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public Long getiReleaseid() {
        return iReleaseid;
    }

    public void setiReleaseid(Long iReleaseid) {
        this.iReleaseid = iReleaseid;
    }

    public Long getiDeviceid() {
        return iDeviceid;
    }

    public void setiDeviceid(Long iDeviceid) {
        this.iDeviceid = iDeviceid;
    }

    public Date gettCtime() {
        return tCtime;
    }

    public void settCtime(Date tCtime) {
        this.tCtime = tCtime;
    }
}