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
	private int Buyamount;
	private int num = 0;
	private List<Product> list = new ArrayList<Product>();
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBuyamount() {
		return Buyamount;
	}

	public void setBuyamount(int buyamount) {
		Buyamount = buyamount;
	}

	//չʾ����
	public String details(){
		Product product_1 = productService.ProductDetailsById(product.getPro_number());
		String [] color =product_1.getColor().split(",");
		String [] img =product_1.getPro_img().split(",");
		session.put("goodsinfo", product_1);
		session.put("goodsinfocolor", color);
		session.put("goodsinfoimg", img);
		return "Finddetails";
	}

	//���빺�ﳵ(��Ϊ������)
	@SuppressWarnings("unchecked")
	public String cart(){
		System.out.println("���빺�ﳵ������==>"+Buyamount);
		boolean flag = false; //�ж��Ƿ�Ϊͬһ����Ʒ
		if(session.get("cartList") == null){
			Product product1 = productService.ProductDetailsById(product.getPro_number());
			list.add(product1);
		}else{
			List<Product>  list1 = new ArrayList<Product>();
			list1 = (List<Product>) session.get("cartList");
			for(int i=0;i<list1.size();i++){
				if(product.getPro_number() == list1.get(i).getPro_number()){
					flag =true;
				}else{
					flag =false;
				}
			}
			
			if(false ==flag){
				Product product3 = productService.ProductDetailsById(product.getPro_number());
				list.add(product3);
			}else{
				//��ʱ������
			}
		}
		session.put("cartList",list);
		return "Addcart";
	}
	
	//////////////���ز�ѯ///////////////(PageUtil)session.getAttribute("pageutil");
	
	/*//ģ����ѯ
	public String searchInfo(){
		return "searchInfo";
	}*/
	
	
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
