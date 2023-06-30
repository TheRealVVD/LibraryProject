package library.project.mvc.services;

import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import library.project.mvc.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAll(String sort, int page, int size) {
        return booksRepository.findAll(PageRequest.of(page, size, Sort.by("yearOfProduction"))).getContent();
    }

    public List<Book> findAll(int page, int size) {
        return booksRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Book> findAll(String sort) {
        return booksRepository.findAll(Sort.by("yearOfProduction"));
    }

    public Book findOne(int id) {
        Optional<Book> Book = booksRepository.findById(id);
        return Book.orElse(null);
    }

    @Transactional
    public void save(Book Book) {
        booksRepository.save(Book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBook_id(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void addOwner(int book_id, Person person) {
        booksRepository.findById(book_id).orElse(null).setOwner(person);
    }

    @Transactional
    public void getBookFree(int book_id) {
        booksRepository.findById(book_id).orElse(null).setOwner(null);
    }
}
