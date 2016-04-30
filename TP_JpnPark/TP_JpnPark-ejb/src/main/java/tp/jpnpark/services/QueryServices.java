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
public class QueryServices {

    @Inject
    private EntityFacade entityManager;
    /*
    public List<GuestBook> logByVisitor(Long parkId, Long visitorId) {
        TypedQuery<GuestBook> guestbooks = entityManager.getEntityManager().createNamedQuery("logByVisitor", GuestBook.class);
        guestbooks.setParameter("parkId", parkId);
        guestbooks.setParameter("visitorId", visitorId);
        return guestbooks.getResultList();
    }

       public List<Visitor> visitorsOnMachine(Long machineId) {
    TypedQuery<Visitor> visitors = entityManager.getEntityManager().createNamedQuery("visitorsOnMachine", Visitor.class);
    visitors.setParameter("machineId", machineId);
    return visitors.getResultList();
    }
    
    public Integer tiredVisitors(Long parkId) {
    TypedQuery<Integer> visitors = entityManager.getEntityManager().createNamedQuery("tiredVisitors", Integer.class);
    visitors.setParameter("parkId", parkId);
    return visitors.getSingleResult();
    }

    public List<Machine> machinesInPark(Long parkId) {
        TypedQuery<Machine> machines = entityManager.getEntityManager().createNamedQuery("machinesInPark", Machine.class);
        machines.setParameter("parkId", parkId);
        return machines.getResultList();
    }
    
    public Integer actionVisitors(Long parkId) {
    TypedQuery<Integer> visitors = entityManager.getEntityManager().createNamedQuery("actionVisitors", Integer.class);
    visitors.setParameter("parkId", parkId);
    return visitors.getSingleResult();
    }*/

}
