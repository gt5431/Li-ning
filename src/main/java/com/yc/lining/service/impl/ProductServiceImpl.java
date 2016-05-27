package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.mapper.ProductMapper;
import com.yc.lining.service.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
@Autowired
private ProductMapper mapper;
    //爆款推荐--页面加载显示的商品
	@Override
	public List getAllProduct() {
		return mapper.selectAllProduct();
	}
	@Override
	public List getAllProById(int typeid) {
		return mapper.selectAllProById(typeid);
	}
	@Override
	public List getSportPro(){
		return mapper.selectSprotPro();
	}

	

}
