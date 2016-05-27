package com.yc.lining.entity;

import java.io.Serializable;


public class Place implements Serializable{
	private static final long serialVersionUID = 3241560262523390276L;
	
	private int pid;
    private String placename;
    private int uid;
    private String shname;
    private String shtel;
    private int postcode;
    private String email;
    
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getShname() {
		return shname;
	}
	public void setShname(String shname) {
		this.shname = shname;
	}
	public String getShtel() {
		return shtel;
	}
	public void setShtel(String shtel) {
		this.shtel = shtel;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public String toString() {
		return "\nPlace [pid=" + pid + ", placename=" + placename + ", uid="
				+ uid + ", shname=" + shname + ", shtel=" + shtel
				+ ", postcode=" + postcode + "]";
	}
	
	public Place(int pid, String placename, int uid, String shname,
			String shtel, int postcode) {
		this.pid = pid;
		this.placename = placename;
		this.uid = uid;
		this.shname = shname;
		this.shtel = shtel;
		this.postcode = postcode;
	}
	
	public Place() {
		
	}
	
	public Place(String placename,String shname,String shtel, int postcode) {
		this.placename = placename;
		this.shname = shname;
		this.shtel = shtel;
		this.postcode = postcode;
	}
}