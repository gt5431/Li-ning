package com.yc.lining.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Manager;
import com.yc.lining.entity.Orderform;
import com.yc.lining.service.OrderService;

@Controller("orderformAction")
public class OrderformAction1 implements ModelDriven<Orderform>,SessionAware{
	private Orderform orderform;
	private Map<String, Object> session;
	@Autowired
	private OrderService orderService;
	private Object orders;
	public Object getOrders() {
		return orders;
	}
	public void setOrders(Object orders) {
		this.orders = orders;
	}
	public String listAllOrder(){
		System.out.println("you son of bitch");
		orders=orderService.listAllOrder();
		return "success";
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public Orderform getModel() {
		orderform=new Orderform();
		return orderform;
	}

}
