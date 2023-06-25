package library.project.mvc.dao;

import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO (SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> showAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional
    public Person showPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void updatePerson(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToUpdate = session.get(Person.class, id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setSurname(updatedPerson.getSurname());
        personToUpdate.setPatronymic(updatedPerson.getPatronymic());
        personToUpdate.setYearOfBirthday(updatedPerson.getYearOfBirthday());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }

}
