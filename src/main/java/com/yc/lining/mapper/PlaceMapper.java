package com.yc.lining.mapper;

import com.yc.lining.entity.Place;
import java.util.List;

public interface PlaceMapper {
	List<Place> findAddressByUid(int uid);

	int insertPlaceById(Place place);
	
	
    /*int deleteByPrimaryKey(BigDecimal pid);
    int insert(Place record);
    int insertSelective(Place record);
    Place selectByPrimaryKey(BigDecimal pid);
    int updateByPrimaryKeySelective(Place record);
    int updateByPrimaryKey(Place record);*/
}