package com.myblog.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.myblog.NotFoundException;
import com.myblog.entity.Blog;
import com.myblog.entity.BlogQuery;
import com.myblog.repository.BlogRepository;
import com.myblog.service.BlogService;
import com.myblog.utils.MarkDownUtils;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogRepository blogRepo;

	@Override
	public Blog getById(Long id) {
		return blogRepo.getOne(id);
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable,BlogQuery blog) {
		
		return blogRepo.findAll(new Specification<Blog>() {

			/**
			 * Q：这段不太懂
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<>();
				if(!"".equals(blog.getTitle()) && blog.getTitle() != null) {
					list.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
				}
				
				if(blog.isRecommend()) {
					list.add(cb.equal(root.<Boolean>get("isRecommend"), blog.isRecommend()));
				}
				
				if(blog.getTypeId() != null) {
					list.add(cb.equal(root.<Long>get("type").get("id"), blog.getTypeId()));
				}
				
				if(blog.getUserId() != null) {
					list.add(cb.equal(root.<Long>get("user").get("id"),blog.getUserId()));
				}
				
				query.where(list.toArray(new Predicate[list.size()]));
				return null;
			}
			
		}, pageable);
	}

	@Transactional
	@Override
	public Blog updateBlog(Long id, Blog blog) {
		return blogRepo.save(blog);
	}

	@Transactional
	@Override
	public Blog addBlog(Blog blog) {
		if(blog.getId() == 0L) {
			blog.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			blog.setVisitNum(0);
		}else {
			Blog b = blogRepo.getOne(blog.getId());
			blog.setCreatedTime(b.getCreatedTime());
			blog.setVisitNum(b.getVisitNum());
			blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		}
		
		return blogRepo.save(blog);
	}

	@Transactional
	@Override
	public void deleteBlog(Long id) {
		blogRepo.deleteById(id);
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable) {
		return blogRepo.findAll(pageable);
	}

	@Override
	public List<Blog> getTopRecommendBlog(Integer size) {
		Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
		Pageable pageable = PageRequest.of(0,size,sort);
		return blogRepo.getTopRecommendBlog(pageable);
	}

	@Override
	public Page<Blog> searchBlog(String query,Pageable pageable) {
		return blogRepo.getBlogLikeQuery(query, pageable);
	}
	
	@Override
	public Blog getAndConvert(Long id) {
		Blog blog = blogRepo.getOne(id);
		if(blog == null) {
			throw new NotFoundException("未找到该博客");
		}
		
		String md = blog.getContent();
		Blog b = new Blog();
		BeanUtils.copyProperties(blog, b);
		b.setContent(MarkDownUtils.convertToHtml(md));
		
		blogRepo.updateVisitNum(id);//更新访问次数
		
		return b;
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable, Long tagId) {
		return blogRepo.findAll(new Specification<Blog>() {

			/**
			 * 通过tagId查找Blog列表
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Object, Object> join = root.join("tags");
				return cb.equal(join.get("id"),tagId);
			}
			
		}, pageable);
		}
	
	//根据年份获取博客
	@Override
	public Map<String, List<Blog>> getArchiveBlog() {
		List<String> years = blogRepo.getGroupYear();
		Map<String,List<Blog>> blogs = new HashMap<>();
		for(String year:years) {
			blogs.put(year, blogRepo.getByYear(year));
		}
		return blogs;
	}

	@Transactional
	@Override
	public int updateVisitNum(Long id) {
		return blogRepo.updateVisitNum(id);
	}

	@Override
	public Long getBlogNum() {
		return blogRepo.count();
	}
 
}
