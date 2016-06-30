package com.yc.lining.mapper;

import com.yc.lining.entity.Typesinfo;

import java.util.List;

public interface TypesinfoMapper {
	
   List<Typesinfo> selectAllTypes();
   
   
   List<Typesinfo> listAllType();
   int addType(Typesinfo type);
}
