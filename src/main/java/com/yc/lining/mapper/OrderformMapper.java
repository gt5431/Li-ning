package com.yc.lining.mapper;

import java.util.List;

import com.yc.lining.entity.Orderform;

public interface OrderformMapper {
    int insert(Orderform record);

    int insertSelective(Orderform record);

	int insertOrderById(Orderform orderform);
	
	 List<Orderform> listAllOrder();
}
