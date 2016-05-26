package com.yc.lining.service;

import com.yc.lining.entity.Goodsinfo;

public interface GoodsinfoService {

	//根据商品id查找商品详细信息
	Goodsinfo FindGoodsById(int pro_number);
}
