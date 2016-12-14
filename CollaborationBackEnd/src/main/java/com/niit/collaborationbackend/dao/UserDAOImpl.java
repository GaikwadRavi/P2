package com.niit.collaborationbackend.dao;

import java.util.List;


//import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.collaborationbackend.model.User;

//import com.backend.mode.UserDetails;

@EnableTransactionManagement
@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		
	}
	@Transactional
public boolean save(User userdetails)
{
	try{
	sessionFactory.getCurrentSession().saveOrUpdate(userdetails);
	return true;
	}catch (HibernateException e){
		e.printStackTrace();
		return false;
	
	} 
}
	@Transactional
	public boolean update(User userdetails)
	{
		try {
			sessionFactory.getCurrentSession().update(userdetails);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public void delete(String id)
	{
		User userDelete = new User();
		userDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userDelete);
		
		
	/*
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	*/
	}
	
	@Transactional
	public User get(String id)
	{
		String hql = "from User where id ='"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		
		if(list==null || list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
		
	}
	@Transactional
	public List<User> getName(String name)
	{
		
		//String hql = "from User where name ='Sagar'";
		String hql = "from User where name ='"+name+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		
		if(list==null || list.isEmpty())
		{
			return null;
		}
		else
		{
			return list;
		}
		
	}
	
	
	@Transactional
	 public User getRowById(String id) {
	  Session session = sessionFactory.openSession();
	  User user = (User) session.load(User.class, id);
	 // delete(user);
	  return user;
	 }
	
	@SuppressWarnings("deprecation")
	@Transactional
	public List<User> list()
	{
		
		String hql = " from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	
	}
	@Transactional
	public User authenticate(String id, String password) {
		// TODO Auto-generated method stub
		String hql = "from User where id='" + id + "' and " + " password ='" +password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if (list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	@Transactional
	public void setOnline(String userID) {
		//String hql = " UPDATE Friend SET isOnline = 'Y' where userID='"+ userID +"'";
		String hql = "UPDATE User SET isOnLine = true where id = '"+ userID +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	@Transactional
	public void setOffLine(String userID) {
		//String hql = "UPDATE Friend SET isOnline = 'N' where userID = '"+ userID +"'";
		String hql = "UPDATE User SET isOnLine = false where id = '"+ userID +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

}