package mx.cuu.dev.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import mx.cuu.dev.demo.model.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {
    private static final Logger LOG = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save_Success() {
        Person person = new Person(1000, "Test", 10);
        Person row = personRepository.save(person);
        Assert.assertNotNull(row);
        assertThat(row).hasFieldOrPropertyWithValue("fullName", "Test");
        assertThat(row).hasFieldOrPropertyWithValue("age", 10);
        LOG.info("Save Row, OK: {}", person);
    }

    @Test
    public void findAll_Success() {
        Person personOne = new Person(101, "Person One", 12);
        personOne = entityManager.merge(personOne);
        Person personTwo = new Person(102, "Person Two", 15);
        personTwo = entityManager.merge(personTwo);

        List<Person> persons = personRepository.findAll();
        LOG.info("Persons found: {}", persons.size());
        assertThat(persons).contains(personOne, personTwo);
    }

    @Test
    public void update_Success() {
        Person person = new Person(10, "Person", 12);
        person = entityManager.merge(person);
        person.setFullName("Person Update");
        Person personUpdated = personRepository.save(person);
        assertThat(person).isEqualTo(personUpdated);
    }

}
