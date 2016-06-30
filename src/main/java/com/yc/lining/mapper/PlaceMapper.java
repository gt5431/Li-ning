package com.yc.lining.mapper;

import com.yc.lining.entity.Place;
import com.yc.lining.entity.Place1;

import java.util.List;

public interface PlaceMapper {
	List<Place> findAddressByUid(int uid);

	int insertPlaceById(Place place);
	 List<Place1> listAllPlace();
}
