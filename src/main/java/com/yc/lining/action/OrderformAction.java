package com.yc.lining.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Orderform;
import com.yc.lining.entity.Place;
import com.yc.lining.service.OrderformService;

@Controller("orderAction")
public class OrderformAction implements ModelDriven<Place>,SessionAware{

	@Autowired
	private OrderformService  orderService;
	private Map<String, Object> session;
	private Place place;
	private int buynumber;
	private double buyprice;
	private int pidnumber;
	private Orderform orderform;
	private int Buyamount;
	
	public int getBuyamount() {
		return Buyamount;
	}

	public void setBuyamount(int buyamount) {
		Buyamount = buyamount;
	}

	public Orderform getOrderform() {
		return orderform;
	}

	public void setOrderform(Orderform orderform) {
		this.orderform = orderform;
	}

	public int getBuynumber() {
		return buynumber;
	}

	public void setBuynumber(int buynumber) {
		this.buynumber = buynumber;
	}

	public double getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}

	public int getPidnumber() {
		return pidnumber;
	}

	public void setPidnumber(int pidnumber) {
		this.pidnumber = pidnumber;
	}

	public String order(){
		System.out.println("当前的购买数量为==>"+buynumber);
		List<Place> placeList = orderService.findPlaceByUid(101);
		session.put("placeList",placeList);
		return "orderSuccess";
	}
	
	public void hand(){
		System.out.println("pid==>"+pidnumber);
		Orderform orderform = new Orderform(buynumber,buyprice,pidnumber);
		int result = orderService.handOrder(orderform);
		System.out.println(result+"!");
		if(1==result){
			session.put("orderform", orderform);
			
		}else{
			
		}
	}
	
	public String addAdress(){
		int result = orderService.addNewAddress(place);
		if(result !=1){
			return "";
		}else{
			return "Addsuccess";
		}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public Place getModel() {
		place = new Place();
		return place;
	}
}
