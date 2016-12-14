package com.niit.collaboration;

import java.sql.Timestamp;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.BlogCommentDAO1;
import com.niit.collaborationbackend.dao.BlogDAO1;
import com.niit.collaborationbackend.model.Blog1;
import com.niit.collaborationbackend.model.BlogComment;

public class Blog1Test {
	
	public static void main(String args[]) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		Blog1 blog1 = (Blog1) context.getBean("blog1");
		BlogDAO1 blogDAO1 = (BlogDAO1) context.getBean("blogDAO1");
		
		BlogComment blogComment = (BlogComment) context.getBean("blogComment");
		BlogCommentDAO1 blogCommentDAO1 = (BlogCommentDAO1) context.getBean("blogCommentDAO1");
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		
		blog1.setBlogName("Mi And Myself");
		blog1.setBlogDescription("This is  new blog created");
		blog1.setStatus('A');
		blog1.setCreatedAt(timestamp);
		blog1.setModifiedAt(timestamp);

		blog1.setUserId("URS001");
		
		blogDAO1.saveOrUpdateBlog(blog1);
		
		blogComment.setBlog(blog1);
		blogComment.setCommentedAt(timestamp);
		blogComment.setBlogCommentContent("Lorem ipsum dolor sit amet, "
				+ "consectetur adipisicing eliak vubhefuNOEFJWOIWJEZsd CA;ukhdW kjfweYBGRCukqjwhduqih3r t. Exsdfhnvvjdsagvozs mkvslbgkhsjngvmio;klpedita esse tempora impedit magnam magni ipsum molestias et autem ea rerum.");
		blogComment.setUserId("USR001");
		blogCommentDAO1.saveOrUpdateBlogComment(blogComment);
		
BlogComment blogComment1 = new BlogComment();
		
		blogComment1.setBlog(blog1);
		blogComment1.setCommentedAt(timestamp);
		blogComment1.setBlogCommentContent("Lorem ipsum dolor sit amet, updated   "
				+ "consectetur adipisicing eliak vubhefuNOEF uJWOIWJEZsd CA;ukhdW kjfweYBGRCukqjwhduqih3r t. Exsdfhnvvjdsagvozs mkvslbgkhsjngvmio;klpedita esse tempora impedit magnam magni ipsum molestias et autem ea rerum.");
		blogComment1.setUserId("USR001");
	blogCommentDAO1.saveOrUpdateBlogComment(blogComment1);
	
	if (blogDAO1.getBlog("blg005") != null) {
		System.out.println("blog exist");
	} else {
		System.out.println("NOt exist");
	}

	 System.out.println(blogDAO1.listBlogs());
	
	System.out.println(blogDAO1.listBlogsByCreatedAt('P').toString());

		
	}
}
