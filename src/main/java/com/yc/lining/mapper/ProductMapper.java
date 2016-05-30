package com.yc.lining.mapper;

import com.yc.lining.entity.Product;
import com.yc.lining.util.PageUtil;

import java.util.List;

public interface ProductMapper {

	Product findProductDetailsById(int proNumber);

	List<Product> selectAllProduct();
	List<Product> selectAllProById(int typesid);
	List<Product> selectSprotPro();

	int getCount();

	int getCount1();
	
	//分页
	List<Product> findByPage1();//首页刚加载的分页情况

	List<Product> findByPage2(PageUtil pageUtil);

	//按日期
	List<Product> findByDate1();

	List<Product> findByDate2(PageUtil pageutil);
	
	//价格降序
	
	
}