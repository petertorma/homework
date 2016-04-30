package tp.jpnpark.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import tp.jpnpark.entities.Address;
import tp.jpnpark.entities.GuestBook;
import tp.jpnpark.entities.Machine;
import tp.jpnpark.entities.Park;
import tp.jpnpark.entities.Visitor;
import tp.jpnpark.enums.StateOfTheVisitor;
import tp.jpnpark.facade.EntityFacade;

/**
 *
 * @author Torma Péter
 */
@Stateless
public class ParkService {

    @Inject
    private EntityFacade entityManager;

    @Inject
    private MachineService machinService;

    @Inject
    private VisitorService visitorService;

    public Park create(Park park, long addressId) {
        park.setAdress(entityManager.find(Address.class, addressId));
        return entityManager.create(park);
    }

    public Park find(long parkId) {
        return entityManager.find(Park.class, parkId);
    }

    public Park update(Park park, long parkId) {
        Park tempPark = entityManager.find(Park.class, parkId); // eddigi adatai az adott parknak

        park.setAdress(tempPark.getAdress());
        park.setId(tempPark.getId());
        park.setMachines(tempPark.getMachines());
        park.setArea(tempPark.getArea() + park.getArea()); // csak bővíteni lehet, csükkenteni nem lehet egy már létező park méretét. 

        for (Visitor visitor : entityManager.findAll(Visitor.class)) {// átállítani minden visitor nál a parkot
            if (visitor.getPark().getId().equals(park.getId())) {
                visitor.setPark(park);
                entityManager.update(visitor);
            }
        }

        for (Machine machine : entityManager.findAll(Machine.class)) {// átállítani minden machine nél a parkot
            if (machine.getPark().getId().equals(park.getId())) {
                machine.setPark(park);
                entityManager.update(machine);
            }
        }
        for (GuestBook gb : entityManager.findAll(GuestBook.class)) {// átállítani minden guestBook nál a parkot
            if (gb.getPark().getId().equals(park.getId())) {
                gb.setPark(park);
                entityManager.update(gb);
            }
        }
        return entityManager.update(park);
    }

    public void Delete(long parkId) {
        deleteMachinesFromPark(parkId);
        deleteVisitorsFromPark(parkId);
        deleteGuestBookOfThePark(parkId);
        entityManager.delete(parkId);
    }

    public void deleteMachinesFromPark(long parkId) {
        Park tempPark = entityManager.find(Park.class, parkId);
        List<Machine> machines = tempPark.getMachines();

        for (Machine machine : machines) {
            machinService.deleteVisitorsFromMachine(machine);
            machine.setPark(null);
            entityManager.update(machine);
        }
        tempPark.getMachines().clear();
        entityManager.update(tempPark);
    }

    public void deleteMachineFromPark(long parkId, long machineId) {
        Park tempPark = entityManager.find(Park.class, parkId);
        Machine machine = entityManager.find(Machine.class, machineId);
        machinService.deleteVisitorsFromMachine(machine);
        machine.setPark(null);
        entityManager.update(machine);
        entityManager.update(tempPark);
    }

    public void deleteVisitorsFromPark(long parkId) {
        Park park = entityManager.find(Park.class, parkId);
        for (Visitor visitor : entityManager.findAll(Visitor.class)) {
            if (visitor.getPark().getId().equals(park.getId())) {
                visitor.setPark(null);
                visitor.setIsActive(false);
                entityManager.update(visitor);
            }
        }
    }

    public void deleteGuestBookOfThePark(long parkId) {
        Park park = entityManager.find(Park.class, parkId);
        for (GuestBook gb : entityManager.findAll(GuestBook.class)) {
            if (gb.getPark().getId().equals(park.getId())) {
                entityManager.delete(gb);
            }
        }
    }

    public boolean checkPark(long parkId) {
        if (entityManager.find(Park.class, parkId) != null) {
            return true;
        }
        throw new RuntimeException("there is no park with this ID");
    }

    public void addMachine(long parkId, long machineId) {
        machinService.checkMachine(machineId);
        checkPark(parkId);

        Park park = entityManager.find(Park.class, parkId);
        Machine machine = entityManager.find(Machine.class, machineId);

        if (park.getMoney() < machine.getPrice()) {
            throw new RuntimeException("the park has not enough money to byu this machine");
        }

        if (park.getArea() < machine.getSizeOfMachine()) {
            throw new RuntimeException("there is not enough space to get this machine");
        }

        park.getMachines().add(machine);
        park.setMoney(park.getMoney() - machine.getPrice());
        entityManager.update(park);
    }

    public void addVisitor(long visitorId, long parkId) {
        visitorService.checkVisitor(visitorId);
        checkPark(parkId);

        Park park = entityManager.find(Park.class, parkId);
        Visitor visitor = entityManager.find(Visitor.class, visitorId);

        if (visitor.getMoney() < park.getTicketPrice()) {
            throw new RuntimeException("the visitor has no money to buy a ticket");
        }

        visitor.setIsActive(true);
        visitor.setPark(park);
        visitor.setMoney(visitor.getMoney() - park.getTicketPrice());
        visitor.setEntryDate(Calendar.getInstance().getTime());
        entityManager.update(visitor);

    }

    public void exitVisitor(long visitorId, long parkId) {
        visitorService.checkVisitor(visitorId);
        checkPark(parkId);

        Park park = entityManager.find(Park.class, parkId);
        Visitor visitor = entityManager.find(Visitor.class, visitorId);

        if (!visitor.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("the visitor is not in this park");
        }

        visitor.setIsActive(false);
        visitor.setPark(null);
        entityManager.update(visitor);
    }

    public void enterVisitorOnMachine(long parkId, long machineId, long visitorId) {
        visitorService.checkVisitor(visitorId);
        checkPark(parkId);
        machinService.checkMachine(machineId);

        Park park = entityManager.find(Park.class, parkId);
        Visitor visitor = entityManager.find(Visitor.class, visitorId);
        Machine machine = entityManager.find(Machine.class, machineId);

        if (!visitor.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("the visitor is not in this park");
        }
        if (!machine.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("the machine is not in this park");
        }
        if (visitor.getState().equals(StateOfTheVisitor.ON_MACHINE)) {
            throw new RuntimeException("the visitor is already on a machine");
        }
        if (machine.getCapacity() == machine.getVisitorsOnMachine()) {
            throw new RuntimeException("there is no free spaces on the machine for the visitor to get in");
        }

        machine.setVisitorsOnMachine(machine.getVisitorsOnMachine() + 1);
        entityManager.update(machine);
        visitor.setMachine(machine);
        visitor.setMoney(visitor.getMoney() - machine.getTicketPrice());
        visitor.setState(StateOfTheVisitor.ON_MACHINE);
        entityManager.update(visitor);
    }

    public void exitvisitorFromMachine(long parkId, long machineId, long visitorId) {
        visitorService.checkVisitor(visitorId);
        checkPark(parkId);
        machinService.checkMachine(machineId);

        Park park = entityManager.find(Park.class, parkId);
        Visitor visitor = entityManager.find(Visitor.class, visitorId);
        Machine machine = entityManager.find(Machine.class, machineId);

        if (!visitor.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("the visitor is not in this park");
        }
        if (!machine.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("the machine is not in this park");
        }
        if (visitor.getState().equals(StateOfTheVisitor.REST)) {
            throw new RuntimeException("the visitor is not on a machine");
        }
        if (!visitor.getMachine().getId().equals(machine.getId())) {
            throw new RuntimeException("the visitor is not on this machine");
        }

        visitor.setState(StateOfTheVisitor.REST);
        visitor.setMachine(null);
        entityManager.update(visitor);
        machine.setVisitorsOnMachine(machine.getVisitorsOnMachine() - 1);
        entityManager.update(machine);
    }

    public void LogIntoGuestBook(long parkId, long visitorId, long guestBookId, String mesage) {
        visitorService.checkVisitor(visitorId);
        checkPark(parkId);

        //check if guestbook is exists
        if (entityManager.find(GuestBook.class, guestBookId) == null) {
            throw new RuntimeException("there is no guestbook with this id");
        }

        GuestBook gb = entityManager.find(GuestBook.class, guestBookId);
        Park park = entityManager.find(Park.class, parkId);
        Visitor visitor = entityManager.find(Visitor.class, visitorId);

        if (!visitor.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("the visitor is not in this park");
        }

        if (!gb.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("this guestbook is not belong to this park");
        }

        gb.getLogs().put(LocalDate.now(), mesage);
        entityManager.update(gb);

    }

    public String getGuestBookLogs(long parkId, long gbId) {
        if (entityManager.find(GuestBook.class, gbId) == null) {
            throw new RuntimeException("there is no guestbook with this id");
        }
        checkPark(parkId);

        GuestBook gb = entityManager.find(GuestBook.class, gbId);
        Park park = entityManager.find(Park.class, parkId);

        if (!gb.getPark().getId().equals(park.getId())) {
            throw new RuntimeException("this guestbook is not belong to this park");
        }

        return gb.getLogs().toString();
    }
}
