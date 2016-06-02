package com.yc.lining.entity;

import java.io.Serializable;
import java.util.Date;

public class Usersinfo implements Serializable{
	private static final long serialVersionUID = -450868104654563968L;
	private int uid;
	private String username;
	private String password;
	private String realName;
	private String sex;
	private Date birthday;
	private String email;
	private String phone;
	private String balance;
	private int score;
	private String expendTotal;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setExpendTotal(String expendTotal) {
		this.expendTotal = expendTotal;
	}
	
	public Usersinfo(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Usersinfo() {
		
	}
	
	@Override
	public String toString() {
		return "\nUsersinfo [uid=" + uid + ", username=" + username
				+ ", password=" + password + ", realName=" + realName
				+ ", sex=" + sex + ", birthday=" + birthday + ", email="
				+ email + ", phone=" + phone + ", balance=" + balance
				+ ", score=" + score + ", expendTotal=" + expendTotal + "]";
	}
}
