package com.yc.lining.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.Product;
import com.yc.lining.entity.ProductBean;
import com.yc.lining.service.ProductService;
import com.yc.lining.util.PageUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ProductServiceImplTest {
	@Autowired
	private ProductService productService;
	
	
	private ProductBean product;
	
	@Test
	public void testFindAll() {
		List<ProductBean> products=productService.findAll();
		System.out.println(products);
		assertNotNull(products);
	}
	
	@Test
	public void getCount() {
		int num=productService.getCount();
		System.out.println(num);
		assertNotNull(num);
	}
	
	@Test
	public void findByPrice() {
		PageUtil pageUtil=new PageUtil(1,8);
		product=new ProductBean();
		product.setLowPrice(100);
		product.setHighPrice(300);
		List<ProductBean> products=productService.findByPrice(product, pageUtil);
		System.out.println(products);
		assertNotNull(products);
	}


}
