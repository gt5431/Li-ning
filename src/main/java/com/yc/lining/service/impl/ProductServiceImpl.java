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

    //�����Ƽ�--ҳ�������ʾ����Ʒ
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
		System.out.println("ʵ�����е�==>"+pageUtil);
		if(0 == pageUtil.getPageNo() && 0== pageUtil.getPageSize()){
			return productMapper.findByPage1();
		}else if(0 != pageUtil.getPageNo()	&&	0 != pageUtil.getPageSize()){
			System.out.println("���õڶ�������findByPage2");
			return productMapper.findByPage2(pageUtil);
		}
		return null;
	}
	
	@Override
	public int getCount() {
		return productMapper.getCount();
	}
	
	//���۸�Χ   ******��Ҫ����double lowprice,double highprice
	@Override
	public int getCount1() {
		return productMapper.getCount1();
	}
	
	//�����ڲ�ѯ
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
