package com.yc.lining.service;

import com.yc.lining.entity.Usersinfo;

public interface UserService {
	//��¼
	Usersinfo login(Usersinfo usersinfo);
	//ע��
	boolean register(Usersinfo usersinfo);
}
