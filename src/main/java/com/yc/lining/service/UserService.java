package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Usersinfo;

public interface UserService {
	//��¼
	Usersinfo login(Usersinfo usersinfo);
	//ע��
	boolean register(Usersinfo usersinfo);
	List<Usersinfo> listAllUser();
}
