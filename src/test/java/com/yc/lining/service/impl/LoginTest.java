package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Product;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.ProductService;
import com.yc.lining.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class LoginTest {
	
	@Autowired
	private JavaMailSender javaMaiSender;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testlogin() {
		Usersinfo usersinfo = new Usersinfo("hello","aa");
		usersinfo = userService.login(usersinfo);
		System.out.println(usersinfo);
		assertNotNull("登录失败",usersinfo);
	}
	
	/*@Test
	public void testSendEmail_2() {
		MimeMessage mm = javaMaiSender.createMimeMessage();//邮件信息类
		try {
			MimeMessageHelper smm = new MimeMessageHelper(mm,true);
			smm.setFrom("studymail_test@163.com");//发送者
			smm.setTo("2769328353@qq.com");
			//smm.setTo("15886435708@139.com");//接受者
			smm.setSubject("你给我look吗");//主题
			smm.setText("111111111111111111111111<br>"+
					"<img src='cid:aaa'></img>"
					+"<a href='http://localhost:8080/Vote/vote.jsp'>点击链接即可激活</a><br>",true);//内容
			FileSystemResource fsss = new FileSystemResource("C:/Users/gt123/Desktop/kkkkkk123.jpg");
			smm.addInline("aaa",fsss);
			File file = new File("C:/Users/gt123/Desktop/事务.txt");
			smm.addAttachment("事务.txt",file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMaiSender.send(mm);
		System.out.println("发送成功....");
	}
	
	@Test
	public void testSendEmail_3() {
		MimeMessage mm = javaMaiSender.createMimeMessage();//邮件信息类
		try {
			MimeMessageHelper smm = new MimeMessageHelper(mm,true);
			smm.setFrom("studymail_test@163.com");//发送者
			smm.setTo("2769328353@qq.com");
			//smm.setTo("15886435708@139.com");//接受者
			smm.setSubject("你给我look吗");//主题
			smm.setText("<table width='100%' cellspacing='0' cellpadding='0'>"
					+ "<tbody><tr><td>"
					+ "<div style='padding:30px 55px 30px 55px;'>"
					+ "<h1 style='margin:0;color:#424242;font-size:32px;height:54px;line-height:54px;"
					+ "font-family:Microsoft Yahei,Arial;'>最后一步……</h1>"
					+ "<p style='padding:0;margin:5px 0 0;color:#424242;font-size:14px;line-height:24px;"
					+ "font-family:Microsoft Yahei,Arial;'>确认你的邮箱地址以完成你的豆丁账号注册。"
					+ "很简单——只需点击下面的按钮即可。</p>"
					+ "<div style='height:84px;background-color:#eaf3ff;margin:24px 0 0;'>"
					+ "<a title='立即验证' target='_blank' href='http://127.0.0.1:8080/AiGo/' "
					+ "style='display:block;height:84px;line-height:84px;color:#006bbd;"
					+ "text-align:center;font-size:22px;font-weight:bold;text-decoration:none;"
					+ "font-family:Microsoft Yahei,Arial;'>立即验证</a>"
					+ "<p style='color:#424242;font-size:14px;margin:30px 0 0;"
					+ "border-top:2px solid #f4f4f4;line-height:26px;padding:10px 0 0;"
					+ "font-family:Microsoft Yahei,Arial;'>"
					+ "也可以点击或复制下面的链接到浏览器的地址栏中完成验证：</p>"
					+ "<p style='word-break:break-all;line-height:26px;padding:0;"
					+ "margin:0;font-size:14px;'>"
					+ "<a target='_blank' href='http://127.0.0.1:8080/AiGo/' "
					+ "style='color:#006bbd;text-decoration:none;"
					+ "font-family:Microsoft Yahei,Arial;'>http://localhost:8080/AiGo</a></p></div>"
					+ "</tbody></tr></td></table>",true);
			FileSystemResource fsss = new FileSystemResource("C:/Users/gt123/Desktop/kkkkkk123.jpg");
			smm.addInline("aaa",fsss);
			File file = new File("C:/Users/gt123/Desktop/事务.txt");
			smm.addAttachment("事务.txt",file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMaiSender.send(mm);
		System.out.println("发送成功....");
	}
	
	

	@Test
	public void testListAllUser() {
		List<Usersinfo> list=userService.listAllUser();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"\n");
		}
		assertNotNull("xhadsji",list);

	}
	@Autowired
	private ProductService productService;
	@Test
	public void testlistAllProduct() {
		List<Product> list=productService.listAllProduct();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"\n");
		}
	}*/
}

