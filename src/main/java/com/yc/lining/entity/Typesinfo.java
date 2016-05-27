package com.yc.lining.entity;

import java.math.BigDecimal;

public class Typesinfo {
    private int typesid;

    private String typename;

    public int getTypesid() {
        return typesid;
    }

    public void setTypesid(int typesid) {
        this.typesid = typesid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

	@Override
	public String toString() {
		return "Typesinfo [typesid=" + typesid + ", typename=" + typename + "]";
	}
    
}