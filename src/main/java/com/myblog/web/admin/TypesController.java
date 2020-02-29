package com.myblog.web.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myblog.entity.Type;
import com.myblog.service.TypeService;

@Controller
@RequestMapping("/admin")
public class TypesController {
	
	@Autowired
	TypeService typeService;
	
	@GetMapping("/types")
	public String types(@PageableDefault(value=6,sort= {"id"},direction=Sort.Direction.ASC) Pageable pageable, Model model) {
		
		Page<Type> page = typeService.listType(pageable);
		model.addAttribute("page", page);
		
		return "/admin/types.html";
		
	}
	
	
	/**
	 * 
	 * @param model
	 * @return
	 * Q:Model有用，而RedirectAtttributes不起作用
	 */
	@GetMapping("/types/input")
	public String typeInput(Model model) {
		
		model.addAttribute("type", new Type(-1,""));
		
		return "/admin/types-input.html";
		
	}
	
	/**
	 * 
	 * @param type
	 * @param result
	 * @param attr
	 * @return
	 * Q:@valid，BindingResult搭配使用
	 */
	@RequestMapping("/types/add")
	public String addType(@Valid Type type,BindingResult result,RedirectAttributes attr) {
		
		Type t = typeService.getByName(type.getName());
		if(t != null) {
			result.rejectValue("name", "nameError", "不能添加重复类型");
		}
		if(result.hasErrors()) {
			return "/admin/types-input.html";
		}
		Type t2 = typeService.save(type);
		if(t2 == null) {
			attr.addFlashAttribute("message", "添加失败");
		}else {
			
			attr.addFlashAttribute("message", "添加成功");
		}
		return "redirect:/admin/types";
	}
	/**
	 * 
	 * @param id
	 * @param type
	 * @param result
	 * @param attr
	 * @return
	 * Q:BindingResult 要跟在@Valid之后
	 * Q:跳转到修改分类页面
	 */
	@RequestMapping("/types/{id}/update")
	public String toUpdate(@PathVariable Long id,Model model) {
		
		model.addAttribute("type", typeService.getById(id));
		return "/admin/types-input.html";
		
	}
	
	@RequestMapping("/types/update/{id}")
	public String update(@PathVariable Long id,@Valid Type type,BindingResult result,RedirectAttributes attr) {
		Type t = typeService.getByName(type.getName());
		if(t != null) {
			result.rejectValue("name", "nameError", "不能添加重复类型");
		}
		if(result.hasErrors()) {
			return "/admin/types-input.html";
		}
		Type t2 = typeService.update(id,type);
		if(t2 == null) {
			attr.addFlashAttribute("message", "修改失败");
		}else {
			attr.addFlashAttribute("message", "修改成功");
		}
		attr.addFlashAttribute("type", typeService.getById(id));
		
		
		return "redirect:/admin/types";
	}
	
	@RequestMapping("/types/{id}/delete")
	public String delete(@PathVariable Long id,RedirectAttributes attr) {
		Type t = typeService.delete(id);
		if(t == null) {
			attr.addFlashAttribute("message", "删除失败");
		}else {
			attr.addFlashAttribute("message", "删除成功");
		}
		return "redirect:/admin/types";
	}
}
