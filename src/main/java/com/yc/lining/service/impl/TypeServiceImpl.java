package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Typesinfo;
import com.yc.lining.mapper.TypesinfoMapper;
import com.yc.lining.service.TypeService;
@Service("typeService")
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypesinfoMapper typesinfoMapper;
	@Override
	public List<Typesinfo> listType() {
		return typesinfoMapper.listAllType();
	}
	@Override
	public boolean addType(Typesinfo type) {
		if( typesinfoMapper.addType(type)==1){
			return true;
		}
		return false;
	}

}
