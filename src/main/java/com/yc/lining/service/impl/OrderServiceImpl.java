package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Orderform;
import com.yc.lining.mapper.OrderformMapper;
import com.yc.lining.service.OrderService;
@Service("orderService1")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderformMapper orderformMapper;
	@Override
	public List<Orderform> listAllOrder() {
		
		return orderformMapper.listAllOrder();
	}

}
