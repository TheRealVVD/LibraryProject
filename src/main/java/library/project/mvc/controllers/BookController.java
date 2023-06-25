package library.project.mvc.controllers;

import library.project.mvc.dao.BookDAO;
import library.project.mvc.dao.PersonDAO;
import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDAO.showAll());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("people", personDAO.showAll());
        model.addAttribute("ownerPerson", bookDAO.showOwner(id));
        return "books/showBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                               @ModelAttribute("book") @Valid Book updatedBook,
                               BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.updateBook(id, updatedBook);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/add")
    public String bookOwner(@PathVariable("id") int book_id,
                            @ModelAttribute("person") Person person) {
        bookDAO.addOwner(book_id, person.getId());
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/get_free")
    public String getBookFree(@PathVariable("id") int book_id,
                              @ModelAttribute("person") Person person) {
        bookDAO.getBookFree(book_id);
        return "redirect:/books/{id}";
    }

}
