package com.niit.collaborationbackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.dao.UserDAO;
import com.niit.collaborationbackend.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	FriendDAO friendDAO;
	//For retrieve User list(It work Fine)(271016)
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		System.out.println("I am user list");
		List<User> user= userDAO.list();
		if(user.isEmpty())
		{
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		
	}
	//For Add User(It work Fine)271016
	@RequestMapping(value="/users/", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (userDAO.get(user.getId())==null) {
			userDAO.save(user);
			user.setErrorCode("200");
			user.setErrorMessage("Thank you for Registration");
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		}
		user.setErrorCode("404");
		user.setErrorMessage("User alredy exist with Id:"+user.getId());
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/makeAdmin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("id") String empID) {
		User user = userDAO.get(empID);
		
		if(user==null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Employee does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND); //200
		}
		String role = " {employee, User_Admin}  ";
		user.setRole(role);
		userDAO.update(user);
		return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND); //200
		
	}
	
	@RequestMapping(value="/users/{name}", method=RequestMethod.POST)
	public ResponseEntity<List<User>> searchList(@PathVariable("name") String name) {
		System.out.println("Hi I am in Search mapping");
		List<User> user= userDAO.getName(name);
		System.out.println("I passed getName() function");
		if(user.isEmpty())
		{
			System.out.println("I am in empty");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		System.out.println("Search list is not empty");
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		
	}
	//This method for get Single user by Id(it work fine)
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		System.out.println("Hi I am getById");
		User user= userDAO.get(id);
		//userDAO.get(id);
		if (user==null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User does not exits");
			System.out.println("User Id not exist");
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
			
		}
		System.out.println("User Exist");
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	//It work Fine(271016)
	@RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		//User user= userDAO.get(id);
		if (userDAO.get(id)==null) {
			System.out.println("user not found");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
			
		}
		System.out.println("user found");
		userDAO.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	//This method work fine(271016)
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		User user= userDAO.get(id);
		if (user==null) {
			System.out.println("User Id not Found");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
			
		}
		System.out.println("User Id Founded");
		userDAO.delete(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	//It work fine(271016)
	@RequestMapping(value="/users/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session) {
		System.out.println("Hi am Ravi");
		user = userDAO.authenticate(user.getId(),user.getPassword());
		System.out.println("Hi am Ravi");
		if(user==null)
		{
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid credentials. Please enter valid credentials");
			System.out.println("Invalid credentials");
			return new  ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
		else
		{
			user.setErrorCode("200");
			System.out.println("Credentials valid");
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserID", user.getId());
			friendDAO.setOnline(user.getId());
			userDAO.setOnline(user.getId());
		
			
			System.out.println("User Login Successful");
		}
		return new  ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		String loggedInUserID = (String)session.getAttribute("loggedInUserID");
		friendDAO.setOffLine(loggedInUserID);
		userDAO.setOffLine(loggedInUserID);
		
		session.invalidate();
		
		return("You Successfully loggouedout");
		
	}
	
	
	
	
}
