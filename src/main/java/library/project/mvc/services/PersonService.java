package library.project.mvc.services;

import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import library.project.mvc.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book -> {
                long diffInMillies = new Date().getTime() - book.getTakenAt().getTime();
                //проверка на просрочку более 10 дней (864000000)
                if (diffInMillies > 864000000) {
                    book.setExpired(true);
                }
            });
            return person.get().getBooks();
        }
        return Collections.emptyList();
    }

    public Optional<Person> findByFullName(String name, String surname, String patronymic) {
        return peopleRepository.findByFullName(name, surname, patronymic);
    }
}
