package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Product;
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
	/////////////////////////5-30//////////////////////////////////

	@Override
	public List<Product> findByPage(PageUtil pageUtil) {
		System.out.println("实现类中的==>"+pageUtil);
		if(0 == pageUtil.getPageNo() && 0== pageUtil.getPageSize()){
			return productMapper.findByPage1();
		}else if(0 != pageUtil.getPageNo()	&&	0 != pageUtil.getPageSize()){
			System.out.println("调用第二个方法findByPage2");
			return productMapper.findByPage2(pageUtil);
		}
		return null;
	}
	
	@Override
	public int getCount() {
		return productMapper.getCount();
	}
	
	//按价格范围   ******需要传参double lowprice,double highprice
	@Override
	public int getCount1() {
		return productMapper.getCount1();
	}
	
	//按日期查询
	@Override
	public List<Product> findByDate(PageUtil pageutil) {
		if(0 == pageutil.getPageNo() && 0== pageutil.getPageSize()){
			return productMapper.findByDate1();
		}else if(0 != pageutil.getPageNo()	&&	0 != pageutil.getPageSize()){
			return productMapper.findByDate2(pageutil);
		}
		return null;
	}
}
