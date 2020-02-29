package com.myblog.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_blog")
public class Blog {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String content;
	private String firstPic;
	private long visitNum;
	private boolean donate;
	private boolean share;
	private boolean recommend;
	private String flag;
	private boolean comment;
	private boolean publish;
	private Timestamp  createdTime;
	private Timestamp updateTime;
	private String description;
	@Transient
	private String tagIds;
	@ManyToOne
	private Type type;
	
	@ManyToMany(cascade = {CascadeType.PERSIST})
	private List<Tag> tags = new ArrayList<>();
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="blog")
	private List<Comment> comments = new ArrayList<>();
	public Blog() {
		super();
		
	}
	
	public void convertToString() {
		if(tags != null && tags.size() != 0) {
			StringBuilder str = new StringBuilder("");
			for(Tag tag:tags) {
				str.append(tag.getId()+",");
			}
			tagIds = str.subSequence(0, str.length()).toString();
		}
		
	}
	
	
	public boolean isDonate() {
		return donate;
	}


	public void setDonate(boolean donate) {
		this.donate = donate;
	}


	public boolean isShare() {
		return share;
	}


	public void setShare(boolean share) {
		this.share = share;
	}


	public boolean isRecommend() {
		return recommend;
	}


	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}


	public boolean isComment() {
		return comment;
	}


	public void setComment(boolean comment) {
		this.comment = comment;
	}


	public boolean isPublish() {
		return publish;
	}


	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFirstPic() {
		return firstPic;
	}
	public void setFirstPic(String firstPic) {
		this.firstPic = firstPic;
	}
	
	public long getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(long visitNum) {
		this.visitNum = visitNum;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTagIds() {
		return tagIds;
	}
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", firstPic=" + firstPic + ", visitNum="
				+ visitNum + ", donate=" + donate + ", share=" + share + ", recommend=" + recommend + ", flag=" + flag
				+ ", comment=" + comment + ", publish=" + publish + ", createdTime=" + createdTime + ", updateTime="
				+ updateTime + ", description=" + description + ", tagIds=" + tagIds + ", type=" + type + ", tags="
				+ tags + ", user=" + user + ", comments=" + comments + "]";
	}


	
	
	
	
}
