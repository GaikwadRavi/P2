package com.niit.collaborationbackend.dao;

import java.util.List;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;

public interface JobDAO {
	public boolean postJob(Job job); //save() method for Job table
	
	public boolean updateJob(Job job);
	
	public List<Job> getAllVacantJobs(); //getAllOpendjobs()
	
	public List<Job> getAllJobs();
	
	public boolean applyForJob(JobApplication jobApplication); //like save method for C_Job_Applied table
	
	public boolean updateJobApplication(JobApplication jobApplication);
	
	public JobApplication getJobApplication(String userID, int jobID);
	
	public boolean callForInterview(String userID,int jobID);
	public boolean rejectJobApplication(String userID,int jobID);
	
	public List<Job> getMyAppliedJobs(String userID);
	

}
