package com.myblog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_tag")
public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1266553552147464022L;
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@ManyToMany(mappedBy="tags")
	private List<Blog> blogs = new ArrayList<>();
	
	public Tag() {
		super();
		
	}
	public Tag(long id, String name) {
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
		return "Tag [id=" + id + ", name=" + name + "]";
	}
	
}
