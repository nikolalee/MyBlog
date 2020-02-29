package com.myblog.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entity.Comment;
import com.myblog.repository.CommentRepository;
import com.myblog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepo;
	
	/**
	 * Q：获取评论及其回复列表
	 */
	@Override
	public List<Comment> getById(Long id) {
		
		List<Comment> list = commentRepo.findByBlogIdAndParentCommentNull(id);
		
		return setList(list);
	}
	
	
	/**
	 * Q:存储新评论
	 */
	@Transactional
	@Override
	public Comment saveComment(Comment comment) {
		Long pid = comment.getParentComment().getId() ;
		if(pid != -1) {
			comment.setParentComment(commentRepo.getOne(pid));
		}else {
			comment.setParentComment(null);
		}
		comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return commentRepo.save(comment);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 * Q:将取出的主评论复制到另一个集合，以免之后的操作会影响数据库
	 */
	private List<Comment> setList(List<Comment> list){
		
		List<Comment> list2 = new ArrayList<>();
		for(Comment comment:list) {
			Comment c = new Comment();
			BeanUtils.copyProperties(comment, c);
			list2.add(c);
		}
		getSubComment(list2);
		
		return list2;
	}
	
	/**
	 * 
	 * @param list
	 * Q:调用getSubList函数将所有回复取出，并将该值设为主评论的subComment
	 */
	private void getSubComment(List<Comment> list) {
		
		for(Comment comment:list) {
			if(comment.getSubComments().size() > 0) {
				
				List<Comment> subList = comment.getSubComments();
				for(Comment c:subList) {
					getSubList(c);
				}
			}
			comment.setSubComments(subComment);
			subComment = new ArrayList<>();
		}
		
		
		
	}
	
	private List<Comment> subComment = new ArrayList<>();
	/**
	 * 
	 * @param comment
	 * Q：该函数用递归将所有回复的回复取出
	 */
	private void getSubList(Comment comment) {
		subComment.add(comment);
		if(comment.getSubComments().size() > 0) {
			List<Comment> subList = comment.getSubComments();
			for(Comment c:subList) {
				subComment.add(c);
				if(c.getSubComments().size() > 0) {
					getSubList(c);
				}
			}
		}
	}
 
	
}
