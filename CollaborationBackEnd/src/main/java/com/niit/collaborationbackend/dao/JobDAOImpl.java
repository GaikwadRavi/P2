package com.niit.collaborationbackend.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;

@Repository(value="jobDAO")
public class JobDAOImpl implements JobDAO {
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Integer getMaxID()
	{
		String hql = "Select max(id) from Job";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxID = (Integer) query.uniqueResult();
		return maxID;
	}
	private Integer getMaxID1()
	{
		String hql = "Select max(id) from JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxID = (Integer) query.uniqueResult();
		return maxID;
	}
	
	@Transactional
	public boolean postJob(Job job) {
		
		try {
				job.setId(getMaxID()+1);
			sessionFactory.getCurrentSession().save(job);
		}
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Transactional
	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Transactional
	public JobApplication getJobApplication(String userID, int jobID) {
		String hql = "from JobApplication where userID = '"+ userID+"' and jobID = "+ jobID;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
		
	}
	@Transactional
	public List<Job> getMyAppliedJobs(String userID) {
		String hql = "from Job where id in (select jobID from JobApplication where userID = '"+ userID+"')";
		//String hql = "from JobApplication where userID = '"+ userID+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("hello i am");
		List<Job> list = (List<Job>) query.list();
		return list;
		//return (JobApplication) query.list();
	}
	@Transactional
	public List<Job> getAllVacantJobs() {
		String hql = "from Job where status = 'V' ";
		//String hql = " from C_JOB";
		System.out.println("I am in job list");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("I am in job list");
		
		return query.list();
	}
	@Transactional
	public boolean applyForJob(JobApplication jobApplication) {
		try {
				jobApplication.setId(getMaxID1()+1);
			sessionFactory.getCurrentSession().saveOrUpdate(jobApplication);
		}
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Transactional
	public List<Job> getAllJobs() {
		String hql = "from Job";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
	@Transactional
	public boolean callForInterview(String userID, int jobID)
	{
		
		try {
			String hql = "UPDATE JobApplication SET status = 'C' where userID ='"+userID+"' and jobID="+jobID;
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
	}
	catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
	}
		
	@Transactional
	public boolean rejectJobApplication(String userID, int jobID)
	{
		
		try {
			String hql = "UPDATE JobApplication SET status = 'R' where userID ='"+userID+"' and jobID="+jobID;
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
	}
	catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
	}

}
