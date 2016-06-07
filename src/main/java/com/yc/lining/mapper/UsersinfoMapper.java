package com.yc.lining.mapper;

import java.util.List;

import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Usersinfo;

public interface UsersinfoMapper {

	Usersinfo modifyUserinfo(Usersinfo usersinfo);

	int insertUser(Usersinfo usersinfo);
	
	List<Goodsinfo> CollectionById(int uid);
	
}
