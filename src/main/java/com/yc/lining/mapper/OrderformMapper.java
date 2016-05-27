package com.yc.lining.mapper;

import com.yc.lining.entity.Orderform;

public interface OrderformMapper {
    int insert(Orderform record);

    int insertSelective(Orderform record);

	int insertOrderById();

}