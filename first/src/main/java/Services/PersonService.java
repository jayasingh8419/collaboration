package Services;


import java.util.List;

import Model.Person;


public interface PersonService {
List<Person> getAllPersons(); 
Person getPersonById(int id);
void savePerson(Person person);
}

/*public interface PersonService {
List<Person> getAllPersons(); 
}
*/