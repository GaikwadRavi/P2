package com.niit.collaborationbackend.dao;

import java.util.List;
import com.niit.collaborationbackend.model.Blog1;

public interface BlogDAO1 {
	
	public boolean saveOrUpdateBlog(Blog1 blog1);
	
	void deleteBlog(String blogId);
	
	Blog1 getBlog(String blogId);
	
	List<Blog1> listBlogs();
	
	List<Blog1> listBlogsByCreatedAt(char status);
	

}
