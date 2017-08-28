package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Collection02;
import com.yc.lining.service.CollectionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class CollectionServiceImplTest {
	@Autowired
	private CollectionService collectionService;
	
	@Test
	public void testCollection() {
		Collection02 col = new Collection02(101,4);
		boolean i=collectionService.CollectionGoods(col);
		assertNotNull("µÇÂ¼Ê§°Ü",i);

	}

}
