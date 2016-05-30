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
public class ProductAction implements ModelDriven<ProductBean>,SessionAware{

	@Autowired
	private ProductService productService;
	private Map<String, Object> session;
	private ProductBean product;
	private List<ProductBean> list;
	private PageUtil pageUtil;
	private List<Object> pageList;
	private int Buyamount;
	private int num = 0;
	private List<Product> list = new ArrayList<Product>();
	
	//展示详情
	public String details(){
		Product product_1 = productService.ProductDetailsById(product.getPro_number());
		String [] color =product_1.getColor().split(",");
		String [] img =product_1.getPro_img().split(",");
		session.put("goodsinfo", product_1);
		session.put("goodsinfocolor", color);
		session.put("goodsinfoimg", img);
		return "Finddetails";
	}

	//加入购物车(做为测试用)
	@SuppressWarnings("unchecked")
	public String cart(){
		System.out.println("加入购物车的数量==>"+Buyamount);
		boolean flag = false; //判断是否为同一件商品
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
				//暂时不处理
			}
		}
		session.put("cartList",list);
		return "Addcart";
	}
	
	//////////////多重查询///////////////(PageUtil)session.getAttribute("pageutil");
	
	/*//模糊查询
	public String searchInfo(){
		return "searchInfo";
	}*/
	
	public String findAll(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		list=productService.findPageUtil(pageUtil);
		session.put("pageUtil", pageUtil);
		session.put("products", list);
		return "success";
	}
	
	public String getCount(){
		int num=productService.getCount();
		session.put("num", num);
		return "num";
	}
	
	//根据新品分页查询
	public void findByDate(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		System.out.println("ssion====================="+pageUtil+"num"+num);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil", pageUtil);
		List<ProductBean> products=productService.findByDate(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("价格排序==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
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
	
	
	//按价格降序分页查询
	public void findByPriceDesc(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		System.out.println("ssion====================="+pageUtil+"num"+num);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil", pageUtil);
		List<ProductBean> products=productService.findByPriceDesc(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("价格降序==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
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
	
	//根据商品名分页查询
	public void findByType(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		System.out.println("ssion====================="+pageUtil+"num"+num);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil", pageUtil);
		List<ProductBean> products=productService.findByType(product,pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		
		System.out.println("模糊查询==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
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
	
	public void findByPrice(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		System.out.println("ssion====================="+pageUtil+"分页num===>"+num);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil", pageUtil);
		List<ProductBean> products=productService.findByPrice(product,pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("价格区间查询==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
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
	
	public String findByPage(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		System.out.println("session111==================="+pageUtil);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		System.out.println("session111==================="+pageUtil);
		list=productService.findPageUtil(pageUtil);
		session.put("pageUtil", pageUtil);
		session.put("list", list);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(list);
		System.out.println("productsList==>"+list);
		System.out.println("当前的pageUtil内容为==>"+pageUtil);
		return "success";
	}

	@Override
	public ProductBean getModel() {
		product=new ProductBean();
		return product;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session  = session;
	}
	
}
