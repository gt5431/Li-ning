package com.yc.lining.mapper;

import com.yc.lining.entity.Place;
import java.util.List;

public interface PlaceMapper {
	List<Place> findAddressByUid(int uid);

	int insertPlaceById(Place place);
	
}
