package tp.jpnpark.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import tp.jpnpark.enums.TypeOfMachines;

/**
 *
 * @author Torma Péter
 */
@Entity
@NamedQuery(name = "machinesInPark",
    query = "SELECT m FROM Machine m WHERE m.parkID = :parkId")
public class Machine implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "size cannot be null")
    private long sizeOfMachine;

    @NotNull(message = "price cannot be null")
    private int price;

    @NotNull(message = "ticket price cannot be null")
    private int ticketPrice;

    @NotNull(message = "capacity vannot be null")
    private int capacity;

    private Long parkID;
    
    private int visitorsOnMachine;

    @Enumerated(EnumType.STRING)
    private TypeOfMachines machineType;

    private int ageLimit;

    public Machine() {
        //default
    }

    public int getVisitorsOnMachine() {
        return visitorsOnMachine;
    }

    public void setVisitorsOnMachine(int visitorsOnMachine) {
        this.visitorsOnMachine = visitorsOnMachine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParkID() {
        return parkID;
    }

    public void setParkID(Long parkID) {
        this.parkID = parkID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getSizeOfMachine() {
        return sizeOfMachine;
    }

    public void setSizeOfMachine(long sizeOfMachine) {
        this.sizeOfMachine = sizeOfMachine;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TypeOfMachines getMachineType() {
        return machineType;
    }

    public void setMachineType(TypeOfMachines machineType) {
        this.machineType = machineType;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
