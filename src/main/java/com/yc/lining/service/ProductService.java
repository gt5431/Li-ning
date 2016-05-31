package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Product;
import com.yc.lining.entity.ProductBean;
import com.yc.lining.util.PageUtil;

public interface ProductService {

	Product ProductDetailsById(int proNumber);

	public List<Product> getAllProduct();
	public List<Product> getAllProById(int typesid);
	public List<Product> getSportPro();

	//查询所有商品
	List<ProductBean> findAll();

	//获取总记录数
	int getCount();

	// 分页查询商品信息 pageNo  第几页  pageSize 每页的记录条数
	List<ProductBean> findByPage (ProductBean product);

	//按价格区间查询商品信息
	List<ProductBean> findByPrice(PageUtil pageUtil);

	//根据商品名模糊查询
	List<ProductBean> findByType(PageUtil pageUtil);

	//按价格降序查询
	List<ProductBean> findByPriceDesc(PageUtil pageUtil);

	//按日期查询
	List<ProductBean>findByDate(PageUtil pageUtil);

	//按分頁查詢
	List<ProductBean> findPageUtil(PageUtil pageUtil);

	int getCount1();

}

