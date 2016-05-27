package com.yc.lining.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Orderform {
    private BigDecimal orderid;

    private Date riqi;

    private BigDecimal uId;

    private BigDecimal proNumber;

    private BigDecimal buyNumber;

    private BigDecimal buyprice;

    private BigDecimal pid;

    private BigDecimal orderflas;

    public BigDecimal getOrderid() {
        return orderid;
    }

    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }

    public Date getRiqi() {
        return riqi;
    }

    public void setRiqi(Date riqi) {
        this.riqi = riqi;
    }

    public BigDecimal getuId() {
        return uId;
    }

    public void setuId(BigDecimal uId) {
        this.uId = uId;
    }

    public BigDecimal getProNumber() {
        return proNumber;
    }

    public void setProNumber(BigDecimal proNumber) {
        this.proNumber = proNumber;
    }

    public BigDecimal getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(BigDecimal buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public BigDecimal getPid() {
        return pid;
    }

    public void setPid(BigDecimal pid) {
        this.pid = pid;
    }

    public BigDecimal getOrderflas() {
        return orderflas;
    }

    public void setOrderflas(BigDecimal orderflas) {
        this.orderflas = orderflas;
    }
}