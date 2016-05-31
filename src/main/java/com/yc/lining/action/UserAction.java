package com.yc.lining.action;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Collection02;
import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Item;
import com.yc.lining.entity.Product;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.GoodsinfoService;
import com.yc.lining.service.UserService;

@Controller("userAction")
public class UserAction implements ModelDriven<Usersinfo>,SessionAware{
	
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsinfoService goodsinfoService;
	
	private Usersinfo usersinfo;
	private List<Goodsinfo> collection;
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
		LogManager.getLogger().debug("登录");
		usersinfo = userService.login(usersinfo);
		if(usersinfo == null){
			session.put("loginMsg","登录失败");
			return "loginFail";
		}else{
			session.put("usersinfo",usersinfo);
			collection = goodsinfoService.CollectionById(usersinfo.getUid());
			System.out.println("收藏列表==》"+collection);
			session.put("collection",collection);
			return "login";
		}
	}
	
	public String loginout(){
		session.put("usersinfo",null);
		session.put("collection",null);
		return "loginout";
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
