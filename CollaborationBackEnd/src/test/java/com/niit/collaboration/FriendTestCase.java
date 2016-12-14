package com.niit.collaboration;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.model.Friend;

public class FriendTestCase {

	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		friendDAO = (FriendDAO) context.getBean("friendDAO");
		
		friend = (Friend) context.getBean("friend");
	}
	
	@Test
	public void addFriendTestCase() {
		
		//friend.setId(3);
		friend.setUserID("420");
		friend.setStatus("A");
		friend.setIsOnline('Y');
		friend.setFriendID("9");
		
		//friendDAO.save(friend);
		
		assertEquals("addFriendTestCase",friendDAO.save(friend),true);
		
		
		
	}
}
