package com.yc.lining.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.lining.entity.ProductBean;
import com.yc.lining.service.ProductService;
import com.yc.lining.util.PageUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ProductPageUtilTest {

	@Autowired
	private ProductService productService;
	
	
	private PageUtil pageUtil;
	
	@Test
	public void testFindPageUtil() {
		pageUtil.setPageNo(1);
		pageUtil.setPageSize(8);
		List<ProductBean> list = productService.findPageUtil(pageUtil);
		System.out.println("·ÖÒ³==>"+list);
	}

}
