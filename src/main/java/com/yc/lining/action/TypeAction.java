package com.yc.lining.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Manager;
import com.yc.lining.entity.Typesinfo;
import com.yc.lining.service.TypeService;
@Controller("typeAction")
public class TypeAction implements ModelDriven<Typesinfo>,SessionAware {
	private Typesinfo type;
	private Map<String, Object> session;
	@Autowired
	private TypeService  typeService;
	private Object types;
	
	public Object getTypes() {
		return types;
	}

	public void setTypes(Object types) {
		this.types = types;
	}
	
	public String listAllType(){
		types=typeService.listType();
		System.out.println(types);
		
		return "success";
	}
	
	public String addType(){
		types=typeService.addType(type);
		return "success";
	}
	
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	@Override
	public Typesinfo getModel() {
		type=new Typesinfo();
		return type;
	}

}
