package library.project.mvc.controllers;

import library.project.mvc.dao.BookDAO;
import library.project.mvc.dao.PersonDAO;
import library.project.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String showAllPeople(Model model) {
        model.addAttribute("people", personDAO.showAll());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/showPerson";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/edit";

    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id,
                               @ModelAttribute("person") @Valid Person updatedPerson,
                               BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.updatePerson(id, updatedPerson);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
