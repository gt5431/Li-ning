package com.yc.lining.entity;

import java.math.BigDecimal;

public class Product {
    private int proNumber;

    private String proName;

    private BigDecimal typesid;

    private BigDecimal proTagprice;

    private BigDecimal proPrice;

    private String proImg;

    private String detail;

    private String registertime;

    private String removetime;

    private BigDecimal registerflag;

    private BigDecimal mid;

    private BigDecimal stock;

    private String sex;

    private String proSize;

    private String color;

    public int getProNumber() {
		return proNumber;
	}

	public void setProNumber(int proNumber) {
		this.proNumber = proNumber;
	}

	public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public BigDecimal getTypesid() {
        return typesid;
    }

    public void setTypesid(BigDecimal typesid) {
        this.typesid = typesid;
    }

    public BigDecimal getProTagprice() {
        return proTagprice;
    }

    public void setProTagprice(BigDecimal proTagprice) {
        this.proTagprice = proTagprice;
    }

    public BigDecimal getProPrice() {
        return proPrice;
    }

    public void setProPrice(BigDecimal proPrice) {
        this.proPrice = proPrice;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg == null ? null : proImg.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime == null ? null : registertime.trim();
    }

    public String getRemovetime() {
        return removetime;
    }

    public void setRemovetime(String removetime) {
        this.removetime = removetime == null ? null : removetime.trim();
    }

    public BigDecimal getRegisterflag() {
        return registerflag;
    }

    public void setRegisterflag(BigDecimal registerflag) {
        this.registerflag = registerflag;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getProSize() {
        return proSize;
    }

    public void setProSize(String proSize) {
        this.proSize = proSize == null ? null : proSize.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }
}