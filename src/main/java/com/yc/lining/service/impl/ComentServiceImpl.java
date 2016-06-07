package com.yc.lining.service.impl;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.lining.entity.CommentBean;
import com.yc.lining.entity.Comments;
import com.yc.lining.entity.ProComment;
import com.yc.lining.mapper.CommentsMapper;
import com.yc.lining.service.CommentService;
@Transactional
@Service("comentServiceImpl")
public class ComentServiceImpl implements CommentService{
	@Autowired
	private CommentsMapper mapper;
	
	@Override
	public List selectNoComment(int u_id) {
		return mapper.selectNoComment(u_id);
	}

	@Override
	public CommentBean selectCommentimg(int orderid) {
		return mapper.selectCommentimg(orderid);
	}
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void insertComment1(Comments comment) {
		//写评价,插入到数据库
		mapper.insertComment(comment);
		//更新订单表的评价状态
		mapper.updateOrderComment(comment.getOrderid());
	}
	@Override 
	public List<ProComment> selectCommentByProNum1(int pro_number) {
		return mapper.selectCommentByProNum(pro_number);
	}
   
}
