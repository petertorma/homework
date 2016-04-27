package tp.jpnpark.facade;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Torma Péter
 */
public class EntityFacade {

    private static final Logger LOGGER = Logger.getLogger(EntityFacade.class.getName());

    @PersistenceContext(unitName = "vidamparkPU")
    protected EntityManager entityManager;

    public EntityFacade() {
        //default 
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public <T> T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public <T> T update(T entity) {
        return entityManager.merge(entity);
    }

    public <T> void delete(T entity) {
        entityManager.remove(entity);
    }

    public <T> T find(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }

}
