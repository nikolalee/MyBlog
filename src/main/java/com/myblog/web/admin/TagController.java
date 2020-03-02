package com.myblog.web.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myblog.entity.Tag;
import com.myblog.service.TagService;

@Controller
@RequestMapping("/admin")
public class TagController {
	
	@Autowired
	TagService tagService;
	
	@RequestMapping("/tags")
	public String tagPage(Model model,
			@PageableDefault(size=6,sort= {"id"},direction=Sort.Direction.DESC) Pageable pageable) {
		
		model.addAttribute("page",tagService.getListTag(pageable));
		return "admin/tags.html";
	}
	
	@RequestMapping("/tags/addPage")
	public String addPage(Model model) {
		model.addAttribute("tag", new Tag());
		return "admin/tags-input.html";
	}
	
	@RequestMapping("/tags/add")
	public String addTag(@Valid Tag tag,BindingResult result,RedirectAttributes attr) {
		
		Tag t = tagService.getByName(tag.getName());
		if(t != null) {
			result.rejectValue("name", "nameError", "不能添加重复标签");
			return "admin/tags-input.html";
		}
		
		Tag t2 = tagService.addTag(tag);
		if(t2 == null) {
			attr.addFlashAttribute("message", "添加失败");
		}else {
			attr.addFlashAttribute("message", "添加成功");
		}
		
		return "redirect:/admin/tags";
	}
	
	@RequestMapping("/tags/{id}/update")
	public String updatePage(@PathVariable Long id,Model model) {
		
		model.addAttribute("tag", tagService.getById(id));
		return "admin/tags-input.html";
	}
	
	@RequestMapping("/tags/update/{id}")
	public String updateTag(@PathVariable Long id,@Valid Tag tag,BindingResult result,RedirectAttributes attr) {
		
		Tag t = tagService.getByName(tag.getName());
		if(t != null) {
			result.rejectValue("name", "nameError", "不能添加重复标签");
			return "admin/tags-input.html";
		}
		
		Tag t2 = tagService.updateTag(id, tag);
		if(t2 == null) {
			attr.addFlashAttribute("message", "修改失败");
		}else {
			attr.addFlashAttribute("message", "修改成功");
		}
		
		return "redirect:/admin/tags";
	}
	
	@RequestMapping("/tags/{id}/delete")
	public String deleteTag(@PathVariable Long id,RedirectAttributes attr) {
		tagService.deleteTag(id);
		attr.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/tags";
	}
	
	
}
