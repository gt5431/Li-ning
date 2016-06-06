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
	
	//��ҳ
	List<Product> findByPage1();//��ҳ�ռ��صķ�ҳ���

	List<Product> findByPage2(PageUtil pageUtil);

	//������
	List<Product> findByDate1();

	List<Product> findByDate2(PageUtil pageutil);
	
	//�۸���
	
	
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
		 
		//fenye��ԃ
		List<ProductBean> findPageUtil(PageUtil pageUtil);

		int getCount2(ProductBean product);

		int getCount3(ProductBean product);
		
}
