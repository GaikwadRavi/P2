package com.niit.collaborationbackend.controller;

import java.sql.Timestamp;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.collaborationbackend.dao.BlogCommentDAO1;
import com.niit.collaborationbackend.dao.BlogDAO1;
import com.niit.collaborationbackend.model.Blog1;
import com.niit.collaborationbackend.model.BlogComment;
import com.niit.collaborationbackend.utility.IdGenerator;

@RestController
@RequestMapping("/myblogs")
public class BlogController1 {
	
	@Autowired
	Blog1 blog1;
	
	@Autowired
	BlogComment blogComment;
	
	@Autowired(required = true)
	BlogDAO1 blogDAO1;
	
	@Autowired
	BlogCommentDAO1 blogCommentDAO1;
	
	@GetMapping("/blogs/")
	public ResponseEntity<List<Blog1>> listAllBlogs() {
		List<Blog1> listOfBlogs = blogDAO1.listBlogsByCreatedAt('A');
		if(listOfBlogs.isEmpty())
		{
			return new ResponseEntity<List<Blog1>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog1>> (HttpStatus.OK);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<Blog1> getBlog(@PathVariable("blogId") String blogId)
	{
		this.blog1 = blogDAO1.getBlog(blogId);
		if(this.blog1 == null)
		{
			return new ResponseEntity<Blog1>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Blog1>(HttpStatus.OK);
	}
	
	@PostMapping("/blogs/")
	public ResponseEntity<Blog1> createBlog(@RequestBody Blog1 blog1, UriComponentsBuilder ucBuilder )
	{
		blog1.setBlogId(IdGenerator.generateId("BLG"));
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		blog1.setCreatedAt(timestamp);
		blog1.setModifiedAt(timestamp);
		blog1.setStatus('P');
		blogDAO1.saveOrUpdateBlog(blog1);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/blog/{id}").buildAndExpand(blog1.getBlogId()).toUri());
		return new ResponseEntity<Blog1>(httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<Blog1> updateBlog(@PathVariable("blogId") String blogId, @RequestBody Blog1 blog1) {
		this.blog1 = blogDAO1.getBlog(blogId);
		if(this.blogDAO1 == null)
		{
			return new ResponseEntity<Blog1>(HttpStatus.NOT_FOUND);
		}
		this.blog1.setBlogName(blog1.getBlogName());
		this.blog1.setBlogDescription(blog1.getBlogDescription());
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		this.blog1.setModifiedAt(timestamp);
		blogDAO1.saveOrUpdateBlog(this.blog1);
		return new ResponseEntity<Blog1>(this.blog1, HttpStatus.OK);	
	}
	
	@DeleteMapping("/blogs/{blogId}")
	public ResponseEntity<Blog1> deleteBlog(@PathVariable("blogId") String blogId)
	{
		this.blog1 = blogDAO1.getBlog(blogId);
		if(this.blog1 ==null)
		{
			return new ResponseEntity<Blog1>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog1>(HttpStatus.NO_CONTENT);
	}

}