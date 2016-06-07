package com.yc.lining.entity;

import java.io.Serializable;

public class Collection02 implements Serializable{

	private static final long serialVersionUID = 1794589020399617696L;
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
	
	
	
	public Collection02(int u_id, int pro_number) {
		
		this.u_id = u_id;
		this.pro_number = pro_number;
	}
	public Collection02() {
		
	}
	@Override
	public String toString() {
		return "\nCollection02 [u_id=" + u_id + ", pro_number=" + pro_number
				+ "]";
	}
	
	
}
