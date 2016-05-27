package com.yc.lining.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Product;
import com.yc.lining.service.ProductService;

@Controller("proAction")
public class ProductAction implements ModelDriven<Product>,SessionAware{

	@Autowired
	private ProductService productService;
	private Map<String, Object> session;
	private Product product;
	private List<Product> list = new ArrayList<Product>();

	//展示详情
	public String details(){
		return "details";
	}

	//加入购物车(做为测试用)
	@SuppressWarnings("unchecked")
	public String cart(){
		if(session.get("cartList") == null){
			Product product1 = productService.ProductDetailsById(product.getPro_number());
			list.add(product1);
		}else{
			List<Product>  list1 = new ArrayList<Product>();
			list1 = (List<Product>) session.get("cartList");
			for(int i=0;i<list1.size();i++){
				if(product.getPro_number() == list1.get(i).getPro_number()){
					break;
				}else{
					Product product3 = productService.ProductDetailsById(product.getPro_number());
					list.add(product3);
				}
			}
		}
		session.put("cartList",list);
		return "Addcart";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session  = session;
	}

	@Override
	public Product getModel() {
		product = new Product();
		return product;
	}
}
