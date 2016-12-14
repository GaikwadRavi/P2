package com.niit.collaborationbackend.controller;

import java.sql.Timestamp;
import java.util.Date;
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

import com.niit.collaborationbackend.dao.JobDAO;
import com.niit.collaborationbackend.model.BaseDomain;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;

@RestController
public class JobController {
	@Autowired
	Job job;
	
	@Autowired //it is spring annotation to create the object of pojo class by spring
	BaseDomain baseDomain;
	
	@Autowired
	JobApplication jobApplication;
	
	@Autowired
	JobDAO jobDAO;
	
	Date date = new Date();
	long time = date.getTime();
	Timestamp timestamp = new Timestamp(time);
	
	@RequestMapping(value = "/getAllJobs", method = RequestMethod.GET)
	public ResponseEntity< List<Job>> getAllJobs()
	{
		System.out.println("All job");
		List<Job> allJobs = jobDAO.getAllJobs();
		
		if(allJobs==null)
		{
			System.out.println("All job null");
			Job job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("No Jobs Available");
			allJobs.add(job);
		}
		return new ResponseEntity<List<Job>>(allJobs, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/getAllVacantJobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllOpendJobs() {
		
		List<Job> jobs = jobDAO.getAllVacantJobs();
		System.out.println("I am in job list");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		
	}
	
	/*@RequestMapping(value = "/getMyAppliedJobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		
		List<Job> jobs = (List<Job>) jobDAO.getMyAppliedJobs(loggedInUserID);
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}*/
	@RequestMapping(value = "/postAJob/", method = RequestMethod.POST)
	public ResponseEntity<BaseDomain> postAJob(@RequestBody Job job)
	{
		
		job.setStatus('V');
		job.setDateTime(timestamp);
		if(jobDAO.postJob(job)==true)
		{
			baseDomain.setErrorCode("200");
			baseDomain.setErrorMessage("Successfuly posted the job");
		}
		else
		{
			baseDomain.setErrorCode("400");
			baseDomain.setErrorMessage("Not able posted the job");
		}
		return new ResponseEntity<BaseDomain>(baseDomain,HttpStatus.OK);
	}

	@RequestMapping(value = "/getMyAppliedJobs/", method = RequestMethod.GET)
	
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession)
	{
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		System.out.println(loggedInUserID);
		List<Job> jobs = (List<Job>)jobDAO.getMyAppliedJobs(loggedInUserID);
		System.out.println(jobs);
		System.out.println("hello i am");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/applayForJob/{jobID}", method = RequestMethod.POST)
	public ResponseEntity<Job> applayForJob(@PathVariable("jobID") int jobID, HttpSession httpSession)
	{
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		//jobApplication.setId(2);
		jobApplication.setJobID(jobID);
		jobApplication.setUserID(loggedInUserID);
		jobApplication.setStatus('N');
		jobApplication.setDateApplied(timestamp);
		jobApplication.setRemarks("Applied");
		if(jobDAO.applyForJob(jobApplication)!=true)
		{
			job.setErrorCode("404");
			job.setErrorMessage("Not able to applay for the job");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	@RequestMapping(value = "/canCallForInterview/{userID}/{jobID}", method = RequestMethod.PUT)
	public ResponseEntity<Job> callForInterview(@PathVariable("userID") String userID,@PathVariable("jobID") int jobID)
	{
		//jobApplication.setStatus('C');
		if(jobDAO.callForInterview(userID,jobID)!=true)
		{
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change the job application status 'call for interview'");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rejectJobApplication/{userID}/{jobID}", method = RequestMethod.PUT)
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userID") String userID,@PathVariable("jobID") int jobID)
	{
		jobApplication.setStatus('R');
		if(jobDAO.rejectJobApplication(userID, jobID)!=true)
		{
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change the job application status 'call for interview'");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
}
