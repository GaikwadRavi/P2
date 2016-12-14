package com.niit.collaborationbackend.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="C_JOB_APPLIED")
@Component
public class JobApplication extends BaseDomain{
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="user_id")
	private String userID;
	
	@Column(name="job_id")
	private int jobID;

	@Column(name="date_applied")
	private Timestamp dateApplied;
	//private Date dateApplied;

	private String remarks;
	
	private char status; //N-Newly Applied, R-Rejected, C-call for Interview, S-selected
	
	public Timestamp getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Timestamp dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	

	/*public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}*/

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	
	
	

}
