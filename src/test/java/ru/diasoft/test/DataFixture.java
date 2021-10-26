package ru.diasoft.test;

import ru.diasoft.test.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for managing test data in the database.
 *
 * @author Vikhor Mikhail
 */
public class DataFixture {
    private final static EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private final List<Integer> createdContactIds;

    protected final static String insertIntoContactType =
            "insert into contact_type (id, type) values (?1, ?2)";
    protected final static String insertIntoPerson =
            "insert into person (id, first_name, last_name, middle_name, position) values (?1, ?2, ?3, ?4, ?5)";
    protected final static String insertIntoContact =
            "insert into contacts (id, number, contact_type_id, person_id) values (?1, ?2, ?3, ?4)";

    /** Keeps inserted ContactType id */
    public final static int CONTACT_TYPE_ID = 999990;

    /** Keeps inserted Person id */
    public final static int PERSON_ID = 998000;

    /** Keeps inserted first Contact id */
    public final static int CONTACT_1_ID = 997000;

    /** Keeps inserted second Contact id */
    public final static int CONTACT_2_ID = 997001;

    /**
     * Class constructor
     */
    public DataFixture(){
        createdContactIds = new ArrayList<>();
    }

    /**
     * Returns the list of IDs of inserted contacts
     *
     * @return {@link List<Integer>}
     */
    public List<Integer> getCreatedContactIds() {
        return createdContactIds;
    }

    /**
     * Inserts test data into the database
     */
    public void insert(){
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(insertIntoContactType)
                .setParameter(1, CONTACT_TYPE_ID)
                .setParameter( 2, "Рабочий-Тестовый")
                .executeUpdate();

        entityManager.createNativeQuery(insertIntoPerson)
                .setParameter(1, PERSON_ID)
                .setParameter(2, "Фрунзик")
                .setParameter(3, "Мушегович")
                .setParameter(4, "Мкртчян")
                .setParameter(5, "Актер")
                .executeUpdate();

        entityManager.createNativeQuery(insertIntoContact)
                .setParameter(1, CONTACT_1_ID)
                .setParameter(2, "79847854785")
                .setParameter(3, CONTACT_TYPE_ID)
                .setParameter(4, PERSON_ID)
                .executeUpdate();

        entityManager.createNativeQuery(insertIntoContact)
                .setParameter(1, CONTACT_2_ID)
                .setParameter(2, "7254796354")
                .setParameter(3, CONTACT_TYPE_ID)
                .setParameter(4, PERSON_ID)
                .executeUpdate();
        entityManager.getTransaction().commit();

        createdContactIds.add(CONTACT_1_ID);
        createdContactIds.add(CONTACT_2_ID);
    }

    /**
     * Removes test data from the database
     */
    public void delete(){
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }

        for (Integer id: createdContactIds){
            entityManager.createNativeQuery("delete from contacts where id = ?")
                    .setParameter(1, id)
                    .executeUpdate();
        }

        entityManager.createNativeQuery("delete from contact_type where id = ?1")
                .setParameter(1, CONTACT_TYPE_ID)
                .executeUpdate();

        entityManager.createNativeQuery("delete from person where id = ?1")
                .setParameter(1, PERSON_ID)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

}
