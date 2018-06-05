package mx.cuu.dev.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.cuu.dev.demo.model.Person;
import mx.cuu.dev.demo.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Return all persons registered.
     *
     * @return
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Find person by identifier.
     *
     * @param id
     *            Identifier value.
     * @return Person information.
     */
    public Person findPersonById(int id) {
        return personRepository.getOne(id);
    }

    /**
     * Save person information.
     *
     * @param person
     *            Person information.
     * @return Row recorded.
     */
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
    
    /**
     * Delete person by identifier.
     * @param id Person identifier.
     */
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }
}
