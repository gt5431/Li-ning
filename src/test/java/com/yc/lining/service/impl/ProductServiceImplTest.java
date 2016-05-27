package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Product;
import com.yc.lining.service.ProductService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ProductServiceImplTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void testProductDetailsById() {
		Product product = new Product();
		product = productService.ProductDetailsById(1);
		System.out.println(product);
		assertNotNull("≤È—Ø≥…π¶",product);
	}

}
