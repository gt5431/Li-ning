package com.yc.lining.entity;

public class Item {

	private int u_id;
	private int pro_number;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getPro_number() {
		return pro_number;
	}

	public void setPro_number(int pro_number) {
		this.pro_number = pro_number;
	}

	@Override
	public String toString() {
		return "\nItem [u_id=" + u_id + ", pro_number=" + pro_number + "]";
	}

	
}
