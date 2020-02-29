package com.myblog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myblog.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long>{
	Tag getByName(String name);
	@Query("select t from Tag t")
	List<Tag> getTopByBlogSize(Pageable pageable);
 }
