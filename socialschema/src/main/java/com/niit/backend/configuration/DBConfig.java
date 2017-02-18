package com.niit.backend.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.backend.model.BlogComment;
import com.niit.backend.model.BlogPost;
import com.niit.backend.model.Friend;
import com.niit.backend.model.Job;
import com.niit.backend.model.Message;
import com.niit.backend.model.OutputMessage;
import com.niit.backend.model.UploadFile;
import com.niit.backend.model.User;

@Configuration
@EnableTransactionManagement
public class DBConfig {
	//create an instance
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf=
				new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty(
				"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		Class classes[]={User.class,Job.class,Friend.class,UploadFile.class,BlogPost.class,BlogComment.class};
		return lsf.addAnnotatedClasses(classes)
				/*.addAnnotatedClass(Job.class)
				.addAnnotatedClass(UploadFile.class)*/
		   .buildSessionFactory();
	}
	
	
	
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("PROJECT");
	    dataSource.setPassword("project");
	    return dataSource;
	}
	
	
	@Bean
	public HibernateTransactionManager hibTransManagement(){
		return new HibernateTransactionManager(sessionFactory());
	}


}


