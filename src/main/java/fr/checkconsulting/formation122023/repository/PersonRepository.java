package fr.checkconsulting.formation122023.repository;

import fr.checkconsulting.formation122023.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {


    @Query("select p " +
            "from Person p " +
            "where (p.firstName = :firstName or :firstName is null ) and (p.lastName = :lastName or :lastName is null) " +
            "and (p.old >= :minAge or :minAge is null  ) and (p.old <= :maxAge or :maxAge is null )")
    List<Person> searchPersonByCriteria(String firstName, String lastName, String minAge, String maxAge);
}
