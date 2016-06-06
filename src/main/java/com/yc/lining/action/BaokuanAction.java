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
import com.yc.lining.service.ProductService;
import com.yc.lining.service.TypeInfoService;

@Controller("baokuanAction")
public class BaokuanAction implements ModelDriven<Product>,SessionAware{
	@Autowired
	private TypeInfoService typesService;
	@Autowired
	private ProductService productService;

	private Product product;
	private Map<String, Object> session;

	//爆款推荐下的分类商品
	public void getProById(){
		//取到响应的数据，根据书签id取到对应的网站
		List<Product> pro = productService.getAllProById(product.getTypesid());
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pro);//把对象转换成json字符串
		//取到响应对象
		HttpServletResponse response = ServletActionContext.getResponse();//取到响应对象
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
	public Product getModel() {
		product = new Product();
		return product;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		
		this.session = session;
	}
}

