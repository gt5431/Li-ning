package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Typesinfo;
import com.yc.lining.mapper.TypesinfoMapper;
import com.yc.lining.service.TypeInfoService;
@Service("typesService")
public class TypesInfoServiceImpl implements TypeInfoService{
	
	@Autowired
	private TypesinfoMapper typesinfoMapper;
	
	@Override
	public List<Typesinfo> getAllTypes() {
		return typesinfoMapper.selectAllTypes();
	}

}
