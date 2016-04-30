package tp.jpnpark.services;

import javax.ejb.Stateless;
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
public class MachineService {

    private EntityFacade entityManager;

    public MachineService() {
    }

    public Machine create(Machine machine) {
        return entityManager.create(machine);
    }

    public Machine find(long machineId) {
        return entityManager.find(Machine.class, machineId);
    }

    public Machine update(long machineId) {
        checkMachine(machineId);
        Machine machine = entityManager.find(Machine.class, machineId);
        deleteVisitorsFromMachine(machine);
        for (Park park : entityManager.findAll(Park.class)) {
            if (machine.getPark().getId().equals(park.getId())) {
                park.getMachines().remove(machine);
                park.getMachines().add(machine);
                entityManager.update(park);
            }
        }
        return entityManager.update(machine);
    }

    public void delete(long machineId) {
        checkMachine(machineId);
        Machine machine = entityManager.find(Machine.class, machineId);
        deleteVisitorsFromMachine(machine);

        for (Park park : entityManager.findAll(Park.class)) {
            if (machine.getPark().getId().equals(park.getId())) {
                park.getMachines().remove(machine);
                entityManager.update(park);
            }
        }
        entityManager.delete(machine);
    }

    public void deleteVisitorFromMachine(long machineId, long visitorId) {
        if (entityManager.find(Machine.class, machineId) == null) {
            throw new RuntimeException("There is no machine with this id."); // TODO create own exception
        }
        if (entityManager.find(Visitor.class, visitorId) == null) {
            throw new RuntimeException("There is no visitor with this id.");
        }
        Visitor visitor = entityManager.find(Visitor.class, visitorId);
        Machine machine = entityManager.find(Machine.class, machineId);

        if (visitor.getMachine().getId().equals(machine.getId())) { // ha van idő több exception készítése a pontos hiba meghatározáshoz
            visitor.setIsActive(false);
            visitor.setState(StateOfTheVisitor.REST);
            visitor.setMachine(null);
            entityManager.update(visitor);
        }
    }

    public void deleteVisitorsFromMachine(Machine machine) {
        checkMachine(machine.getId());
        for (Visitor visitor : entityManager.findAll(Visitor.class)) {
            if (visitor.getMachine().getId().equals(machine.getId())) {
                visitor.setMachine(null);
                visitor.setState(StateOfTheVisitor.REST);
                entityManager.update(visitor);
            }
        }
    }

    public boolean checkMachine(long machineId) {
        if (entityManager.find(Park.class, machineId) != null) {
            return true;
        }
        throw new RuntimeException("there is no machine with this ID");
    }

}
