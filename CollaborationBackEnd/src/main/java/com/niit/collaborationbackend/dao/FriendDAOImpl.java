package com.niit.collaborationbackend.dao;

import java.util.List;



import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationbackend.model.Friend;

@EnableTransactionManagement
@Repository(value="friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory) {
		try {
				this.sessionFactory = sessionFactory;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Integer getMaxId()
	{
		String hql = "Select max(id) from Friend";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxID = (Integer) query.uniqueResult();
		return maxID;
	}
	
	@Transactional
	public boolean update(Friend friend)
	{
		try {
				sessionFactory.getCurrentSession().update(friend);
				return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Transactional
	public void delete(String userID, String friendID) {
		Friend friend = new Friend();
		friend.setFriendID(friendID);
		friend.setUserID(userID);
		sessionFactory.getCurrentSession().delete(friend);
	}
	
	@Transactional
	public List<Friend> getMyFriends(String userID) {
		String hql = "from Friend where userID = " + "'"+ userID +"' and status ='"+ "A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Friend> list = (List<Friend>) query.list();
		
		return list;
	}
	
	@Transactional
	public List<Friend> getNewFriendRequest(String userID)
	{
		String hql = "from Friend where userID=" + "'" + userID + "'and status ='"+ "N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Friend> list = (List<Friend>) query.list();
		return list;
		
	}
	
	@Transactional
	public Friend get(String userID, String friendID)
	{
		String hql = "from Friend where userID = " + "'" + userID + "'and FRIEND_ID='"+ friendID + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Friend> list = (List<Friend>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
		
	}
	@Transactional
	public void setOnline(String userID) {
		String hql = " UPDATE Friend SET isOnline = 'Y' where userID='"+ userID +"'";
		//String hql = "UPDATE Friend SET status = 'N' where userID = '"+ userID +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	@Transactional
	@Override
	public boolean save(Friend friend) {
		try {
				friend.setId(getMaxId()+1);
				
				sessionFactory.getCurrentSession().save(friend);
				return true;
		} catch(HibernateException e) {
			
				e.printStackTrace();
				return false;
		}
	}

	@Transactional
	public void setOffLine(String userID) {
		String hql = "UPDATE Friend SET isOnline = 'N' where userID = '"+ userID +"'";
		//String hql = "UPDATE Friend SET status = 'N' where userID = '"+ userID +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

}
