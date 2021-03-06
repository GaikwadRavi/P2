package com.niit.collaborationbackend.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.niit.collaborationbackend.utility.IdGenerator;


@Entity
@Component
public class Event {
	@Id
	private String eventId;
	private String content;
	private String place;
	private Timestamp eventAt;
	private Timestamp postedAt;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Timestamp getEventAt() {
		return eventAt;
	}
	public void setEventAt(Timestamp eventAt) {
		this.eventAt = eventAt;
	}
	public Timestamp getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(Timestamp postedAt) {
		this.postedAt = postedAt;
	}
	
	public Event(){
	
	
		this.eventId = IdGenerator.generateId("EVN");
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", content=" + content + ", place=" + place + ", eventAt=" + eventAt
				+ ", postedAt=" + postedAt + "]";
	}
	

}
