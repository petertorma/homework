package tp.jpnpark.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import tp.jpnpark.entities.GuestBook;
import tp.jpnpark.entities.Park;
import tp.jpnpark.entities.Visitor;
import tp.jpnpark.exceptions.InvalidValues;
import tp.jpnpark.facade.EntityFacade;

/**
 *
 * @author Torma Péter
 */
@Stateless
public class GuestBookService {

    @Inject
    private EntityFacade entityManager;

    @Inject
    private ParkService parkService;

    @Inject
    private VisitorService visitorService;

    public boolean checkGuestBook(long gbId) {
        if (entityManager.find(GuestBook.class, gbId) == null) {
            throw new InvalidValues("book not exists with this ID:  " + gbId);
        }
        return true;
    }

    public GuestBook create(GuestBook gb, long parkId, long visitorId) {
        visitorService.checkVisitor(visitorId);
        parkService.checkPark(parkId);

        Park park = entityManager.find(Park.class, parkId);
        Visitor visitor = entityManager.find(Visitor.class, visitorId);

        if (!visitor.getPark().getId().equals(park.getId())) {
            throw new InvalidValues("the visitor is not in this park");
        }
        gb.setPark(park);
        gb.setVisitorId(visitor);
        return entityManager.create(gb);

    }

    public GuestBook update(long gbId, GuestBook gb) {
        checkGuestBook(gbId);
        GuestBook tempGb = entityManager.find(GuestBook.class, gbId);
        gb.setPark(tempGb.getPark());
        gb.setVisitorId(tempGb.getVisitorId());
        gb.setId(tempGb.getId());
        return entityManager.update(gb);
    }

    public GuestBook find(long gbId) {
        return entityManager.find(GuestBook.class, gbId);
    }

    public void delete(long gbId) {
        checkGuestBook(gbId);
        entityManager.delete(gbId);
    }
}
