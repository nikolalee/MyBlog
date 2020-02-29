package com.myblog.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myblog.entity.Comment;
import com.myblog.entity.User;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private BlogService blogService;
	@Value("${comment.avatar}")
	private String avatar;
	
	@RequestMapping("/comments/{id}")
	public String getComments(@PathVariable Long id,Model model) {
		
		model.addAttribute("comments", commentService.getById(id));
		
		return "blog.html :: commentList";
	}
	
	@RequestMapping("/comments")
	public String saveComment(Comment comment,HttpSession session) {
		Long id = comment.getBlog().getId();
		comment.setBlog(blogService.getById(id));
		User user = (User)session.getAttribute("USER_SESSION");
		if(user == null) {
			comment.setAvatar(avatar);
		}else {
			comment.setAvatar(user.getAvatar());
			comment.setAdminComment(true);
		}
		commentService.saveComment(comment);
		
		return "redirect:/comments/"+id;
	}
}
