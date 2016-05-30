package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Product;
import com.yc.lining.util.PageUtil;

public interface ProductService {
	
	Product ProductDetailsById(int proNumber);
	
	public List<Product> getAllProduct();
    public List<Product> getAllProById(int typesid);
    public List<Product> getSportPro();

	List<Product> findByPage(PageUtil pageUtil);

	int getCount();

	int getCount1();

	List<Product> findByDate(PageUtil pageutil);
	
}