package com.yc.lining.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Product;
import com.yc.lining.entity.Typesinfo;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.ProductService;
import com.yc.lining.service.TypeInfoService;
import com.yc.lining.service.UserService;

@Controller("userAction")
public class UserAction implements ModelDriven<Usersinfo>,SessionAware{
	@Autowired
	private UserService userService;
	@Autowired
	private TypeInfoService typeService;
	@Autowired
	private ProductService proservice;
	private Usersinfo usersinfo;
	private Map<String, Object> session;
	private Usersinfo usersinfo1;
	
	public Usersinfo getUsersinfo1() {
		return usersinfo1;
	}

	public void setUsersinfo1(Usersinfo usersinfo1) {
		this.usersinfo1 = usersinfo1;
	}

	public Usersinfo getUsersinfo() {
		return usersinfo;
	}

	public void setUsersinfo(Usersinfo usersinfo) {
		this.usersinfo = usersinfo;
	}

	public String login(){
		usersinfo = userService.login(usersinfo);
		if(usersinfo == null){
			session.put("loginMsg","登录失败");
			return "loginFail";
		}else{
			session.put("usersinfo",usersinfo);
			return "login";
		}
	}
	
	public String register(){
		usersinfo1 =  userService.login(usersinfo);
		//判断用户名是否已注册
		if(usersinfo1 != null){
			session.put("registerMsg","该用户已注册");
			return "success";
		}else{
			userService.register(usersinfo);
			return "success";
		}
	}
	//爆款推荐--页面加载显示的商品及类型
	public String list(){
		List<Product> products=proservice.getAllProduct();
		List<Typesinfo> types=typeService.getAllTypes();
		List<Product> sport=proservice.getSportPro();
		System.out.println(products);
		System.out.println(types);
	    session.put("Product", products);
	    session.put("Type", types);
	    session.put("Sport", sport);
		return "listAll";
	}

	@Override
	public Usersinfo getModel() {
		usersinfo = new Usersinfo();
		return usersinfo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
