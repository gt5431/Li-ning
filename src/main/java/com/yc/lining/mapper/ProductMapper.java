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
	
	//��ҳ
	List<Product> findByPage1();//��ҳ�ռ��صķ�ҳ���

	List<Product> findByPage2(PageUtil pageUtil);

	//������
	List<Product> findByDate1();

	List<Product> findByDate2(PageUtil pageutil);
	
	//�۸���
	
	
}