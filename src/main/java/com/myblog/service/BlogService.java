package com.myblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myblog.entity.Blog;
import com.myblog.entity.BlogQuery;

public interface BlogService {
	Blog getById(Long id);
	Blog getAndConvert(Long id);
	Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
	Page<Blog> listBlog(Pageable pageable);
	Page<Blog> listBlog(Pageable pageable,Long tagId);
	List<Blog> getTopRecommendBlog(Integer size);
	Page<Blog> searchBlog(String query,Pageable pageable);
	Blog updateBlog(Long id,Blog blog);
	Blog addBlog(Blog blog);
	void deleteBlog(Long id);
	Map<String,List<Blog>> getArchiveBlog(); //按年份获取博客列表
	int updateVisitNum(Long id); //更新访问次数
	Long getBlogNum();
}
