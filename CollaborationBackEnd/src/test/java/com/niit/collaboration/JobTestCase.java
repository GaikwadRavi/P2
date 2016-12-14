package com.niit.collaboration;

import static org.junit.Assert.*;


import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.JobDAO;
import com.niit.collaborationbackend.dao.UserDAO;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.User;

public class JobTestCase {


	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	Job job;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init(){
		System.out.println("In INIT Method...");
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		jobDAO = (JobDAO) context.getBean("jobDAO");
		System.out.println("In UsersDAO");
		job = (Job) context.getBean("job");
		System.out.println("In Jobs");
	}
	Date date = new Date();
	long time = date.getTime();
	Timestamp timestamp = new Timestamp(time);
	
	@Test
	public void addUserTestCase(){
		System.out.println("Trying to create a new job");
		job.setTitle("Network Engineer");
		job.setDescription("Required CCNA");
		job.setDateTime(timestamp);
		job.setQualification("B.E");
		job.setStatus('V');
		
		/*userdetails.setId("4");
		userdetails.setName("Shrikant Gosavi");
		userdetails.setAddress("Indapur");
		userdetails.setPassword("1234");
		userdetails.setMobile("3652145623");
		userdetails.setEmail("ShrAnu@gmail.com");
		userdetails.setOnLine(true);
		userdetails.setRole("User_Student");
		userDAO.save(userdetails);*/
		System.out.println("User Added Successfully");
		assertEquals("addJobTestCase",jobDAO.postJob(job),true);
	}

}
