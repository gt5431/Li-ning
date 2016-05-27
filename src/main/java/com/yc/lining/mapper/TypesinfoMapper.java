package com.yc.lining.mapper;

import com.yc.lining.entity.Typesinfo;
import java.math.BigDecimal;

public interface TypesinfoMapper {
    int deleteByPrimaryKey(BigDecimal typesid);

    int insert(Typesinfo record);

    int insertSelective(Typesinfo record);

    Typesinfo selectByPrimaryKey(BigDecimal typesid);

    int updateByPrimaryKeySelective(Typesinfo record);

    int updateByPrimaryKey(Typesinfo record);
}