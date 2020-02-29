package com.myblog.service;

import java.util.List;

import com.myblog.entity.Comment;

public interface CommentService {
	List<Comment> getById(Long id);
	Comment saveComment(Comment comment);
}
