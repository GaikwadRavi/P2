
package com.niit.collaborationbackend.model;

import java.sql.Date;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="C_JOB")
@Component
public class Job extends BaseDomain {
	@Id
	//@GeneratedValue
	private int id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	
	@NotNull
	private String qualification;
	
	@NotNull
	private char status;  //Status V-vacant, F-Filled, P-Pending
	
	@Column(name="date_time")
	@NotNull
	private Timestamp DateTime;
	
	public Timestamp getDateTime() {
		return DateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		DateTime = dateTime;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		if(status==null)
		{
			status=new Character('V');
		}
		this.status = status;
	}
	
	

}
