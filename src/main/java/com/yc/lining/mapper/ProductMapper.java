package com.yc.lining.mapper;

import com.yc.lining.entity.Product;
import java.math.BigDecimal;

public interface ProductMapper {
    int deleteByPrimaryKey(BigDecimal proNumber);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(BigDecimal proNumber);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}