package library.project.mvc.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @NotEmpty(message = "Поле названия книги не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле названия книги должно быть длинной больше 1 и меньше 51 символов")
    @Column(name = "book_name")
    private String name;

    @NotEmpty(message = "Поле имени автора не должно быть пустым")
    @Size(min = 2, max = 50, message = "Поле имени автора должно быть длинной больше 1 и меньше 51 символов")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Год должен быть больше 1500")
    @Column(name = "year_of_production")
    private int yearOfProduction;

    @Column(name = "takenAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    public Book() {
    }

    public Book(String name, String author, int yearOfProduction) {
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
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

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
