package com.yc.lining.mapper;

import com.yc.lining.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductMapper {
   List selectAllProduct();
   List selectAllProById(int typesid);
   List selectSprotPro();
}