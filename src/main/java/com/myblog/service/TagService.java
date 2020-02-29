package com.myblog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myblog.entity.Tag;

public interface TagService {
	Tag getById(Long id);
	List<Tag> getTagList();
	Tag getByName(String name);
	List<Tag> getAllTag();
	List<Tag> getTopTag(Integer size);
	Page<Tag> getListTag(Pageable pageable);
	Tag addTag(Tag tag);
	Tag updateTag(Long id,Tag tag);
	List<Tag> listTag(String ids);
	List<Long> convertToList(String ids);
	void deleteTag(Long id);
}
