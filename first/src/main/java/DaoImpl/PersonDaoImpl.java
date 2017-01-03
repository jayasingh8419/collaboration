package DaoImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Dao.PersonDao;
import Model.Person;


@Repository
public class PersonDaoImpl implements PersonDao{
	@Autowired
private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<Person> getAllPersons() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Person");
		List<Person> persons=query.list();
		session.close();
		return persons;
	}


	public Person getPersonById(int id) {
		Session session=sessionFactory.openSession();
		//select * from personinfo where id=2
		Person person=(Person)session.get(Person.class, id);
		session.close();
		return person;
	}


	public void savePerson(Person person) {
		Session session=sessionFactory.openSession();
		session.save(person);
		session.flush();
		session.close();
	}

}


/*@Repository
public class PersonDaoImpl implements PersonDao{
	@Autowired
private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<Person> getAllPersons() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Person");
		List<Person> persons=query.list();
		session.close();
		return persons;
	}

}
*/