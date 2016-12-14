package com.niit.collaborationbackend.dao;

import java.util.List;



import com.niit.collaborationbackend.model.User;


public interface UserDAO {
	public boolean save(User userdetails);
	
	public boolean update(User userdetails);
	
	public void delete(String id);
	
	public User get(String id);
	
	public User getRowById(String id);
	
	public List<User> getName(String name);
	
	public List<User> list();
	public User authenticate(String id,String password);
	
    public void setOnline(String userID);
	public void setOffLine(String userID);
	


}
