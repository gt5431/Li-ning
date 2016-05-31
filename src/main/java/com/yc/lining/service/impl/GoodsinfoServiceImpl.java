package com.yc.lining.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.mapper.GoodsinfoMapper;
import com.yc.lining.service.GoodsinfoService;

@Service("goodsinfoService")
public class GoodsinfoServiceImpl implements GoodsinfoService {
	@Autowired
	private GoodsinfoMapper goodsinfoMapper;
	
	@Override
	public Goodsinfo FindGoodsById(int pro_number) {
		return goodsinfoMapper.GoodsinfoById(pro_number);
	}

	@Override
	public List<Goodsinfo> CollectionById(int u_id) {
		
		return goodsinfoMapper.CollectionById(u_id);
	}

}
