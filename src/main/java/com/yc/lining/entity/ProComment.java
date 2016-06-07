package com.yc.lining.entity;

import java.io.Serializable;

public class ProComment implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private String comment_date;
	private String comment_content;
	private String username;
    private int u_id;
    
    
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "ProComment [comment_date=" + comment_date
				+ ", comment_content=" + comment_content + ", username=" + username
				+ "]";
	}
}
