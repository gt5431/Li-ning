package com.yc.lining.entity;
public class Product {
    private int pro_number;

    private String pro_name;

    private int typesid;

    private int pro_tagprice;

    private int proPrice;

    private String proImg;

    private String detail;

    private String registertime;

    private String removetime;

    private int registerflag;

    private int mid;

    private int stock;

    private String sex;

    private String proSize;

    private String color;

	public int getPro_number() {
		return pro_number;
	}

	public void setPro_number(int pro_number) {
		this.pro_number = pro_number;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getTypesid() {
		return typesid;
	}

	public void setTypesid(int typesid) {
		this.typesid = typesid;
	}

	public int getPro_tagprice() {
		return pro_tagprice;
	}

	public void setPro_tagprice(int pro_tagprice) {
		this.pro_tagprice = pro_tagprice;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public String getProImg() {
		return proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public String getRemovetime() {
		return removetime;
	}

	public void setRemovetime(String removetime) {
		this.removetime = removetime;
	}

	public int getRegisterflag() {
		return registerflag;
	}

	public void setRegisterflag(int registerflag) {
		this.registerflag = registerflag;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProSize() {
		return proSize;
	}

	public void setProSize(String proSize) {
		this.proSize = proSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Product [pro_number=" + pro_number + ", pro_name=" + pro_name
				+ ", typesid=" + typesid + ", pro_tagprice=" + pro_tagprice
				+ ", proPrice=" + proPrice + ", proImg=" + proImg + ", detail="
				+ detail + ", registertime=" + registertime + ", removetime="
				+ removetime + ", registerflag=" + registerflag + ", mid="
				+ mid + ", stock=" + stock + ", sex=" + sex + ", proSize="
				+ proSize + ", color=" + color + "]";
	}

   
}