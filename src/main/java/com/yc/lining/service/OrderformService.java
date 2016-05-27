package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.Place;

public interface OrderformService {

	List<Place> findPlaceByUid(int uid);

	int addNewAddress(Place place);

	int handOrder();
	
}
