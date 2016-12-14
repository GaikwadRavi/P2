package com.niit.collaborationbackend.model;

import java.sql.Date;


//import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "C_BLOG")
@Component
public class Blog extends BaseDomain {
	@Id
	private Integer id;
	
	@Transient
	@Column(name = "date_time")
	private Date dateTime;
	
	@NotNull
	private char status;
	
	@NotNull
	private String reason;
	
	private String title;
	
	private String description;
	
	@Column(name = "user_id")
	private String userID;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	

}
