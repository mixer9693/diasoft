package ru.diasoft.test.service;


import ru.diasoft.test.DataFixture;
import ru.diasoft.test.entity.Contact;
import ru.diasoft.test.entity.ContactType;
import ru.diasoft.test.entity.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for <code>EntityServiceImpl</code>
 *
 * @author Vikhor Mikhail
 */
class EntityServiceImplTest {
    static EntityService<Contact> contactEntityService = new EntityServiceImpl<>(Contact.class);
    static DataFixture mockData;

    @BeforeAll
    static void beforeAll() {
        mockData = new DataFixture();
        mockData.insert();
    }

    @Test
    void getContactByIdWithValidParam(){
        Contact contact = contactEntityService.getById(DataFixture.CONTACT_1_ID);
        assertNotNull(contact);
        assertNotNull(contact.getPerson());
        assertNotNull(contact.getContactType());
    }

    @Test
    void getContactByIdWithInvalidParam(){
        Contact contact = contactEntityService.getById(-1);
        assertNull(contact);

        contact = contactEntityService.getById(999999);
        assertNull(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            contactEntityService.getById(null);
        });
    }

    @Test
    void getAllContacts(){
        List<Contact> contacts = contactEntityService.getAll();
        assertNotNull(contacts);
        assertTrue(contacts.size() > 0);
    }

    @Test
    void saveContactWithValidData(){
        Person person = new Person();
        person.setId(DataFixture.PERSON_ID);

        ContactType contactType = new ContactType();
        contactType.setId(DataFixture.CONTACT_TYPE_ID);

        Contact contact = new Contact();
        contact.setNumber("79998554785");
        contact.setPerson(person);
        contact.setContactType(contactType);
        contactEntityService.save(contact);
        assertNotNull(contact.getId());
        mockData.getCreatedContactIds().add(contact.getId());
    }

    @Test
    void saveContactWithInvalidParams(){
        final String correctNumber = "79995478541";
        final String longNumber = "14787856988547885214565555652979889dsdssd";

        Person person = new Person();
        person.setId(DataFixture.PERSON_ID);

        ContactType contactType = new ContactType();
        contactType.setId(DataFixture.CONTACT_TYPE_ID);

        //without ContactType and Person
        Contact contact = new Contact();
        contact.setNumber(correctNumber);
        assertThrows(PersistenceException.class, () -> {
           contactEntityService.save(contact);
           mockData.getCreatedContactIds().add(contact.getId());
        });

        //without ContactType
        Contact contact2 = new Contact();
        contact2.setNumber(correctNumber);
        contact2.setPerson(person);
        contact2.setContactType(null);
        assertThrows(PersistenceException.class, () -> {
            contactEntityService.save(contact2);
            mockData.getCreatedContactIds().add(contact2.getId());
        });

        //without Person
        Contact contact3 = new Contact();
        contact3.setNumber(correctNumber);
        contact3.setPerson(null);
        contact3.setContactType(contactType);
        assertThrows(PersistenceException.class, () -> {
            contactEntityService.save(contact3);
            mockData.getCreatedContactIds().add(contact3.getId());
        });

        //long number
        Contact contact5 = new Contact();
        contact5.setNumber(longNumber);
        contact5.setPerson(person);
        contact5.setContactType(contactType);
        assertThrows(PersistenceException.class, () -> {
            contactEntityService.save(contact5);
            mockData.getCreatedContactIds().add(contact5.getId());
        });

    }

    @Test
    void saveExistingContact(){
        Person person = new Person();
        person.setId(DataFixture.PERSON_ID);

        ContactType contactType = new ContactType();
        contactType.setId(DataFixture.CONTACT_TYPE_ID);

        Contact contact = new Contact();
        contact.setId(DataFixture.CONTACT_1_ID);
        contact.setNumber("78589664547");
        contact.setPerson(person);
        contact.setContactType(contactType);

        assertThrows(PersistenceException.class, () -> {
            contactEntityService.save(contact);
            mockData.getCreatedContactIds().add(contact.getId());
        });
    }

    @Test
    void updateContactWithValidParams(){
        String newNumber = "222444777888";
        Contact contact = contactEntityService.getById(DataFixture.CONTACT_1_ID);
        contact.setNumber(newNumber);
        contactEntityService.update(contact);

        Contact updatedContact = null;
        try{
            updatedContact = contactEntityService.getById(DataFixture.CONTACT_1_ID);
            assertNotNull(updatedContact);
        }catch (Exception e){
            fail("Method execution aborted due to error getting contact: " + e.getMessage());
        }
        assertEquals(updatedContact.getNumber(), newNumber);
    }


    @Test
    void updateContactWithInvalidParams(){
        String longNumber = "487487418498498f4e98fe98f4ed98f49e8f4e1f49";
        Contact contact = contactEntityService.getById(DataFixture.CONTACT_1_ID);

        contact.setNumber(longNumber);
        assertThrows(PersistenceException.class, () -> {
            contactEntityService.update(contact);
        });

        contact.setPerson(new Person());
        assertThrows(PersistenceException.class, () -> {
            contactEntityService.update(contact);
        });

        Contact detachedContact = new Contact();
        detachedContact.setId(DataFixture.CONTACT_1_ID);
        detachedContact.setNumber("778");
        assertThrows(PersistenceException.class, () -> {
            contactEntityService.update(detachedContact);
        });
    }

    @Test
    void removeContactWithValidParams(){
        Contact contact = contactEntityService.getById(DataFixture.CONTACT_2_ID);
        contactEntityService.remove(contact);

        Contact removedContact = null;
        try {
            removedContact  = contactEntityService.getById(DataFixture.CONTACT_2_ID);
        }catch (Exception e){
            fail("Method execution aborted due to error getting contact: " + e.getMessage());
        }
        assertNull(removedContact);
    }

    @Test
    void removeContactWithInvalidParams(){
        Contact contact = new Contact();
        contact.setId(DataFixture.CONTACT_2_ID);
        assertThrows(PersistenceException.class, () -> {
           contactEntityService.remove(contact);
        });
    }

    @AfterAll
    static void afterAll(){
        mockData.delete();
    }

}