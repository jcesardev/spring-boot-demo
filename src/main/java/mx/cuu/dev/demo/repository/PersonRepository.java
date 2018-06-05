package mx.cuu.dev.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.cuu.dev.demo.model.Person;

/**
 * DAO for {@link Person} model.
 *
 * @author Julio Cesar Bolaños Palacios.
 *
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
