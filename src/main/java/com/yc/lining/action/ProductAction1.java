package com.yc.lining.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Product;
import com.yc.lining.service.ProductService;
@Controller("productAction")
public class ProductAction1 implements SessionAware, ModelDriven<Product> {
	
	@Autowired
	private ProductService productService;
	
	private Product product;
	Map<String, Object> session;
	private Object products;
	private File pro_colorpic1;
	private String pro_colorpic1FileName;
	private String pro_colorpic1ContentType;

	private File pro_pic1;
	private String pro_pic1FileName;
	private String pro_pic1ContentType;
	
	private File pro_pic;
	private String pro_picFileName;
	private String pro_picContentType;
	
	private File pro_colorpic;
	private String pro_colorpicFileName;
	private String pro_colorpicContentType;
	
	public Object getProducts() {
		return products;
	}

	public File getPro_colorpic1() {
		return pro_colorpic1;
	}

	public void setPro_colorpic1(File pro_colorpic1) {
		this.pro_colorpic1 = pro_colorpic1;
	}

	public String getPro_colorpic1FileName() {
		return pro_colorpic1FileName;
	}

	public void setPro_colorpic1FileName(String pro_colorpic1FileName) {
		this.pro_colorpic1FileName = pro_colorpic1FileName;
	}

	public String getPro_colorpic1ContentType() {
		return pro_colorpic1ContentType;
	}

	public void setPro_colorpic1ContentType(String pro_colorpic1ContentType) {
		this.pro_colorpic1ContentType = pro_colorpic1ContentType;
	}

	public File getPro_pic1() {
		return pro_pic1;
	}

	public void setPro_pic1(File pro_pic1) {
		this.pro_pic1 = pro_pic1;
	}

	public String getPro_pic1ContentType() {
		return pro_pic1ContentType;
	}

	public void setPro_pic1ContentType(String pro_pic1ContentType) {
		this.pro_pic1ContentType = pro_pic1ContentType;
	}

	public String getPro_pic1FileName() {
		return pro_pic1FileName;
	}

	public void setPro_pic1FileName(String pro_pic1FileName) {
		this.pro_pic1FileName = pro_pic1FileName;
	}

	public void setProducts(Object products) {
		this.products = products;
	}


	public String listAllProduct() {
		products = productService.listAllProduct();
		return "productsJson";
	}

	public String removeProduct() {
		products = productService.removeProduct(product);
		return "productsJson";
	}

	public String updateProduct() {
		String savePath = ServletActionContext.getServletContext().getRealPath("/upload");
		LogManager.getLogger().debug("上传服务器的位置是：" + savePath);
		String path="upload/"+pro_colorpic1FileName;
		String path1="upload/"+pro_pic1FileName;
		product.setColor(path);
		product.setPro_img(path1);
		
		try {
			FileUtils.copyFile(pro_pic1, new File(savePath + File.separator + pro_pic1FileName));
			System.out.println(savePath + File.separator+ pro_pic1FileName);
			FileUtils.copyFile(pro_colorpic1, new File(savePath + File.separator + pro_colorpic1FileName));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.out.println("将要上架的product为==>"+product);
			products=productService.updateProduct(product);
			System.out.println("aaaa");
		}
		
		return "productsJson";
	}
	
	public String addProduct(){
		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		String path="upload/"+pro_colorpicFileName;
		String path1="upload/"+pro_picFileName;
		product.setColor(path);
		product.setPro_img(path1);
		product.setPro_number(product.getPro_number());
		product.setPro_name(product.getPro_name());
		product.setTypesid(product.getTypesid());
		product.setPro_tagprice(product.getPro_tagprice());
		product.setPro_price(product.getPro_price());
		product.setDetail(product.getDetail());
		product.setRegistertime(product.getRegistertime());
		product.setRemovetime(product.getRemovetime());
		product.setRegisterflag(product.getRegisterflag());
		product.setMid(product.getMid());
		product.setStock(product.getStock());
		product.setSex(product.getSex());
		product.setProSize(product.getProSize());
		product.setTypename(product.getTypename());
		System.out.println("!!!!!!!!!!"+product.getRegistertime());
		System.out.println("!!!!!!!!!!"+product.getRemovetime());

		LogManager.getLogger().debug("取出的参数"+pro_colorpicFileName+"---"+path);
		LogManager.getLogger().debug("取出的参数"+pro_picFileName+"---"+path1);
		try {
			
				FileUtils.copyFile(pro_pic, new File(savePath + File.separator
						+ pro_picFileName));
		
			
				FileUtils.copyFile(pro_colorpic, new File(savePath + File.separator
						+ pro_colorpicFileName));
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		products=productService.addProduct(product);
		return "success";
	}
	
	
	public File getPro_pic() {
		return pro_pic;
	}

	public void setPro_pic(File pro_pic) {
		this.pro_pic = pro_pic;
	}

	public String getPro_picFileName() {
		return pro_picFileName;
	}

	public void setPro_picFileName(String pro_picFileName) {
		this.pro_picFileName = pro_picFileName;
	}

	public String getPro_picContentType() {
		return pro_picContentType;
	}

	public void setPro_picContentType(String pro_picContentType) {
		this.pro_picContentType = pro_picContentType;
	}

	public File getPro_colorpic() {
		return pro_colorpic;
	}

	public void setPro_colorpic(File pro_colorpic) {
		this.pro_colorpic = pro_colorpic;
	}

	public String getPro_colorpicFileName() {
		return pro_colorpicFileName;
	}

	public void setPro_colorpicFileName(String pro_colorpicFileName) {
		this.pro_colorpicFileName = pro_colorpicFileName;
	}

	public String getPro_colorpicContentType() {
		return pro_colorpicContentType;
	}

	public void setPro_colorpicContentType(String pro_colorpicContentType) {
		this.pro_colorpicContentType = pro_colorpicContentType;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public Product getModel() {
		product = new Product();
		return product;
	}
}
