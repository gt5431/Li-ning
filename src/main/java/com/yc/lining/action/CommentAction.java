package com.yc.lining.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Comments;
import com.yc.lining.service.CommentService;
@Controller("commentAction")
public class CommentAction implements ModelDriven<Comments>,SessionAware{
	@Autowired
	private CommentService commservice;
    private Comments comment;
	private Map<String, Object> session;
   
	//评价未评价的订单并且更新订单表里的信息
    public String comment1(){
		   try {
			   System.out.println(comment.getU_id());
			commservice.insertComment1(comment);
			return "commsuccess";
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   return "commfail";
    }
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public Comments getModel() {
		comment=new Comments();
		return comment;
	}
	
	
	
}