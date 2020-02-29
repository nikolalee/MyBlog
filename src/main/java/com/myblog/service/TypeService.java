package com.myblog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myblog.entity.Type;

public interface TypeService {
	
	Type getById(Long id);
	Page<Type> listType(Pageable pageable);
	List<Type> listTypeByBlogSize(Integer size);
	Type save(Type type);
	Type update(Long id,Type type);
	Type delete(Long id);
	Type getByName(String name);
	List<Type> getAllType();
	
}
