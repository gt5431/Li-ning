package com.yc.lining.mapper;

import java.util.List;

import com.yc.lining.entity.Goodsinfo;

public interface GoodsinfoMapper {

	Goodsinfo GoodsinfoById(int pro_number);
	List<Goodsinfo> CollectionById(int u_id);
}
