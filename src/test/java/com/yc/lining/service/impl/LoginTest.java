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
		assertNotNull("��¼ʧ��",usersinfo);
	}
	
	/*@Test
	public void testSendEmail_2() {
		MimeMessage mm = javaMaiSender.createMimeMessage();//�ʼ���Ϣ��
		try {
			MimeMessageHelper smm = new MimeMessageHelper(mm,true);
			smm.setFrom("studymail_test@163.com");//������
			smm.setTo("2769328353@qq.com");
			//smm.setTo("15886435708@139.com");//������
			smm.setSubject("�����look��");//����
			smm.setText("111111111111111111111111<br>"+
					"<img src='cid:aaa'></img>"
					+"<a href='http://localhost:8080/Vote/vote.jsp'>������Ӽ��ɼ���</a><br>",true);//����
			FileSystemResource fsss = new FileSystemResource("C:/Users/gt123/Desktop/kkkkkk123.jpg");
			smm.addInline("aaa",fsss);
			File file = new File("C:/Users/gt123/Desktop/����.txt");
			smm.addAttachment("����.txt",file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMaiSender.send(mm);
		System.out.println("���ͳɹ�....");
	}
	
	@Test
	public void testSendEmail_3() {
		MimeMessage mm = javaMaiSender.createMimeMessage();//�ʼ���Ϣ��
		try {
			MimeMessageHelper smm = new MimeMessageHelper(mm,true);
			smm.setFrom("studymail_test@163.com");//������
			smm.setTo("2769328353@qq.com");
			//smm.setTo("15886435708@139.com");//������
			smm.setSubject("�����look��");//����
			smm.setText("<table width='100%' cellspacing='0' cellpadding='0'>"
					+ "<tbody><tr><td>"
					+ "<div style='padding:30px 55px 30px 55px;'>"
					+ "<h1 style='margin:0;color:#424242;font-size:32px;height:54px;line-height:54px;"
					+ "font-family:Microsoft Yahei,Arial;'>���һ������</h1>"
					+ "<p style='padding:0;margin:5px 0 0;color:#424242;font-size:14px;line-height:24px;"
					+ "font-family:Microsoft Yahei,Arial;'>ȷ����������ַ�������Ķ����˺�ע�ᡣ"
					+ "�ܼ򵥡���ֻ��������İ�ť���ɡ�</p>"
					+ "<div style='height:84px;background-color:#eaf3ff;margin:24px 0 0;'>"
					+ "<a title='������֤' target='_blank' href='http://127.0.0.1:8080/AiGo/' "
					+ "style='display:block;height:84px;line-height:84px;color:#006bbd;"
					+ "text-align:center;font-size:22px;font-weight:bold;text-decoration:none;"
					+ "font-family:Microsoft Yahei,Arial;'>������֤</a>"
					+ "<p style='color:#424242;font-size:14px;margin:30px 0 0;"
					+ "border-top:2px solid #f4f4f4;line-height:26px;padding:10px 0 0;"
					+ "font-family:Microsoft Yahei,Arial;'>"
					+ "Ҳ���Ե��������������ӵ�������ĵ�ַ���������֤��</p>"
					+ "<p style='word-break:break-all;line-height:26px;padding:0;"
					+ "margin:0;font-size:14px;'>"
					+ "<a target='_blank' href='http://127.0.0.1:8080/AiGo/' "
					+ "style='color:#006bbd;text-decoration:none;"
					+ "font-family:Microsoft Yahei,Arial;'>http://localhost:8080/AiGo</a></p></div>"
					+ "</tbody></tr></td></table>",true);
			FileSystemResource fsss = new FileSystemResource("C:/Users/gt123/Desktop/kkkkkk123.jpg");
			smm.addInline("aaa",fsss);
			File file = new File("C:/Users/gt123/Desktop/����.txt");
			smm.addAttachment("����.txt",file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMaiSender.send(mm);
		System.out.println("���ͳɹ�....");
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

