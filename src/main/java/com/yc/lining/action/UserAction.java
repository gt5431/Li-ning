package com.yc.lining.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.UserService;

@Controller("userAction")
public class UserAction implements ModelDriven<Usersinfo>,SessionAware{
	@Autowired
	private UserService userService;
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
	
	public String list(){
		
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
