package com.yc.lining.service;

import java.util.List;

import com.yc.lining.entity.CommentBean;
import com.yc.lining.entity.Comments;
import com.yc.lining.entity.ProComment;

public interface CommentService {
   public  List<CommentBean> selectNoComment(int u_id);
   public  CommentBean selectCommentimg(int orderid);
   public  void insertComment1(Comments comment);
   public  List<ProComment> selectCommentByProNum1(int pro_number);
}
