package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.BlogComment;

public interface BlogCommentDAO1 {
	
	public boolean saveOrUpdateBlogComment(BlogComment blogComment);
	
	void deleteBlogComment(String blogCommentId);

	BlogComment getBlogComment(String blogCommentId);

	List<BlogComment> listBlogComments();
	
	List<BlogComment> listBlogByCreatedAt(String blogId);

}
