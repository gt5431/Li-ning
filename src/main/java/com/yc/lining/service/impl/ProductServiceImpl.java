package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Product;
import com.yc.lining.entity.ProductBean;
import com.yc.lining.mapper.ProductMapper;
import com.yc.lining.service.ProductService;
import com.yc.lining.util.PageUtil;
@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper; 
	
	@Override
	public Product ProductDetailsById(int proNumber) {
		return productMapper.findProductDetailsById(proNumber);
	}

    //爆款推荐--页面加载显示的商品
	@Override
	public List<Product> getAllProduct() {
		return productMapper.selectAllProduct();
	}
	
	@Override
	public List getAllProById(int typeid) {
		return productMapper.selectAllProById(typeid);
	}
	
	@Override
	public List getSportPro(){
		return productMapper.selectSprotPro();
	}
	
	@Override
	public List<ProductBean> findAll() {
		return productMapper.findAll();
	}

	@Override
	public int getCount() {
		return productMapper.getCount();
	}

	@Override
	public List<ProductBean> findByType(PageUtil pageUtil) {
		return productMapper.findByType(pageUtil);
	}


	@Override
	public List<ProductBean> findByPrice(PageUtil pageUtil) {
		return productMapper.findByPrice(pageUtil);
	}

	@Override
	public List<ProductBean> findByPriceDesc(PageUtil pageUtil) {
		return productMapper.findByPriceDesc(pageUtil);
	}
	@Override
	public List<ProductBean> findByDate(PageUtil pageUtil) {
		return productMapper.findByDate(pageUtil);
	}


	@Override
	public List<ProductBean> findByPage(ProductBean product) {
		return productMapper.findByPage(product);
	}

	@Override
	public List<ProductBean> findPageUtil(PageUtil pageUtil) {
		return productMapper.findPageUtil(pageUtil);
	}

	@Override
	public int getCount1(String searchName) {
		System.out.println("业务类中的值为==>"+searchName);
		return productMapper.getCount1(searchName);
	}

	@Override
	public int getCount2(ProductBean product) {
		return productMapper.getCount2(product);
	}

	@Override
	public int getCount3(ProductBean product) {
		System.out.println("要搜索的类型是==>"+product.getSearchName());
		return productMapper.getCount3(product);
	}

}
