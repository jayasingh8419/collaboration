package Dao;

import java.util.List;

import Model.Person;


/*public interface PersonDao {
List<Person> getAllPersons();*/

public interface PersonDao {
List<Person> getAllPersons();
Person getPersonById(int id);
void savePerson(Person person);
}



