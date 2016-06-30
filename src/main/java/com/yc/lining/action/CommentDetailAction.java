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
import com.yc.lining.entity.CommentBean;
import com.yc.lining.entity.Comments;
import com.yc.lining.entity.ProComment;
import com.yc.lining.entity.Product;
import com.yc.lining.service.CommentService;
@Controller("commentDetailAction")
public class CommentDetailAction implements ModelDriven<ProComment>,SessionAware{
	@Autowired
	private CommentService commservice;
    private ProComment procomment;
	private Map<String, Object> session;
    private int pro_number;
	public void setPro_number(int pro_number) {
		this.pro_number = pro_number;
	}
	//在商品详情页面显示某商品下的所有评论
    public void detailping(){
		List<ProComment> comm=commservice.selectCommentByProNum1(pro_number);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(comm);
		//取到响应对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset=utf-8");

		try {
			PrintWriter out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public ProComment getModel() {
		procomment=new ProComment();
		return procomment;
	}
	
	
	
}