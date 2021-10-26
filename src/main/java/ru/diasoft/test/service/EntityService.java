package ru.diasoft.test.service;

import com.sun.istack.NotNull;

import java.util.List;

/**
 * Describes operations on entities in the database
 *
 * @author Vikhor Mikhail
 * @param <T> Class of entity
 */
public interface EntityService<T> {

    /**
     * Returns an entity with the specified ID or <code>null</code> if not found
     *
     * @param id ID of Entity {@link Integer}
     * @return {@link T}
     */
    T getById(@NotNull Integer id);

    /**
     * Returns a list of all entities
     *
     * @return {@link List<T>}
     */
    List<T> getAll();

    /**
     * Saves the new entity to the database
     *
     * @param entity {@link T}
     */
    Integer save(@NotNull T entity);

    /**
     * Updates an existing entity in the database
     *
     * @param entity {@link T}
     */
    void update(@NotNull T entity);

    /**
     * Removes an existing entity from the database
     *
     * @param entity {@link T}
     */
    void remove(@NotNull T entity);
}
