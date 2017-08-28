package com.yc.lining.entity;

import java.math.BigDecimal;

public class Place1 {
    @Override
	public String toString() {
		return "Place [pid=" + pid + ", placename=" + placename + ", u_id="
				+ u_id + ", shname=" + shname + ", shtel=" + shtel
				+ ", postcode=" + postcode + "]";
	}

	private int pid;

    private String placename;

    private int u_id;

    private String shname;

    private String shtel;

    private int postcode;

    
    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename == null ? null : placename.trim();
    }

  

    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname == null ? null : shname.trim();
    }

    public String getShtel() {
        return shtel;
    }

    public void setShtel(String shtel) {
        this.shtel = shtel == null ? null : shtel.trim();
    }

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

  
}