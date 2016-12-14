package com.niit.collaboration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.JobDAO;
import com.niit.collaborationbackend.model.Job;

public class JobTest {

	public static void main(String[] args) throws ParseException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		JobDAO jobDAO = (JobDAO) context.getBean("jobDAO");
		Job job = (Job) context.getBean("job");
		
		final Calendar calendar = new GregorianCalendar();
		
		/*Date date = new Date();
		Date date1=new Date();
		String datestr;
		DateFormat dateFormat1 = new SimpleDateFormat("mm-dd-yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");

		date = dateFormat1.parse("01-01-2015");
		datestr = dateFormat1.format(date);
		date.setDate(03-11-2016);*/
		
		
		
		
		job.setId(1);
		//job.setDateTime();
		job.setQualification("B.E");
		job.setDescription("This job for Hibernate");
	
		
	}

}
