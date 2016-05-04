package tp.jpnpark.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import tp.jpnpark.entities.GuestBook;
import tp.jpnpark.entities.Visitor;
import tp.jpnpark.enums.StateOfTheVisitor;
import tp.jpnpark.exceptions.InvalidValues;
import tp.jpnpark.facade.EntityFacade;

/**
 *
 * @author Torma Péter
 */
@Stateless
public class VisitorService  {

    @Inject
    private EntityFacade entityManager;

    public Visitor create(Visitor visitor) {
        visitor.setIsActive(false);
        visitor.setState(StateOfTheVisitor.REST);
        return entityManager.create(visitor);
    }

    public Visitor find(long visitorId) {
        return entityManager.find(Visitor.class, visitorId);
    }

    public Visitor update(long visitorId, Visitor visitor) { // befejezni 
        Visitor tempVisitor = find(visitorId);

        visitor.setId(find(visitorId).getId());
        visitor.setMachine(tempVisitor.getMachine());
        visitor.setPark(tempVisitor.getPark());
        visitor.setState(tempVisitor.getState());
        visitor.setIsActive(tempVisitor.isIsActive());

        for (GuestBook gb : entityManager.findAll(GuestBook.class)) {
            if (gb.getVisitorId().getId().equals(visitor.getId())) {
                gb.setVisitorId(visitor);
                entityManager.update(gb);
            }
        }

        return entityManager.update(visitor);

    }

    public void delete(long visitorId) {
        checkVisitor(visitorId);
        Visitor tempVisitor = entityManager.find(Visitor.class, visitorId);
        for (GuestBook gb : entityManager.findAll(GuestBook.class)) {
            gb.setVisitorId(null);
            entityManager.update(gb);

        }
        entityManager.delete(tempVisitor);
    }

    public boolean checkVisitor(long visitorId) {
        if (entityManager.find(Visitor.class, visitorId) != null) {
            return true;
        }
        throw new InvalidValues("there is no visitor with this id");
    }
}
