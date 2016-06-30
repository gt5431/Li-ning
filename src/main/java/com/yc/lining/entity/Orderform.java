package com.yc.lining.entity;

import java.util.Date;

public class Orderform {
    private int orderid;

    private Date riqi;

    private int uId;

    private int proNumber;

    private int buyNumber;

    private double buyprice;

    private int pid;

    private int orderflas;
    
    private int commentsflag;

    public int getCommentsflag() {
		return commentsflag;
	}

	public void setCommentsflag(int commentsflag) {
		this.commentsflag = commentsflag;
	}

	public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getRiqi() {
        return riqi;
    }

    public void setRiqi(Date riqi) {
        this.riqi = riqi;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getProNumber() {
        return proNumber;
    }

    public void setProNumber(int proNumber) {
        this.proNumber = proNumber;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getOrderflas() {
        return orderflas;
    }

    public void setOrderflas(int orderflas) {
        this.orderflas = orderflas;
    }

    
    
	@Override
	public String toString() {
		return "\nOrderform [orderid=" + orderid + ", riqi=" + riqi + ", uId="
				+ uId + ", proNumber=" + proNumber + ", buyNumber=" + buyNumber
				+ ", buyprice=" + buyprice + ", pid=" + pid + ", orderflas="
				+ orderflas + ", commentsflag=" + commentsflag + "]";
	}

	public Orderform(int buyNumber, double buyprice, int pid,int uid) {
		this.buyNumber = buyNumber;
		this.buyprice = buyprice;
		this.pid = pid;
		this.uId = uid;
	}

	public Orderform() {
		
	}
}
