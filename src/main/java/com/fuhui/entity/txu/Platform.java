package com.fuhui.entity.txu;

import java.io.Serializable;

public class Platform implements Serializable {
    private Long iIdx;

    private Long iSchoolid;

    private Byte iType;

    private String cPath;

    private static final long serialVersionUID = 1L;

    public Long getiIdx() {
        return iIdx;
    }

    public void setiIdx(Long iIdx) {
        this.iIdx = iIdx;
    }

    public Long getiSchoolid() {
        return iSchoolid;
    }

    public void setiSchoolid(Long iSchoolid) {
        this.iSchoolid = iSchoolid;
    }

    public Byte getiType() {
        return iType;
    }

    public void setiType(Byte iType) {
        this.iType = iType;
    }

    public String getcPath() {
        return cPath;
    }

    public void setcPath(String cPath) {
        this.cPath = cPath == null ? null : cPath.trim();
    }
}