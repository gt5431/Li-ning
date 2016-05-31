package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Goodsinfo;

public interface GoodsinfoService {

	//根据商品id查找商品详细信息
	Goodsinfo FindGoodsById(int pro_number);
	
	//根据用户id查找收藏的商品
	List<Goodsinfo> CollectionById(int u_id);
}
