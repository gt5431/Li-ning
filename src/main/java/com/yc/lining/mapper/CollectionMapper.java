package com.yc.lining.mapper;

import java.util.List;

import com.yc.lining.entity.Collection02;
import com.yc.lining.entity.Goodsinfo;

public interface CollectionMapper {

	int Collectionn(Collection02 coll);

	List<Goodsinfo> CollectionById(int uid);
	
}
