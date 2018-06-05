package mx.cuu.dev.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import mx.cuu.dev.demo.model.Person;
import mx.cuu.dev.demo.repository.PersonRepository;
import mx.cuu.dev.demo.service.PersonService;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(PersonServiceTest.class);

    @TestConfiguration
    static class PersonServiceTestContextConfiguration {
        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void savePerson_Success() {
        Person person = new Person(1, "Julio Cesar", 32);
        Mockito.when(personRepository.save(person)).thenReturn(person);        
        Person rowSaved = personService.savePerson(person);
        assertThat(rowSaved.getFullName().equals(person.getFullName()));
        LOG.info("Person saved: {}", rowSaved);
    }
    
    @Test
    public void getAllPersons_Success() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Hugo", 15));
        persons.add(new Person(2, "Paco", 12));
        persons.add(new Person(3, "Luis", 10));
        Mockito.when(personRepository.findAll()).thenReturn(persons);
        List<Person> rows = personService.getAllPersons();
        assertThat(rows).isNotEmpty();
        LOG.info("Persons found: {}", rows.size());
    }
    
    @Test
    public void deletePersonById_Success() {
        doNothing().when(personRepository).deleteById(10);
        personService.deletePersonById(10);
    }
    
    @Test
    public void findPersonById_Success() {
        Person personFounded = new Person(1000, "Person", 27);
        Mockito.when(personRepository.getOne(1000)).thenReturn(personFounded);
        Person row = personService.findPersonById(1000);
        assertThat(row).hasFieldOrPropertyWithValue("fullName", "Person");
        assertThat(row).hasFieldOrPropertyWithValue("id", 1000);
        LOG.info("Row found: {}", row);
    }
}

