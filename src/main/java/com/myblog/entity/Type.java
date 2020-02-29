package com.myblog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 
 * @author nikola
 * q:分类信息
 */

@Entity
@Table(name="t_type")
public class Type {
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	private String name;
	@OneToMany
	private List<Blog> blogs = new ArrayList<>();
	
	
	public Type() {
		super();
		
	}
	public Type(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + "]";
	}
	
}
