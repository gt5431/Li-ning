package com.yc.lining.action;


import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Manager;
import com.yc.lining.service.ManagerService;

@Controller("managerAction")
public class ManagerAction implements ModelDriven<Manager>,SessionAware {
	
	
	
	
	private Manager manager;
	@Autowired
	private ManagerService managerService;
	private Map<String, Object> session;
	
	
	public String login(){
		Manager manager1 = managerService.login(manager);
		System.out.println("manager1==>"+manager1);
		if(manager1==null){
			session.put("loginwrong", "用户名或密码错误！！");
			return "mlogin";
		}
		session.put("manager", manager1);
		return "mloginSuccess";
	}
	
	private Object managers;
	
	public Object getManagers() {
		return managers;
	}

	public String listAllManager(){
		managers = managerService.listAll();
		return "success";
//		for(int i=0;i<managers.size();i++){
//			System.out.println(managers.get(i));
//		}
//		if(managers==null){
//			session.put("nodata", "暂无数据");
//		}
//		session.put("managers",managers);
//		return "cxManagersuccess";
	}
	
	public String addManager(){
		managers = managerService.addManager(manager);
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	@Override
	public Manager getModel() {
		manager=new Manager();
		return manager;
	}

}
