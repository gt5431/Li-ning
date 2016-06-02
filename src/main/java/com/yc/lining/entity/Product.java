package com.yc.lining.entity;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 8375699147090209033L;

	private int pro_number;

	private String pro_name;

	private int typesid;

	private double pro_tagprice;

	private double pro_price;

	private String pro_img;

	private String detail;

	private String registertime;

	private String removetime;

	private int registerflag;

	private int mid;

	private int stock;

	private String sex;

	private String proSize;

	private String color;
	private int Buyamount; //¹ºÂòÊýÁ¿
	
	public int getBuyamount() {
		return Buyamount;
	}

	public void setBuyamount(int buyamount) {
		Buyamount = buyamount;
	}

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

	public double getPro_tagprice() {
		return pro_tagprice;
	}

	public void setPro_tagprice(double pro_tagprice) {
		this.pro_tagprice = pro_tagprice;
	}

	public double getPro_price() {
		return pro_price;
	}

	public void setPro_price(double pro_price) {
		this.pro_price = pro_price;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
		return "\nProduct [pro_number=" + pro_number + ", pro_name=" + pro_name
				+ ", typesid=" + typesid + ", pro_tagprice=" + pro_tagprice
				+ ", pro_price=" + pro_price + ", pro_img=" + pro_img
				+ ", detail=" + detail + ", registertime=" + registertime
				+ ", removetime=" + removetime + ", registerflag="
				+ registerflag + ", mid=" + mid + ", stock=" + stock + ", sex="
				+ sex + ", proSize=" + proSize + ", color=" + color
				+ ", Buyamount=" + Buyamount + "]";
	}

	public Product() {

	}
}