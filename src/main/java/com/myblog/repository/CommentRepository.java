package com.myblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myblog.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	//list不行
	List<Comment> findByBlogIdAndParentCommentNull(Long id);
}
