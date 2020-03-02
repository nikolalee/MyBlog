package com.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myblog.entity.BlogQuery;
import com.myblog.service.BlogService;
import com.myblog.service.TagService;
import com.myblog.service.TypeService;

@Controller
public class ShowController {
	
	@Autowired
	private TypeService typeService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private TagService tagService;
		
	/**
	 * 
	 * @param id
	 * @param model
	 * @param pageable
	 * @return
	 * @获取分类列表
	 */
	@RequestMapping("/types/{id}")
	public String showTypes(@PathVariable Long id,Model model,
							@PageableDefault(size=6,sort="updateTime",direction=Sort.Direction.DESC) Pageable pageable) {
		if(id == -1) {
			id = typeService.getById(1L).getId();
		}
		
		BlogQuery query = new BlogQuery();
		query.setTypeId(id);
		model.addAttribute("types", typeService.getAllType());
		model.addAttribute("page", blogService.listBlog(pageable, query));
		model.addAttribute("activeTypeId", id);
		
		return "types.html";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @param pageable
	 * @return
	 * @获取标签列表
	 */
	@RequestMapping("/tags/{id}")
	public String showTags(@PathVariable Long id,Model model,
			@PageableDefault(size=6,sort="updateTime",direction=Sort.Direction.DESC) Pageable pageable) {
		if(id == -1) {
			id = tagService.getById(1L).getId();
		}
		model.addAttribute("page", blogService.listBlog(pageable, id));
		model.addAttribute("tags",tagService.getAllTag());
		model.addAttribute("activeTagId", id);
		
		return "tags.html";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @获取年份列表
	 */
	@RequestMapping("/archive")
	public String showArchive(Model model) {
		
		model.addAttribute("archiveMap", blogService.getArchiveBlog());
		model.addAttribute("blogCount", blogService.getBlogNum());
		
		return "archives.html";
	}
	
	@RequestMapping("/about")
	public String showAboutMe() {
		
		return "about.html";
	}
}
