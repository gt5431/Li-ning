package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Goodsinfo;

public interface GoodsinfoService {

	//������Ʒid������Ʒ��ϸ��Ϣ
	Goodsinfo FindGoodsById(int pro_number);
	
	//�����û�id�����ղص���Ʒ
	List<Goodsinfo> CollectionById(int u_id);
}
