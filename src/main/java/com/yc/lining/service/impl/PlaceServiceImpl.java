package com.yc.lining.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.lining.entity.Place;
import com.yc.lining.entity.Place1;
import com.yc.lining.mapper.PlaceMapper;
import com.yc.lining.service.PlaceService;
@Service("placeService")
public class PlaceServiceImpl implements PlaceService {
	@Autowired
	private PlaceMapper  placeMapper;
	@Override
	public List<Place1> listAllPlace() {
		
		return placeMapper.listAllPlace();
	}

}
