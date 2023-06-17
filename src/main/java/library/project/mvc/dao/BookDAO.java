package library.project.mvc.dao;

import library.project.mvc.models.Book;
import library.project.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Book> showAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (book_name, author, year_of_production) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYearOfProduction());
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET book_name=?, author=?, year_of_production=? WHERE book_id=?",
                book.getName(), book.getAuthor(), book.getYearOfProduction(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
