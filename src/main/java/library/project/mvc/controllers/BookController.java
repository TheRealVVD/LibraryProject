package library.project.mvc.controllers;

import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import library.project.mvc.services.BookService;
import library.project.mvc.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final PersonService personService;

    private final BookService bookService;

    @Autowired
    public BookController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("people", personService.findAll());
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
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("book", bookService.findOne(id));
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
        bookService.update(id, updatedBook);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/add")
    public String bookOwner(@PathVariable("id") int book_id,
                            @ModelAttribute("person") Person person) {
        bookService.addOwner(book_id, person);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/get_free")
    public String getBookFree(@PathVariable("id") int book_id,
                              @ModelAttribute("person") Person person) {
        bookService.getBookFree(book_id);
        return "redirect:/books/{id}";
    }

}
