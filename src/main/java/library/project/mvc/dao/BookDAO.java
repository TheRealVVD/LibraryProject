package library.project.mvc.dao;

import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO (SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> showAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional
    public Book showBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book bookToUpdate = session.get(Book.class, id);
        bookToUpdate.setName(updatedBook.getName());
        bookToUpdate.setAuthor(updatedBook.getAuthor());
        bookToUpdate.setYearOfProduction(updatedBook.getYearOfProduction());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
        book.setOwner(null);
    }

    @Transactional
    public void addOwner(int book_id,int person_id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, book_id);
        Person person = session.get(Person.class, person_id);
        book.setOwner(session.get(Person.class, person_id));
        session.get(Person.class, person_id).getBooks().add(book);
    }

    @Transactional
    public Person showOwner(int book_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, book_id).getOwner();
    }

    @Transactional
    public void getBookFree(int book_id) {
        Session session = sessionFactory.getCurrentSession();
        session.get(Book.class, book_id).setOwner(null);
    }
}
