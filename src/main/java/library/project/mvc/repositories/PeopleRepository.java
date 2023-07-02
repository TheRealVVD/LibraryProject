package library.project.mvc.repositories;

import library.project.mvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p where p.name = ?1 and p.surname = ?2 and p.patronymic = ?3")
    Optional<Person> findByFullName(String name, String surname, String patronymic);
}
