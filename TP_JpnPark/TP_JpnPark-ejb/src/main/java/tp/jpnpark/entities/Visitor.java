package tp.jpnpark.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import tp.jpnpark.enums.StateOfTheVisitor;

/**
 *
 * @author Torma Péter
 */
@Entity
/*@NamedQueries({
@NamedQuery(name = "visitorsOnMachine",
query = "SELECT vis FROM Visitor vis WHERE vis.machine.machineId = :machineId"),
@NamedQuery(name = "tiredVisitors",
query = "SELECT COUNT(vis) FROM Visitor vis WHERE vis.amusementPark.parkId = :parkId AND v.state LIKE 'REST'"),
@NamedQuery(name = "actionVisitors",
query = "SELECT COUNT(vis) FROM Visitor vis WHERE vis.amusementPark.parkId = :parkId AND v.state LIKE 'ON_MACHINE'")
})*/
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private StateOfTheVisitor state;

    @ManyToOne
    private Park park;

    @ManyToOne
    private Machine machine;

    public Visitor() {
        //visitor
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public StateOfTheVisitor getState() {
        return state;
    }

    public void setState(StateOfTheVisitor state) {
        this.state = state;
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
