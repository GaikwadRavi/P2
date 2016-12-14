package com.niit.collaboration;

//import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.EventDAO;
import com.niit.collaborationbackend.model.Event;

public class EventTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		Event event = (Event)context.getBean("event");
		EventDAO eventDAO = (EventDAO)context.getBean("eventDAO");
		
		Date date = null;
		
		try 
		{
			event.setContent("Get-to-Gether");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			date =  dateFormat.parse("31/12/2016");
			
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		long time = date.getTime();
		
		event.setEventAt(new Timestamp(time));
		date=new Date();
		long time1 = date.getTime();
		Timestamp timestamp = new Timestamp(time1);
		event.setPostedAt(timestamp);
		event.setPlace("Pune");
		eventDAO.saveOrUpdateEvent(event);
		

	}

}
