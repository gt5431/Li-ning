package com.yc.lining.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Product;
import com.yc.lining.mapper.ProductMapper;
import com.yc.lining.service.ProductService;
@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper; 
	
	@Override
	public Product ProductDetailsById(int proNumber) {
		return productMapper.findProductDetailsById(proNumber);
	}

}
