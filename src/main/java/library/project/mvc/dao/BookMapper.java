package library.project.mvc.dao;

import library.project.mvc.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setBook_id(rs.getInt("book_id"));
//        book.setPerson_id(rs.getInt("person_id"));
        book.setName(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfProduction(rs.getInt("year_of_production"));

        return book;
    }
}
                
