package com.yc.lining.entity;

import java.math.BigDecimal;

public class Typesinfo {
    private BigDecimal typesid;

    private String typename;

    public BigDecimal getTypesid() {
        return typesid;
    }

    public void setTypesid(BigDecimal typesid) {
        this.typesid = typesid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }
}