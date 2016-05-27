package com.yc.lining.entity;

import java.math.BigDecimal;

public class Manager {
    private BigDecimal mid;

    private String mname;

    private String mpwd;

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public String getMpwd() {
        return mpwd;
    }

    public void setMpwd(String mpwd) {
        this.mpwd = mpwd == null ? null : mpwd.trim();
    }
}