package ru.diasoft.test.service;

import ru.diasoft.test.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Implements operations on entities in the database.
 *
 * @author Vikhor Mikhail
 * @param <T> Type of Entity {@link Class<T>}
 */
public class EntityServiceImpl<T> implements EntityService<T>{
    protected final EntityManager entityManager = EntityManagerUtil.getEntityManager();
    protected final Class<T> tClass;

    /**
     * Class constructor
     *
     * @param tClass Type of entity {@link Class<T>}
     */
    public EntityServiceImpl(Class<T> tClass){
        this.tClass = tClass;
    }

    /**
     * Returns an entity with the specified ID or <code>null</code> if not found
     *
     * @param id ID of Entity {@link Integer}
     * @return {@link T}
     */
    @Override
    public T getById(Integer id) {
        return entityManager.find(tClass, id);
    }

    /**
     * Returns a list of all entities
     *
     * @return {@link List<T>}
     */
    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(tClass);
        criteria.from(tClass);
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * Saves the new entity to the database
     *
     * @param entity {@link T}
     */
    @Override
    public Integer save(T entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(entity);
            Integer id;
            try {
                Field field = entity.getClass().getDeclaredField("id");
                field.setAccessible(true);
                id = (Integer) field.get(entity);
            }catch (IllegalAccessException | NoSuchFieldException e){
                throw new RuntimeException(e);
            }
            entityManager.getTransaction().commit();
            return id;
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Updates an existing entity in the database
     *
     * @param entity {@link T}
     */
    @Override
    public void update(T entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Removes an existing entity from the database
     *
     * @param entity {@link T}
     */
    @Override
    public void remove(T entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(entityManager.contains(entity)? entity: entityManager.merge(entity));
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
