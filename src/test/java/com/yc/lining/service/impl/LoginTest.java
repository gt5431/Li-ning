package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.CommentBean;
import com.yc.lining.entity.ProComment;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.CommentService;
import com.yc.lining.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class LoginTest {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService s;
	@Test
	public void testlogin() {
		Usersinfo usersinfo = new Usersinfo("ÀÏÍõ","aaa");
		usersinfo = userService.login(usersinfo);
		System.out.println(usersinfo);
		assertNotNull("µÇÂ¼Ê§°Ü",usersinfo);
	}
	@Test
	public void testlogin1() {
	List <ProComment> comm1=s.selectCommentByProNum1(1);
	System.out.println(comm1);
	assertNotNull("µÇÂ¼Ê§°Ü",comm1);

	}
	@Test
	public void testlogin2() {
		CommentBean  comm1=s.selectCommentimg(3);
	   System.out.println(comm1);
	   assertNotNull("µÇÂ¼Ê§°Ü",comm1);

	}
}
