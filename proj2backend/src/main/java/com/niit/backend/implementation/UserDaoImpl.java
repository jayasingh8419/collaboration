package com.niit.backend.implementation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.dao.UserDao;
import com.niit.backend.model.User;
@Repository
public class UserDaoImpl implements UserDao{
Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public User authenticate(User user) {
		System.out.println("h3");
		logger.debug("USERDAOIMPL :: AUTHENTICATE");
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from User where username=?  and password=?");
		//select * from User where username='smith' and password='123'
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		User validUser=(User)query.uniqueResult();
		session.flush();
		session.close();
		if(validUser!=null)
		logger.debug("VALID USER IS  " + validUser.getUsername() + " " + validUser.getRole() + " " + validUser.isOnline());;
		if(validUser==null)
			logger.debug("Valid USER is null");
		return validUser;
		
	}
	@Override
	public void updateUser(User user) {
		System.out.println("h4");
		logger.debug("USERDAOIMPL::UPDATE");
		logger.debug("ISONLINE VALUE IS [BEFORE UPDATE]" + user.isOnline());
		Session session=sessionFactory.openSession();
		User existingUser=(User)session.get(User.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		session.flush();
		session.close();
		logger.debug("ISONLINE VALUE IS [AFTER UPDATE] " + existingUser.isOnline());
	}
	@Override
	public User registerUser(User user) {
		System.out.println("h5");
		logger.debug("USERDAOIMPL - registerUser");
		Session session=sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.close();
		logger.debug("User id in Dao " + user.getId());
		return user;
		
			}

}









/*package com.niit.backend.implementation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.dao.UserDao;
import com.niit.backend.model.User;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public User authenticate(User user) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(
		"from User where username=?  and password=?");
		//select * from User where username='smith' and password='123'
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		User validUser=(User)query.uniqueResult();
		session.close();
		return validUser;
		
	}
	@Override
	public void updateUser(User user) {
		Session session=sessionFactory.openSession();
		User existingUser=(User)session.get(User.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		session.flush();
		session.close();
	}

}*/