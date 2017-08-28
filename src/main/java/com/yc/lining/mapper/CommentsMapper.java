package com.yc.lining.mapper;

import java.util.List;

import com.yc.lining.entity.CommentBean;
import com.yc.lining.entity.Comments;
import com.yc.lining.entity.ProComment;

public interface CommentsMapper {
    void insertComment(Comments comment);
    List selectNoComment(int u_id);
    CommentBean selectCommentimg(int orderid);
    int updateOrderComment(int orderid);
    List selectCommentByProNum(int pro_number);
}