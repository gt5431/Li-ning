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

	//��ѯ������Ʒ
	List<ProductBean> findAll();

	//��ȡ�ܼ�¼��
	int getCount();

	// ��ҳ��ѯ��Ʒ��Ϣ pageNo  �ڼ�ҳ  pageSize ÿҳ�ļ�¼����
	List<ProductBean> findByPage (ProductBean product);

	//���۸������ѯ��Ʒ��Ϣ
	List<ProductBean> findByPrice(PageUtil pageUtil);

	//������Ʒ��ģ����ѯ
	List<ProductBean> findByType(PageUtil pageUtil);

	//���۸����ѯ
	List<ProductBean> findByPriceDesc(PageUtil pageUtil);

	//�����ڲ�ѯ
	List<ProductBean>findByDate(PageUtil pageUtil);

	//����퓲�ԃ
	List<ProductBean> findPageUtil(PageUtil pageUtil);

	int getCount1();

}

