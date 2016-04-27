package tp.jpnpark.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import tp.jpnpark.enums.StateOfTheVisitor;

/**
 *
 * @author Torma Péter
 */
@Entity
public class Visitor implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int money;

    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @NotNull
    private int age;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private StateOfTheVisitor state;
    
    public Visitor() {
        //visitor
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
