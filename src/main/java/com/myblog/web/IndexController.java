package com.myblog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myblog.service.BlogService;
import com.myblog.service.TagService;
import com.myblog.service.TypeService;

@Controller
public class IndexController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("/")
	public String indexPage(Model model,@PageableDefault(size=3,sort= {"updateTime"},direction=Sort.Direction.DESC) Pageable pageable) {
		
		model.addAttribute("page", blogService.listBlog(pageable));
		model.addAttribute("types", typeService.listTypeByBlogSize(6));
		model.addAttribute("tags", tagService.getTopTag(6));
		model.addAttribute("recommendBlogs", blogService.getTopRecommendBlog(8));
		return "index.html";
	}
	
	@RequestMapping("/search")
	public String searchBlog(String query,Model model,@PageableDefault(size=3,sort= {"updateTime"},direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("page", blogService.searchBlog("%"+query+"%", pageable));
		model.addAttribute("query", query);
		return "search.html";
	} 
	
	@RequestMapping("/blog/{id}")
	public String blogPage(@PathVariable Long id,Model model) {
		model.addAttribute("blog", blogService.getAndConvert(id));
		return "blog.html";
	}
	
	@RequestMapping("/footer/newBlog")
	public String newBlog(Model model) {
		
		model.addAttribute("newBlogs", blogService.getTopRecommendBlog(3));
		
		return "_fragments.html :: newBlogList";
	}
	
	
	
}
