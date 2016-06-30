package com.yc.lining.action;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.CommentBean;
import com.yc.lining.entity.Comments;
import com.yc.lining.service.CommentService;
@Controller("myningAction")
public class MyningAction implements ModelDriven<CommentBean>,SessionAware{
	@Autowired
	private CommentService commservice;
    private CommentBean commentbean;
	private Map<String, Object> session;
    private int u_id;
  
 
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	//去会员中心查看未评价的订单
    public String comment(){
    	System.out.println("为评价--》"+commentbean);
		List<CommentBean> comm=commservice.selectNoComment(101);
		session.put("OrderList", comm);
	    return "listorder";
    }
    public String nocomment(){
    	System.out.println(commentbean.getOrderid());
    	CommentBean  comm1=commservice.selectCommentimg(commentbean.getOrderid());
		session.put("OrderImg", comm1);
        return "comm";
    }
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public CommentBean getModel() {
		commentbean=new CommentBean();
		return commentbean;
	}
	
	
	
}