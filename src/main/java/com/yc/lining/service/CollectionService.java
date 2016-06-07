package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Collection02;
import com.yc.lining.entity.Goodsinfo;



public interface CollectionService {

	boolean CollectionGoods(Collection02 coll);
	
	// ’≤ÿ
	List<Goodsinfo> CollectionById(int uid);
}
