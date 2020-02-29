package com.myblog.web.admin;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myblog.entity.Blog;
import com.myblog.entity.BlogQuery;
import com.myblog.entity.User;
import com.myblog.service.BlogService;
import com.myblog.service.TagService;
import com.myblog.service.TypeService;

@Controller
@RequestMapping("/admin")
public class BlogsController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/blogs")
	public String getBlogList(BlogQuery blog,@PageableDefault(size=2,sort= {"updateTime"},direction= Sort.Direction.DESC) Pageable pageable,Model model) {
		 
		model.addAttribute("page", blogService.listBlog(pageable,blog));
		
		model.addAttribute("types", typeService.getAllType());
		
		model.addAttribute("tags", tagService.getAllTag());
		
		return "/admin/blogs.html";
	}
	
	@RequestMapping("/blogs/search")
	public String search(BlogQuery blog,@PageableDefault(size=2,sort= {"updateTime"},direction= Sort.Direction.DESC) Pageable pageable,Model model) {
		 
		model.addAttribute("page", blogService.listBlog(pageable,blog));
		
		model.addAttribute("types", typeService.getAllType());
		
		return "/admin/blogs.html :: blogList";
	}
	
	@RequestMapping("/blogs/add")
	public String addPage(Model model) {
		model.addAttribute("types", typeService.getAllType());
		model.addAttribute("tags", tagService.getAllTag());
		model.addAttribute("blog", new Blog());
		return "/admin/blogs-input.html";
	}
	
	@PostMapping("/blogs")
	public String add(Blog blog,HttpSession session,RedirectAttributes attr) {
		
		blog.setUser((User)session.getAttribute("USER_SESSION"));
		blog.setType(typeService.getById(blog.getType().getId()));
		logger.info("idsWhat: "+blog.getTagIds());
		blog.setTags(tagService.listTag(blog.getTagIds()));
		Blog b = blogService.addBlog(blog);
		logger.info("ItsUnipue: "+blog.getTags());
		if(b == null) {
			attr.addFlashAttribute("message", "操作失败");
		}else {
			attr.addFlashAttribute("message", "操作成功");
		}
		
		return "redirect:/admin/blogs";
	}
	
	
	
	@RequestMapping("/blogs/{id}/update")
	public String updatePage(Blog blog,Model model) {
		logger.info("Blog: "+blog);
		model.addAttribute("types", typeService.getAllType());
		model.addAttribute("tags", tagService.getAllTag());
		Blog b = blogService.getById(blog.getId());
		b.convertToString();
		model.addAttribute("blog", b);
		return "/admin/blogs-input.html";
	}
	
	@RequestMapping("/blogs/{id}/delete")
	public String deleteBlog(@PathVariable Long id,RedirectAttributes attr) {
		
		blogService.deleteBlog(id);
		attr.addFlashAttribute("message", "删除成功");
		
		return "redirect:/admin/blogs";
	}
	
	
	
}
