package com.yc.lining.action;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
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
      private int u_id;
	   private Map<String, Object> session;
	    private File image;                //图片文件
 	    private String imageFileName; //图片名字
	    private String  imageContentType;  //上传文件类型
	 
	
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	//评价未评价的订单并且更新订单表里的信息
    public String comment1(){
    System.out.println("评价信息为"+comment);
	String savePath=ServletActionContext.getServletContext().getRealPath("/upload");
    String path = "upload/"+imageFileName;
    comment.setComment_image(path); 
	try {
		FileUtils.copyFile(image, new File(savePath+File.separator+imageFileName));
		    
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
	try {
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