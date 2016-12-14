package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.Friend;

public interface FriendDAO {
	
	public List<Friend> getMyFriends(String userID);
	
	public Friend get(String userID, String friendID);
	
	
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	
	public void delete(String userID, String friendID);
	
	public List<Friend> getNewFriendRequest(String userID);
	
	public void setOnline(String userID);
	
	public void setOffLine(String userID);
	

}
