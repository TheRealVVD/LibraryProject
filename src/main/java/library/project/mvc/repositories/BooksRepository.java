package library.project.mvc.repositories;

import library.project.mvc.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Page<Book> findAll(Pageable var1);
    List<Book> findAll(Sort var2);

    List<Book> findByNameStartingWith(String bookName);
    List<Book> findByAuthorStartingWith(String authorName);
}
