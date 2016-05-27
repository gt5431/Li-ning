package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Place;
import com.yc.lining.service.OrderformService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class OrderformServiceImplTest {
	
	@Autowired
	private OrderformService orderService;
	
	@Test
	public void testFindPlaceByUid() {
		List<Place> list = orderService.findPlaceByUid(101);
		System.out.println(list);
		assertNotNull("收货地址为空",list);
	}

}
