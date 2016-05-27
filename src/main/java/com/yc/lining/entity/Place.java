package com.yc.lining.entity;

import java.math.BigDecimal;

public class Place {
    private BigDecimal pid;

    private String placename;

    private BigDecimal uId;

    private String shname;

    private String shtel;

    private BigDecimal postcode;

    public BigDecimal getPid() {
        return pid;
    }

    public void setPid(BigDecimal pid) {
        this.pid = pid;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename == null ? null : placename.trim();
    }

    public BigDecimal getuId() {
        return uId;
    }

    public void setuId(BigDecimal uId) {
        this.uId = uId;
    }

    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname == null ? null : shname.trim();
    }

    public String getShtel() {
        return shtel;
    }

    public void setShtel(String shtel) {
        this.shtel = shtel == null ? null : shtel.trim();
    }

    public BigDecimal getPostcode() {
        return postcode;
    }

    public void setPostcode(BigDecimal postcode) {
        this.postcode = postcode;
    }
}