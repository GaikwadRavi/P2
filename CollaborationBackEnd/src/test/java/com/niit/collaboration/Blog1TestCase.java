package com.niit.collaboration;

import static org.junit.Assert.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.BlogCommentDAO1;
import com.niit.collaborationbackend.dao.BlogDAO1;
import com.niit.collaborationbackend.model.Blog1;
import com.niit.collaborationbackend.model.BlogComment;

public class Blog1TestCase {

	@Autowired
	BlogDAO1 blogDAO1;
	
	@Autowired
	Blog1 blog1;
	
	@Autowired
	BlogComment blogComment;
	
	@Autowired
	BlogCommentDAO1 blogCommentDAO1;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blogDAO1 = (BlogDAO1) context.getBean("blogDAO1");
		System.out.println("I am in Blog1TestCase");
		
		blog1 = (Blog1) context.getBean("blog1");
		System.out.println("I am in Blog1TestCase2");
		
		blogComment = (BlogComment) context.getBean("blogComment");
		blogCommentDAO1 = (BlogCommentDAO1) context.getBean("blogCommentDAO1");
		System.out.println("I am in Blog1TestCase3");
		
	}
	Date date = new Date();
	long time = date.getTime();
	Timestamp timestamp = new Timestamp(time);
	@Test
	public void addBlogTestCase() {
		

		blogComment.setBlog(blog1);
		blogComment.setCommentedAt(timestamp);
		blogComment.setBlogCommentId("01");
		blogComment.setBlogCommentContent("Hi hello How are u");
		blogComment.setUserId("2");
		
		//blogCommentDAO1.saveOrUpdateBlogComment(blogComment);
		
		assertEquals("addBolgTestCase",blogCommentDAO1.saveOrUpdateBlogComment(blogComment),true);
		
		
		
		blog1.setBlogId("02");
		blog1.setBlogName("DT-03");
		blog1.setBlogDescription("Lai Lai Bhari");
		blog1.setCreatedAt(timestamp);
		blog1.setModifiedAt(timestamp);
		blog1.setUserId("3");
		blog1.setStatus('A');
		//blog1.setBlogComment((Set<BlogComment>) blogComment);
		
		//blog1.setBlogComments(blogComment);
		//blogDAO1.saveOrUpdateBlog(blog1);
		
		assertEquals("addBolgTestCase",blogDAO1.saveOrUpdateBlog(blog1),true);
	
	
	
	}
}
