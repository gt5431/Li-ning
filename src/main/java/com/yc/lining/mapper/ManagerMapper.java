package com.yc.lining.mapper;

import com.yc.lining.entity.Manager;

import java.math.BigDecimal;
import java.util.List;

public interface ManagerMapper {
   Manager getManagerByManager(Manager manager);
   List<Manager> listAllManager();
   int addManager(Manager manager);
}