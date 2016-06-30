package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Typesinfo;

public interface TypeService {
	List<Typesinfo> listType();
	boolean addType(Typesinfo type);
}
