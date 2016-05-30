package com.yc.lining.mapper;

import com.yc.lining.entity.Comments;

public interface CommentsMapper {
    int insert(Comments record);

    int insertSelective(Comments record);
}