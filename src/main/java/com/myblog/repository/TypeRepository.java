package com.myblog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myblog.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
	Type getByName(String name);
	
	@Query("Select t from Type t")
	List<Type> getTopByBlogSize(Pageable pageable);
}
