package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Usersinfo;

public interface UserService {
	//µÇÂ¼
	Usersinfo login(Usersinfo usersinfo);
	//×¢²á
	boolean register(Usersinfo usersinfo);
	List<Usersinfo> listAllUser();
}
