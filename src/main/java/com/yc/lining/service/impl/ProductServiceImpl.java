package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.ProductBean;
import com.yc.lining.mapper.ProductMapper;
import com.yc.lining.service.ProductService;
import com.yc.lining.util.PageUtil;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductBean> findAll() {
		return productMapper.findAll();
	}

	@Override
	public int getCount() {
		return productMapper.getCount();
	}


	@Override
	public List<ProductBean> findByType(ProductBean product,PageUtil pageUtil) {
		return productMapper.findByType(product, pageUtil);
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
	public List<ProductBean> findByPrice(ProductBean product,PageUtil pageUtil) {
		System.out.println("×î¸ß¼Û==¡·"+product.getHighPrice());
		return productMapper.findByPrice(product,pageUtil);
	}

	@Override
	public List<ProductBean> findByPage(ProductBean product) {
		return productMapper.findByPage(product);
	}

	@Override
	public List<ProductBean> findPageUtil(PageUtil pageUtil) {
		return productMapper.findPageUtil(pageUtil);
	}

	
}
