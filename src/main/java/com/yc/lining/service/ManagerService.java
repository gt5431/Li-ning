package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Manager;

public interface ManagerService {
	Manager login(Manager Manager);
	List<Manager> listAll();
	boolean addManager(Manager Manager);
}
