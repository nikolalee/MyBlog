package com.myblog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myblog.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog>{
	
	@Query("select b from Blog b where b.recommend = true")
	List<Blog> getTopRecommendBlog(Pageable pageable);
	
	@Query("select b from Blog b where b.title like ?1 or b.content like ?1")
	Page<Blog> getBlogLikeQuery(String query,Pageable pageable);
	
	//不太懂
	//获取所有的年份
	@Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc" )
	List<String> getGroupYear();
	
	//根据年份获取当年的博客列表
	@Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
	List<Blog> getByYear(String year);
	
	//修改visitNum，使每次访问后加一
	@Transactional
	@Modifying
	@Query("update Blog b set b.visitNum = b.visitNum + 1L where b.id = ?1")
	int updateVisitNum(Long id);
}
