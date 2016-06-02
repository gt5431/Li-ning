package com.yc.lining.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.yc.lining.entity.ProductBean;
import com.yc.lining.service.ProductService;
import com.yc.lining.util.PageUtil;

@Controller("productAction")
public class ProductAction implements ModelDriven<ProductBean>,SessionAware{
	
	@Autowired
	private ProductService productService;
	private int num;
	private String  firstSelect;
	private ProductBean product;
	private List<ProductBean> list;
	private PageUtil pageUtil;
	private List<Object> pageList;
	
	public List<Object> getPageList() {
		return pageList;
	}

	public List<ProductBean> getList() {
		return list;
	}

	public void setList(List<ProductBean> list) {
		this.list = list;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	public String getFirstSelect() {
		return firstSelect;
	}

	public void setFirstSelect(String firstSelect) {
		this.firstSelect = firstSelect;
	}
	
	private Map<String, Object> session;
	
	public String findAll(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		if(null==firstSelect||firstSelect.length()<=0){
			pageUtil=new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}else{
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
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public ProductBean getModel() {
		product=new ProductBean();
		return product;
	}

}
