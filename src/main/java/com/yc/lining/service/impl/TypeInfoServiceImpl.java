package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.mapper.TypesinfoMapper;
import com.yc.lining.service.TypeInfoService;

@Service("typeInfoServiceImpl")
public class TypeInfoServiceImpl implements TypeInfoService {
@Autowired
private TypesinfoMapper mapper;
	@Override
	public List getAllTypes() {
		return mapper.selectAllTypes();
	}


}
