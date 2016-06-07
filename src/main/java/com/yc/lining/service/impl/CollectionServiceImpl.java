package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Collection02;
import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.mapper.CollectionMapper;
import com.yc.lining.service.CollectionService;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private CollectionMapper collectionMapper;
	

	@Override
	public boolean CollectionGoods(Collection02 coll) {
		if( 1==collectionMapper.Collectionn(coll)){
			return true;
		}
		return false;
	}

	@Override
	public List<Goodsinfo> CollectionById(int uid) {
		return collectionMapper.CollectionById(uid);
	}

}
