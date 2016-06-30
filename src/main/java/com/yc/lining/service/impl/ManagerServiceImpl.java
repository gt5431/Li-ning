package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Manager;
import com.yc.lining.mapper.ManagerMapper;
import com.yc.lining.service.ManagerService;
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public Manager login(Manager manager) {
		System.out.println(manager);
		return managerMapper.getManagerByManager(manager);
	}

	@Override
	public List<Manager> listAll() {
		
		return managerMapper.listAllManager();
	}

	@Override
	public boolean addManager(Manager manager) {
		if(1==managerMapper.addManager(manager)){
			return true;
		}
		return false;
	}

}
