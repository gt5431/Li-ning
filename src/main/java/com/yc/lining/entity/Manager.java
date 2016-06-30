package com.yc.lining.entity;

public class Manager {
	private int mid;

	private String mname;

	private String mpwd;

	public Manager() {
	}

	public Manager(int mid, String mname, String mpwd) {
		this.mid = mid;
		this.mname = mname;
		this.mpwd = mpwd;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpwd() {
		return mpwd;
	}

	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}

	@Override
	public String toString() {
		return "Manager [mid=" + mid + ", mname=" + mname + ", mpwd=" + mpwd
				+ "]";
	}
}