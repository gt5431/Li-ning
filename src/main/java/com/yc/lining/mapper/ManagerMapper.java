package com.yc.lining.mapper;

import com.yc.lining.entity.Manager;
import java.math.BigDecimal;

public interface ManagerMapper {
    int deleteByPrimaryKey(BigDecimal mid);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(BigDecimal mid);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}