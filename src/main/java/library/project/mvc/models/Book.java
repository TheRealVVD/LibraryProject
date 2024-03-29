package library.project.mvc.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    private int person_id;
    @NotEmpty(message = "Поле названия книги не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле названия книги должно быть длинной больше 1 и меньше 51 символов")
    private String name;
    @NotEmpty(message = "Поле имени автора не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле имени автора должно быть длинной больше 1 и меньше 51 символов")
    private String author;

    private int yearOfProduction;

    public Book() {
    }

    public Book(int book_id, int person_id, String name, String author, int yearOfProduction) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.name = name;
        this.author = author;
        this.yearOfProduction = yearOfProduction;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}
