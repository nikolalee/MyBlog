package com.myblog.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myblog.NotFoundException;
import com.myblog.entity.Type;
import com.myblog.repository.TypeRepository;
import com.myblog.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	TypeRepository typeRepo;

	@Override
	public Type getById(Long id) {
		return typeRepo.getOne(id);
	}

	@Override
	public Page<Type> listType(Pageable pageable) {
		return typeRepo.findAll(pageable);
	}

	@Transactional
	@Override
	public Type save(Type type) {
		return typeRepo.save(type);
	}

	@Transactional
	@Override
	public Type update(Long id, Type type) {
		Type t = typeRepo.getOne(id);
		if(t == null) {
			throw new NotFoundException("该分类不存在");
		}
		BeanUtils.copyProperties(type,t);
		return typeRepo.save(t);
	}

	@Transactional
	@Override
	public Type delete(Long id) {
		
		Type t= typeRepo.getOne(id);
		if(t == null) {
			throw new NotFoundException("该分类不存在");
		}
		typeRepo.deleteById(id);;
		return t;
	}

	@Override
	public Type getByName(String name) {
		return typeRepo.getByName(name);
	}

	@Override
	public List<Type> getAllType() {
		return typeRepo.findAll();
	}

	@Override
	public List<Type> listTypeByBlogSize(Integer size) {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
		Pageable pageable = PageRequest.of(0, size, sort);
		
		
		return typeRepo.getTopByBlogSize(pageable);
	}

	

}
