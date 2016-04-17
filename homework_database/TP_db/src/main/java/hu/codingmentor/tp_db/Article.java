package hu.codingmentor.tp_db;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("B")
@NamedQuery(name = "QueryArticle", query = "SELECT b FROM Article b WHERE b.lengthOfItem=hu.codingmentor.tp_db.LengthOfItems.SHORT")
public class Article extends Item implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Article() {
        //default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
