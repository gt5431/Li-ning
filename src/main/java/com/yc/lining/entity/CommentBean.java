package com.yc.lining.entity;

import java.io.Serializable;

public class CommentBean implements Serializable {
	      private int orderid;
	      private String riqi;
	      private int pro_number;
	      private String pro_name;
	      private String color;
		  public int getOrderid() {
			return orderid;
		  }
		public void setOrderid(int orderid) {
			this.orderid = orderid;
		}
		public String getRiqi() {
			return riqi;
		}
		public void setRiqi(String riqi) {
			this.riqi = riqi;
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
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		@Override
		public String toString() {
			return "CommentBean [orderid=" + orderid + ", riqi=" + riqi
					+ ", pro_number=" + pro_number + ", pro_name=" + pro_name
					+ ", color=" + color + "]";
		}
}
