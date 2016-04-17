package hu.codingmentor.tp_db;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("A")
@NamedQuery(name = "QueryBook", query = "SELECT b FROM Book b WHERE b.lengthOfItem=hu.codingmentor.tp_db.LengthOfItems.LONG")
public class Book extends Item implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Book() {
        //default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
