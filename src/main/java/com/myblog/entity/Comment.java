package com.myblog.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="t_comment")
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5527961093860521081L;
	@Id
	@GeneratedValue
	private long id;
	private String nickname;
	private String email;
	private String avatar; //头像
	private String content;
	private Timestamp createTime;
	private boolean adminComment;
	@ManyToOne
	private Comment parentComment;
	@OneToMany(mappedBy="parentComment")
	private List<Comment> subComments = new ArrayList<>();
	@ManyToOne
	private Blog blog;
	public Comment() {
		super();
		
	}
	
	

	public Comment(long id, String nickname, String email, String avatar, String content, Timestamp createTime,
			boolean adminComment, Comment parentComment, List<Comment> subComments, Blog blog) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.avatar = avatar;
		this.content = content;
		this.createTime = createTime;
		this.adminComment = adminComment;
		this.parentComment = parentComment;
		this.subComments = subComments;
		this.blog = blog;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	

	public boolean isAdminComment() {
		return adminComment;
	}

	public void setAdminComment(boolean adminComment) {
		this.adminComment = adminComment;
	}


	public Comment getParentComment() {
		return parentComment;
	}



	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}



	public List<Comment> getSubComments() {
		return subComments;
	}

	public void setSubComments(List<Comment> subComments) {
		this.subComments = subComments;
	}







	@Override
	public String toString() {
		return "Comment [id=" + id + ", nickname=" + nickname + ", email=" + email + ", avatar=" + avatar + ", content="
				+ content + ", createTime=" + createTime + ", adminComment=" + adminComment + ", parentComment="
				+ parentComment + ", subComments=" + subComments + ", blog=" + blog + "]";
	}



	

	
	
}
