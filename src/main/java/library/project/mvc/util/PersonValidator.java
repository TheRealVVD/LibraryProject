package library.project.mvc.util;

import library.project.mvc.dao.PersonDAO;
import library.project.mvc.models.Person;
import library.project.mvc.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

//        if (personService.findByFullName(person.getName(), person.getSurname(), person.getPatronymic()).isPresent()) {
//            errors.rejectValue("name" + " surname " + "patronymic", "", "Человек с таким ФИО уже существует");
//        }
    }
}
