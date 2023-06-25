package library.project.mvc.models;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле имени не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле имени должно быть длинной больше 1 и меньше 51 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Поле фамилии не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле фамилии должно быть длинной больше 1 и меньше 51 символов")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Поле отчества не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле отчества должно быть длинной больше 1 и меньше 51 символов")
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "year")
    private int yearOfBirthday;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String name, String surname, String patronymic, int yearOfBirthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.yearOfBirthday = yearOfBirthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(book);
        book.setOwner(this);
    }

    public boolean hasABook() {
        return this.books.size() > 0;
    }





    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }
}
