package com.niit.collaboration;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.UserDAO;
import com.niit.collaborationbackend.model.User;

public class UserTestCase {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User userdetails;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init(){
		System.out.println("In INIT Method...");
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
		System.out.println("In UsersDAO");
		userdetails = (User) context.getBean("user");
		System.out.println("In Users");
	}
	
	@Test
	public void addUserTestCase(){
		System.out.println("Trying to create a new user");
		userdetails.setId("4");
		userdetails.setName("Shrikant Gosavi");
		userdetails.setAddress("Indapur");
		userdetails.setPassword("1234");
		userdetails.setMobile("3652145623");
		userdetails.setEmail("ShrAnu@gmail.com");
		userdetails.setOnLine(true);
		userdetails.setRole("User_Student");
		//userDAO.save(userdetails);
		System.out.println("User Added Successfully");
		assertEquals("addUserTestCase",userDAO.save(userdetails),true);
	}

	/*@Test
	public void deleteUserTestCase(){
		System.out.println("Trying to update a new user");
		userdetails.setId("4");
		userDAO.delete(userdetails);
		System.out.println("User Deleted Successfully");
		assertEquals("deleteUserTestCase",userDAO.delete(userdetails),true);
	}
	
	@Test
	public void getAllCreateTestCase(){
		assertEquals("getAllUsersTestCase", 2, userDAO.list().size());
	}
	
	@Test
	public void getUserTestCase(){
		assertEquals("getAllUsersTestCase", userDAO.getRowById("4"));
	}
	@Test
	public void getUserTestCase(){
		assertEquals("getAllUsersTestCase", userDAO.authenticate("1", "1234"));
	}*/
}
