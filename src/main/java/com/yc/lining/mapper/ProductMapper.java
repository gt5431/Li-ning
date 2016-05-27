package com.yc.lining.mapper;

import com.yc.lining.entity.Product;


public interface ProductMapper {

	Product findProductDetailsById(int proNumber);
}