package com.yc.lining.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Usersinfo;
import com.yc.lining.mapper.UsersinfoMapper;
import com.yc.lining.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UsersinfoMapper usersinfoMapper; 
	
	@Override
	public Usersinfo login(Usersinfo usersinfo) {
		return usersinfoMapper.modifyUserinfo(usersinfo);
	}

	@Override
	public boolean register(Usersinfo usersinfo) {
		if( 1==usersinfoMapper.insertUser(usersinfo) ){
			return true;
		}
		return false;
	}

}
