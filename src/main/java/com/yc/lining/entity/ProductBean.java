package com.yc.lining.entity;

import java.io.Serializable;

public class ProductBean implements Serializable{
	private static final long serialVersionUID = 3950350070727739610L;
	
		private int pro_number;  
	    private String pro_name;     
	    private int typesid;                           
	    private int pro_tagprice;                               
	    private int pro_price;					
	    private String pro_img;                           
	    private String detail;                               
	    private String registertime;                                  
	    private String removetime ;                                    
	    private int registerflag;                            
	    private int mid;                                          
	    private int stock;                                         
	    private String sex;                                     
	    private String pro_size;                            
	    private String color;
	    private int pageNo;//页号
		private int pageSize;//页面记录数
		private int totalSize;//总条数 getCount()/totalPage
		private int totalPage;//总页数
		private double lowPrice;
		private double highPrice;
		private String searchName; //用于模糊查询的名字
		
		public String getSearchName() {
			return searchName;
		}
		public void setSearchName(String searchName) {
			this.searchName = searchName;
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
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getRegistertime() {
			return registertime;
		}
		public void setRegistertime(String registertime) {
			this.registertime = registertime;
		}
		public String getRemovetime() {
			return removetime;
		}
		public void setRemovetime(String removetime) {
			this.removetime = removetime;
		}
		public int getRegisterflag() {
			return registerflag;
		}
		public void setRegisterflag(int registerflag) {
			this.registerflag = registerflag;
		}
		public int getMid() {
			return mid;
		}
		public void setMid(int mid) {
			this.mid = mid;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getPro_size() {
			return pro_size;
		}
		public void setPro_size(String pro_size) {
			this.pro_size = pro_size;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getTotalSize() {
			return totalSize;
		}
		public void setTotalSize(int totalSize) {
			this.totalSize = totalSize;
		}
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public double getLowPrice() {
			return lowPrice;
		}
		public void setLowPrice(double lowPrice) {
			this.lowPrice = lowPrice;
		}
		public double getHighPrice() {
			return highPrice;
		}
		public void setHighPrice(double highPrice) {
			this.highPrice = highPrice;
		}
		@Override
		public String toString() {
			return "\nProductBean [pro_number=" + pro_number + ", pro_name="
					+ pro_name + ", typesid=" + typesid + ", pro_tagprice="
					+ pro_tagprice + ", pro_price=" + pro_price + ", pro_img="
					+ pro_img + ", detail=" + detail + ", registertime="
					+ registertime + ", removetime=" + removetime
					+ ", registerflag=" + registerflag + ", mid=" + mid
					+ ", stock=" + stock + ", sex=" + sex + ", pro_size="
					+ pro_size + ", color=" + color + ", pageNo=" + pageNo
					+ ", pageSize=" + pageSize + ", totalSize=" + totalSize
					+ ", totalPage=" + totalPage + ", lowPrice=" + lowPrice
					+ ", highPrice=" + highPrice + "]";
		}
		public ProductBean(int pro_number, String pro_name, int pro_tagprice,
				int pro_price, String pro_img, String color, int pageNo,
				int pageSize, int totalSize, int totalPage, double lowPrice,
				double highPrice) {
			this.pro_number = pro_number;
			this.pro_name = pro_name;
			this.pro_tagprice = pro_tagprice;
			this.pro_price = pro_price;
			this.pro_img = pro_img;
			this.color = color;
			this.pageNo = pageNo;
			this.pageSize = pageSize;
			this.totalSize = totalSize;
			this.totalPage = totalPage;
			this.lowPrice = lowPrice;
			this.highPrice = highPrice;
		}
		
		public ProductBean(int pageNo, int pageSize) {
			this.pageNo = pageNo;
			this.pageSize = pageSize;
		}
		public ProductBean() {
			
		}	 
		
}