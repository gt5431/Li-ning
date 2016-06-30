package com.yc.lining.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Orderform;
import com.yc.lining.entity.Typesinfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TypeServiceTest1 {
	@Autowired
	private TypeService typeService;
	@Autowired
	private OrderService orderService;
	@Test
	public void testListAllType() {
		List<Typesinfo> list=typeService.listType();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"\n");
		}
	}
	@Test
	public void testListAllOrder() {
		List<Orderform> list=orderService.listAllOrder();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"\n");
		}
	}

}
