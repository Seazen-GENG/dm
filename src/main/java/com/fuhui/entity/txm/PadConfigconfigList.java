package com.fuhui.entity.txm;

import java.io.Serializable;

public class PadConfigconfigList implements Serializable {
    private Long iIdx;

    private Long iConfigid;

    private String cExtend1;

    private String cConfig;

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

    public String getcExtend1() {
        return cExtend1;
    }

    public void setcExtend1(String cExtend1) {
        this.cExtend1 = cExtend1 == null ? null : cExtend1.trim();
    }

    public String getcConfig() {
        return cConfig;
    }

    public void setcConfig(String cConfig) {
        this.cConfig = cConfig == null ? null : cConfig.trim();
    }

    @Override
    public String toString() {
        return "PadConfigconfigList{" +
                "iIdx=" + iIdx +
                ", iConfigid=" + iConfigid +
                ", cExtend1='" + cExtend1 + '\'' +
                ", cConfig='" + cConfig + '\'' +
                '}';
    }
}