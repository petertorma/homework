package hu.codingmentor.tp_db;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("C")
@NamedQuery(name = "QueryNews" , query = "SELECT b FROM NewsPaper b WHERE b.lengthOfItem=hu.codingmentor.tp_db.LengthOfItems.SHORT")
public class NewsPaper extends Item implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public NewsPaper() {
        //default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

