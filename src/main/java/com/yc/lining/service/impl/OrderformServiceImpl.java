package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Orderform;
import com.yc.lining.entity.Place;
import com.yc.lining.mapper.OrderformMapper;
import com.yc.lining.mapper.PlaceMapper;
import com.yc.lining.service.OrderformService;

@Service("orderService")
public class OrderformServiceImpl implements OrderformService{
	
	@Autowired
	private OrderformMapper orderformMapper;
	
	@Autowired
	private PlaceMapper placeMapper;

	@Override
	public List<Place> findPlaceByUid(int uid) {
		return placeMapper.findAddressByUid(uid);
	}

	@Override
	public int addNewAddress(Place place) {
		System.out.println("实现类中的place==>"+place);
		return placeMapper.insertPlaceById(place);
	}

	@Override
	public int handOrder(Orderform orderform) {
		return orderformMapper.insertOrderById(orderform);
	} 
	
}
=======
package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Orderform;
import com.yc.lining.entity.Place;
import com.yc.lining.mapper.OrderformMapper;
import com.yc.lining.mapper.PlaceMapper;
import com.yc.lining.service.OrderformService;

@Service("orderService")
public class OrderformServiceImpl implements OrderformService{
	
	@Autowired
	private OrderformMapper orderformMapper;
	
	@Autowired
	private PlaceMapper placeMapper;

	@Override
	public List<Place> findPlaceByUid(int uid) {
		return placeMapper.findAddressByUid(uid);
	}

	@Override
	public int addNewAddress(Place place) {
		System.out.println("实现类中的place==>"+place);
		return placeMapper.insertPlaceById(place);
	}

	@Override
	public int handOrder(Orderform orderform) {
		return orderformMapper.insertOrderById(orderform);
	} 
	
}
