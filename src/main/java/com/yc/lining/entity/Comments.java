package com.yc.lining.entity;

import java.io.Serializable;


public class Comments implements Serializable{
	private int comment_id;
	private int orderid;
	private String comment_content;
	private int u_id;


	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	
	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	@Override
	public String toString() {
		return "Comments [comment_id=" + comment_id + ", orderid=" + orderid
		+ ", comment_content=" + comment_content + ", u_id=" + u_id +"]";
	}

}