package tp.jpnpark.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Torma Péter
 */
@Entity
@Table(name = "amusement_park")
public class Park implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToOne
    @NotNull
    private Address adress;

    @NotNull
    private long money;

    @NotNull
    private long area;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "machinesInPark",
            joinColumns = @JoinColumn(name = "parkfk"),
            inverseJoinColumns = @JoinColumn(name = "machine"
                    + "fk"))
    List<Machine> machines = new ArrayList<>();

    private int ticketPrice;

    public Park() {
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
