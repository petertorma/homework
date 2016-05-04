package tp.jpnpark.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import tp.jpnpark.entities.GuestBook;
import tp.jpnpark.entities.Machine;
import tp.jpnpark.entities.Visitor;
import tp.jpnpark.facade.EntityFacade;

/**
 *
 * @author Torma Péter
 */
@Stateless
public class QueryServices  {

    @Inject
    private EntityFacade entityManager;
    
    private static final String PARAMS="parkId";
    
    public List<GuestBook> logByVisitor(Long id, Long visitorId) {
        TypedQuery<GuestBook> guestbooks = entityManager.getEntityManager().createNamedQuery("logByVisitor", GuestBook.class);
        guestbooks.setParameter(PARAMS, id);
        guestbooks.setParameter("visitorId", visitorId);
        return guestbooks.getResultList();
    }

    public List<Visitor> visitorsOnMachine(Long machineId) {
        TypedQuery<Visitor> visitors = entityManager.getEntityManager().createNamedQuery("visitorsOnMachine", Visitor.class);
        visitors.setParameter("machineId", machineId);
        return visitors.getResultList();
    }

    public String tiredVisitors(Long parkId) {
        TypedQuery<Long> visitors = entityManager.getEntityManager().createNamedQuery("tiredVisitors", Long.class);
        visitors.setParameter(PARAMS, parkId);
        return visitors.getSingleResult().toString();
    }

    public List<Machine> machinesInPark(Long parkId) {
        TypedQuery<Machine> machines = entityManager.getEntityManager().createNamedQuery("machinesInPark", Machine.class);
        machines.setParameter(PARAMS, parkId);
        return machines.getResultList();
    }

    public String actionVisitors(Long parkId) {
        TypedQuery<Long> visitors = entityManager.getEntityManager().createNamedQuery("actionVisitors", Long.class);
        visitors.setParameter(PARAMS, parkId);
        return visitors.getSingleResult().toString();
    }

}
