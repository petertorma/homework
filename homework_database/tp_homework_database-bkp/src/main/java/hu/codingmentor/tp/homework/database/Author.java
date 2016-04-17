package hu.codingmentor.tp.homework.database;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "QueryForFemaleAuthor", query = "SELECT b FROM Author b WHERE b.gender=hu.codingmentor.tp_homework_database.Gender.FEMALE"),
    @NamedQuery(name = "QueryForMaleAuthor", query = "SELECT b FROM Author b WHERE b.gender=hu.codingmentor.tp_homework_database.Gender.MALE"),
    @NamedQuery(name = "firtnameQuery", query = "SELECT b.name FROM Author b")

})
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Book> books;

    public Author() {
        //default
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", books=" + books + '}';
    }
}
