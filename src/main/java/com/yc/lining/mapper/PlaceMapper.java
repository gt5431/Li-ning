package com.yc.lining.mapper;

import com.yc.lining.entity.Place;
import java.math.BigDecimal;

public interface PlaceMapper {
    int deleteByPrimaryKey(BigDecimal pid);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(BigDecimal pid);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);
}