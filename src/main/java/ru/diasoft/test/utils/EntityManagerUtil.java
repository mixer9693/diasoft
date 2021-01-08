package ru.diasoft.test.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Returns an entity manager object
 *
 * @author Vikhor Mikhail
 */
public class EntityManagerUtil {
    private static EntityManager entityManager;

    /** Don't let anyone else instantiate this class */
    private EntityManagerUtil() {
    }

    /**
     * Returns current entity manager object.
     *
     * @return {@link EntityManager}
     */
    public static EntityManager getEntityManager(){
        if (entityManager == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ru.diasoft.test.entity");
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
}
