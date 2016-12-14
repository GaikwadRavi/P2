package com.niit.collaborationbackend.dao;

import java.util.List;




import org.hibernate.Query;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationbackend.model.Blog1;

@EnableTransactionManagement
@Repository(value="blogDAO1")
public class BlogDAOImpl1 implements BlogDAO1 {
	@Autowired
	SessionFactory sessionFactory;

	public BlogDAOImpl1(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean saveOrUpdateBlog(Blog1 blog) {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
		return true;

	}
	
	@Transactional
	public void deleteBlog(String blogId) {
		Blog1 blogTodelete = new Blog1();
		blogTodelete.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blogTodelete);

	}

	@Transactional
	public Blog1 getBlog(String blogId) {
		String hql = "from Blog1 where blogId=:blogId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("blogId",blogId);
		List<Blog1> gotBlog = query.list();
		if (gotBlog != null && !gotBlog.isEmpty())
			return gotBlog.get(0);
		return null;
	}

	@Transactional
	public List<Blog1> listBlogs() {
		String hql = "from Blog1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog1> listOfBlogs = query.list();
		return listOfBlogs;
	}

	@Transactional
	public List<Blog1> listBlogsByCreatedAt(char status) {
		String hql = "from Blog1  where status=:status ORDER BY createdAt desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("status", status);
		List<Blog1> listOfBlogs = query.list();
		return listOfBlogs;
		
	}


}
