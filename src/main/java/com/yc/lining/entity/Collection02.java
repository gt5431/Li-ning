package com.yc.lining.entity;

import java.io.Serializable;
import java.util.List;

public class Collection02 implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int pro_number;
	private String pro_name;
	private int pro_tagprice;
	private int pro_price;
	private String pro_img;
	private String color;
	
	private List<Goodsinfo> goodsinfos;

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

	public int getPro_tagprice() {
		return pro_tagprice;
	}

	public void setPro_tagprice(int pro_tagprice) {
		this.pro_tagprice = pro_tagprice;
	}

	public int getPro_price() {
		return pro_price;
	}

	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Goodsinfo> getGoodsinfos() {
		return goodsinfos;
	}

	public void setGoodsinfos(List<Goodsinfo> goodsinfos) {
		this.goodsinfos = goodsinfos;
	}

	@Override
	public String toString() {
		return "\nCollection02 [pro_number=" + pro_number + ", pro_name="
				+ pro_name + ", pro_tagprice=" + pro_tagprice + ", pro_price="
				+ pro_price + ", pro_img=" + pro_img + ", color=" + color
				+ ", goodsinfos=" + goodsinfos + "]";
	}

	
}
