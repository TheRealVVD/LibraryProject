package library.project.mvc.dao;

import library.project.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Person> showAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person showPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, surname, patronymic, year) VALUES (?, ?, ?, ?)",
                person.getName(), person.getSurname(), person.getPatronymic(), person.getYearOfBirthday());
    }

    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, patronymic=?, year=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getSurname(), updatedPerson.getPatronymic(), updatedPerson.getYearOfBirthday(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
