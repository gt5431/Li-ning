package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Product;
import com.yc.lining.entity.ProductBean;
import com.yc.lining.util.PageUtil;

public interface ProductService {
	
	//��ѯ������Ʒ
	List<ProductBean> findAll();
	
	//��ȡ�ܼ�¼��
	int getCount();
	
	// ��ҳ��ѯ��Ʒ��Ϣ pageNo  �ڼ�ҳ  pageSize ÿҳ�ļ�¼����
	List<ProductBean> findByPage (ProductBean product);
	
	
	//���۸������ѯ��Ʒ��Ϣ
	List<ProductBean> findByPrice(ProductBean product,PageUtil pageUtil);
	
	//������Ʒ��ģ����ѯ
	List<ProductBean> findByType(ProductBean product,PageUtil pageUtil);
	
	//���۸����ѯ
	List<ProductBean> findByPriceDesc(PageUtil pageUtil);
	
	//�����ڲ�ѯ
	List<ProductBean>findByDate(PageUtil pageUtil);

	//����퓲�ԃ
	List<ProductBean> findPageUtil(PageUtil pageUtil);
	
}
