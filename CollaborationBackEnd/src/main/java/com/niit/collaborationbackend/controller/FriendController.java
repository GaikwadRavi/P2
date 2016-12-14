package com.niit.collaborationbackend.controller;

import java.util.List;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.User;

@RestController
public class FriendController {
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	/*@Autowired(required =true)
	User loggedInUser;
	*/
	
	
	@RequestMapping(value = "/myFriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>>getMyFriends(HttpSession session) {
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		List<Friend> myFriends = friendDAO.getMyFriends(loggedInUserID);
		if(myFriends.isEmpty())
		{
			System.out.println("I am Empty!");
			friend.setErrorCode("404");
			friend.setErrorMessage("You does not have any friends");
			myFriends.add(friend);
		}
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID") String friendID, HttpSession session) {
		
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		friend.setUserID(loggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus("N");
		friendDAO.save(friend);
		
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	@RequestMapping(value = "/unFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendID") String friendID, HttpSession session) {
		
		/*User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUserID(loggedInUser.getId());
		friend.setFriendID(friendID);
		friend.setStatus("U");
		friendDAO.update(friend);*/
		
		updateRequest(friendID, "U", session);
		
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	@RequestMapping(value = "/rejectFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriendFriendRequest(@PathVariable("friendID") String friendID, HttpSession session) {
		
		/*String loggedInUserID = (String) session.getAttribute("loggedInUser");
		friend.setUserID(loggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus("R");
		friendDAO.update(friend);*/
		
		updateRequest(friendID, "N", session);
		
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	private void updateRequest(String friendID, String status, HttpSession session)
	{
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		friend.setUserID(loggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus(status); //N-New, R-Reject, A-Accepted
		friendDAO.update(friend);
	}
	@RequestMapping(value = "/geMyFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<Friend> getMyFriendRequests(HttpSession session) {
		//User loggedInUser = (User) session.getAttribute("loggedInUser");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		friendDAO.getNewFriendRequest(loggedInUserID);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/acceptFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriendFriendRequest(@PathVariable("friendID") String friendID, HttpSession session)
	{
		updateRequest(friendID, "A", session);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/myFriends/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendsTemp(@PathVariable("id") String id) {
		List<Friend> myFriends = friendDAO.getMyFriends(id);
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}
	
	
}
