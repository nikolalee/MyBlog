package com.myblog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myblog.NotFoundException;
import com.myblog.entity.User;
import com.myblog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String loginPage() {
		return "/admin/login.html";
	}
	
	@RequestMapping("/user/login")
	public String  login(HttpServletRequest req, String username,String password) {
		
		User user = userService.getUserByUsernameAndPassword(username,password);
		
		if(user == null) {
			throw new NotFoundException("用户未找到");
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("USER_SESSION", user);
		
		return "redirect:/admin/index.html";
		
	}
	
	
    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest req) {
    	
    	req.getSession().invalidate();
    	
    	return "recirect:index.html";
    }
    
    

}
