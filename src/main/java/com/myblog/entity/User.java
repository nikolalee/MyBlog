package com.myblog.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8765913085458339335L;
	@Id
	@GeneratedValue
	private long id;
	private String nickname;
	private String username;
	private String password;
	private String email;
	private Integer type;
	private String avatar;
	private Timestamp createTime;
	private Timestamp updateTime;
	@OneToMany
	private List<Blog> blogs = new ArrayList<>();
	public User() {
		super();
		
	}
	
	public User(long id, String nickname, String username, String password, String email, Integer type, String avatar,
			Timestamp createTime, Timestamp updateTime, List<Blog> blogs) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.avatar = avatar;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.blogs = blogs;
	}

	public long getId() {
		return id;
	}
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", type=" + type + ", avatar=" + avatar + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
