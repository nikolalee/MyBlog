package com.myblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myblog.NotFoundException;
import com.myblog.entity.Tag;
import com.myblog.repository.TagRepository;
import com.myblog.service.TagService;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	TagRepository tagRepo;
	
	@Override
	public Tag getById(Long id) {
		return tagRepo.getOne(id);
	}

	@Override
	public List<Tag> getTagList() {
		return tagRepo.findAll();
	}

	@Transactional
	@Override
	public Tag addTag(Tag tag) {
		return tagRepo.save(tag);
	}

	@Transactional
	@Override
	public Tag updateTag(Long id, Tag tag) {
		Tag t = tagRepo.getOne(id);
		if(t == null) {
			throw new NotFoundException("标签并不存在");
		}
		BeanUtils.copyProperties(tag,t);
		return tagRepo.save(t);
	}

	@Transactional
	@Override
	public void deleteTag(Long id) {
		tagRepo.deleteById(id);
	}

	@Override
	public Tag getByName(String name) {
		return tagRepo.getByName(name);
	}

	@Override
	public List<Tag> getAllTag() {
		return tagRepo.findAll();
	}

	@Override
	public List<Tag> listTag(String ids) {
		return tagRepo.findAllById(convertToList(ids));
	}
	
	@Override
	public List<Long> convertToList(String ids) {
		if(!"".equals(ids) && ids != null) {
			String[] strArr = ids.split(",");
			List<Long> longArr = new ArrayList<>();
			for(String obj:strArr) {
				longArr.add(Long.valueOf(obj));
			}
			return longArr;
		}
		return null;
	}

	@Override
	public List<Tag> getTopTag(Integer size) {
		Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
		Pageable pageable = PageRequest.of(0, size,sort);
		return tagRepo.getTopByBlogSize(pageable);
	}

	@Override
	public Page<Tag> getListTag(Pageable pageable) {
		return tagRepo.findAll(pageable);
	}

	
	
}
