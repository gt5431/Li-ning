package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Place;
import com.yc.lining.service.OrderformService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class AddAddressTest {
	
	@Autowired
	private OrderformService orderService;
	
	@Test
	public void testAddNewAddress() {
		Place place = new Place("湖南衡阳","李四","15886434",471100);
		System.out.println(place);
		int result = orderService.addNewAddress(place);
		System.out.println("插入结果为==>"+result);
		assertNotNull("插入失败",result);
	}

}
