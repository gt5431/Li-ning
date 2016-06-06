package com.yc.lining.mapper;

import com.yc.lining.entity.Product;
import com.yc.lining.entity.ProductBean;
import com.yc.lining.util.PageUtil;

import java.util.List;

public interface ProductMapper {

	Product findProductDetailsById(int proNumber);

	List<Product> selectAllProduct();
	List<Product> selectAllProById(int typesid);
	List<Product> selectSprotPro();

	int getCount1(String searchName);
	
	//分页
	List<Product> findByPage1();//首页刚加载的分页情况

	List<Product> findByPage2(PageUtil pageUtil);

	//按日期
	List<Product> findByDate1();

	List<Product> findByDate2(PageUtil pageutil);
	
	//价格降序
	
	
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
		 
		//fenye查詢
		List<ProductBean> findPageUtil(PageUtil pageUtil);

		int getCount2(ProductBean product);

		int getCount3(ProductBean product);
		
}
