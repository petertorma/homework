package tp.jpnpark.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import tp.jpnpark.enums.TypeOfMachines;

/**
 *
 * @author Torma Péter
 */
@Entity
public class Machine implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private long sizeOfMachine;

    private int ticketPrice;

    @NotNull
    private int capacity;

    @Enumerated(EnumType.STRING)
    private TypeOfMachines machineType;

    private int ageLimit;

    public Machine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
