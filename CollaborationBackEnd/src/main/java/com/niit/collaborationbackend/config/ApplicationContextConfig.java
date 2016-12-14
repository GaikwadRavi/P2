package com.niit.collaborationbackend.config;

import java.util.Properties;



import javax.sql.DataSource;

import org.hibernate.SessionFactory;
//import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.collaborationbackend.dao.BlogDAO1;
import com.niit.collaborationbackend.dao.BlogDAOImpl1;
import com.niit.collaborationbackend.model.Blog1;
import com.niit.collaborationbackend.model.BlogComment;
import com.niit.collaborationbackend.model.Event;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;
import com.niit.collaborationbackend.model.User;

//import antlr.debug.Event;


//@EnableWebMvc
//@ComponentScan(basePackages = { "com" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Configuration
@ComponentScan("com")
@EnableTransactionManagement

public class ApplicationContextConfig {
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/MyCollaboration");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		
		System.out.println("I am in geDataSource() function");
		
		return dataSource;
		
	}
	
	
	private Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		System.out.println("I am in geHibernatePor() function");
		return properties;
		
	}
	@Autowired
	@Bean(name="sessionfactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(Blog1.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		
		System.out.println("I am in getSessionFactory() function");
		return sessionBuilder.buildSessionFactory();
	}
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("I am in getTrasactopnManager() function");
		return transactionManager; 
		
	}
	/*@Autowired
	@Bean(name="blogDAO1")
	public BlogDAO1 getBlogDAO1(SessionFactory sessionFactory) {
		return (BlogDAO1) new BlogDAOImpl1(sessionFactory);
	}
	*/


}
