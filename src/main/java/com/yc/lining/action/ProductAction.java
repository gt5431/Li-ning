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
	
	//������Ʒ��ҳ��ѯ
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
		System.out.println("�۸�����==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
	
	
	//���۸����ҳ��ѯ
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
		System.out.println("�۸���==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
	
	//������Ʒ����ҳ��ѯ
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
		
		System.out.println("ģ����ѯ==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
		System.out.println("ssion====================="+pageUtil+"��ҳnum===>"+num);
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
		System.out.println("�۸������ѯ==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
		System.out.println("��ǰ��pageUtil����Ϊ==>"+pageUtil);
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
