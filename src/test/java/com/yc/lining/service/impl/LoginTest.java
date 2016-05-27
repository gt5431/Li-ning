package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class LoginTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testlogin() {
		Usersinfo usersinfo = new Usersinfo("ÀÏÍõ","aaa");
		usersinfo = userService.login(usersinfo);
		System.out.println(usersinfo);
		assertNotNull("µÇÂ¼Ê§°Ü",usersinfo);
	}

}
