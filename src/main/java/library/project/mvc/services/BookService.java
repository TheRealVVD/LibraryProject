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

import java.util.Date;
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

    public List<Book> findAll(boolean sort) {
        if (sort) {
            return booksRepository.findAll(Sort.by("yearOfProduction"));
        }
        return booksRepository.findAll();
    }

    public List<Book> findAll(boolean sort, Integer page, Integer size) {
        if (sort) {
            return booksRepository.findAll(PageRequest.of(page, size, Sort.by("yearOfProduction"))).getContent();
        }
        return booksRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> findBooksByName(String bookName) {
        return booksRepository.findByNameStartingWith(bookName);
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return booksRepository.findByAuthorStartingWith(authorName);
    }

    @Transactional
    public void save(Book Book) {
        booksRepository.save(Book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        updatedBook.setBook_id(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());//чтобы не терялась связь при обновлении
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void addOwner(int book_id, Person person) {
        booksRepository.findById(book_id).ifPresent(
             book -> {
                 book.setOwner(person);
                 book.setTakenAt(new Date());
             }
        );
    }

    @Transactional
    public void setBookFree(int book_id) {
        booksRepository.findById(book_id).ifPresent(book -> {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }

}
