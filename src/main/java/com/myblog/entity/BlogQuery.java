package com.myblog.entity;

public class BlogQuery {
	private String title;
	private boolean isRecommend;
	private Long typeId;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isRecommend() {
		return isRecommend;
	}
	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "BlogQuery [title=" + title + ", isRecommend=" + isRecommend + ", typeId=" + typeId + ", userId="
				+ userId + "]";
	}
	
	
	
	
}
