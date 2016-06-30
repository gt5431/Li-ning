package com.yc.lining.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Product;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	@Test
	public void testUpdateProduct() {
		Product product = new Product("aaaaa",1,200,108,50);
		boolean result = productService.updateProduct(product);
		System.out.println(result);
		assertEquals(true,result);
	}

}
