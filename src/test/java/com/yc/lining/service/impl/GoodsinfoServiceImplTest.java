package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.service.GoodsinfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GoodsinfoServiceImplTest {

	@Autowired
	private GoodsinfoService goodsinfoService;
	
	@Test
	public void testFindGoodsById() {
		Goodsinfo goodsinfo = new Goodsinfo();
		goodsinfo = goodsinfoService.FindGoodsById(15);
		System.out.println(goodsinfo);
		assertNotNull("≤È—Ø ß∞‹",goodsinfo);
	}
	
	@Test
	public void testFindCollectionById() {
		List<Goodsinfo> goodsinfo = goodsinfoService.CollectionById(101);
		System.out.println(goodsinfo);
		assertNotNull("≤È—Ø ß∞‹",goodsinfo);
	}

}
