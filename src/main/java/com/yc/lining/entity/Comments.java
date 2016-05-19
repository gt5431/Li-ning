package com.yc.lining.entity;

import java.math.BigDecimal;

public class Comments {
    private BigDecimal orderid;

    private String coment;

    public BigDecimal getOrderid() {
        return orderid;
    }

    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment == null ? null : coment.trim();
    }
}