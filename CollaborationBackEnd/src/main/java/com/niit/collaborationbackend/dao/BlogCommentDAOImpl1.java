package com.niit.collaborationbackend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationbackend.model.BlogComment;
@EnableTransactionManagement
@Repository("blogCommentDAO1")
public class BlogCommentDAOImpl1 implements BlogCommentDAO1 {
	
	@Autowired
	SessionFactory sessionFactory;

	public BlogCommentDAOImpl1(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdateBlogComment(BlogComment blogComment) {
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
		return true;
	}
	
	@Transactional
	public void deleteBlogComment(String blogCommentId) {
		BlogComment blogCommentToDelete = new BlogComment();
		blogCommentToDelete.setBlogCommentId(blogCommentId);
		sessionFactory.getCurrentSession().delete(blogCommentToDelete);

	}
	
	@Transactional
	public BlogComment getBlogComment(String blogCommentId) {
		String hql = "from BlogComment where blogCommentId=:blogCommentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("blogCommentId", blogCommentId);
		List<BlogComment> gotBlogComment = query.list();
		if (gotBlogComment != null && !gotBlogComment.isEmpty())
			return gotBlogComment.get(0);
		return null;
	}
	
	@Transactional
	public List<BlogComment> listBlogComments() {
		String hql = "from BlogComment";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<BlogComment> listOfBlogComments = query.list();
		return listOfBlogComments;
	}
	

	@Transactional
	public List<BlogComment> listBlogByCreatedAt(String blogId) {
		String hql = "from BlogComment where blog1.blogId=:blogId ORDER BY commentedAt DESC ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("blogId", blogId);
		List<BlogComment> listOfBlogComments = query.list();
		return listOfBlogComments;
	}


}
