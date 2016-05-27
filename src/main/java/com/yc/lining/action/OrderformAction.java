package com.yc.lining.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Place;
import com.yc.lining.service.OrderformService;

@Controller("orderAction")
public class OrderformAction implements ModelDriven<Place>,SessionAware{

	@Autowired
	private OrderformService  orderService;
	private Map<String, Object> session;
	private Place place;
	
	public String order(){
		List<Place> placeList = orderService.findPlaceByUid(101);
		session.put("placeList",placeList);
		return "orderSuccess";
	}
	
	public String hand(){
		int result = orderService.handOrder();
		System.out.println("下单结果为==>"+result);
		return "Handsuccess";
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
