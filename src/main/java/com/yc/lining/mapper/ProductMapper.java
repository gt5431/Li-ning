package com.yc.lining.mapper;

import com.yc.lining.entity.Product;

import java.util.List;

public interface ProductMapper {

	Product findProductDetailsById(int proNumber);
}