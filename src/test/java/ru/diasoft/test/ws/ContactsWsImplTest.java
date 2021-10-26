package ru.diasoft.test.ws;

import ru.diasoft.test.DataFixture;
import ru.diasoft.test.client.generated.*;
import ru.diasoft.test.client.generated.ContactsWs;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for <code>ContactsWsImpl</code>
 *
 * @author Vikhor Mikhail
 */
class ContactsWsImplTest {

    static ContactsWs port;
    static DataFixture mockData;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        mockData = new DataFixture();
        mockData.insert();
        Publisher.publish();
        ContactsWsImplService contactsWs = new ContactsWsImplService(new URL(Publisher.url));
        port = contactsWs.getContactsWsImplPort();
    }

    @Test
    void getContactByIdWithValidParam(){
        Contact contact = port.getById(DataFixture.CONTACT_1_ID);
        assertNotNull(contact);
        assertNotNull(contact.getPerson());
        assertNotNull(contact.getContactType());
    }

    @Test
    void getContactByIdWithInvalidParam(){
        Contact contact = port.getById(-1);
        assertNull(contact);

        contact = port.getById(999999);
        assertNull(contact);

        assertThrows(ServerSOAPFaultException.class, () -> {
            port.getById(null);
        });
    }

    @Test
    void getAllContacts(){
        List<Contact> contacts = port.getAll();
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
        Integer id = port.create(contact);
        assertNotNull(id);
        mockData.getCreatedContactIds().add(id);
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
        assertThrows(ServerSOAPFaultException.class, () -> {
            Integer id = port.create(contact);
            mockData.getCreatedContactIds().add(id);
        });

        //without ContactType
        Contact contact2 = new Contact();
        contact2.setNumber(correctNumber);
        contact2.setPerson(person);
        contact2.setContactType(null);
        assertThrows(ServerSOAPFaultException.class, () -> {
            Integer id = port.create(contact2);
            mockData.getCreatedContactIds().add(id);
        });


        //without Person
        Contact contact3 = new Contact();
        contact3.setNumber(correctNumber);
        contact3.setPerson(null);
        contact3.setContactType(contactType);
        assertThrows(ServerSOAPFaultException.class, () -> {
            Integer id = port.create(contact3);
            mockData.getCreatedContactIds().add(id);
        });

        //long number
        Contact contact5 = new Contact();
        contact5.setNumber(longNumber);
        contact5.setPerson(person);
        contact5.setContactType(contactType);
        assertThrows(ServerSOAPFaultException.class, () -> {
            Integer id = port.create(contact5);
            mockData.getCreatedContactIds().add(id);
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

        assertThrows(ServerSOAPFaultException.class, () -> {
            Integer id = port.create(contact);
            mockData.getCreatedContactIds().add(id);
        });
    }

    @Test
    void updateContactWithValidParams(){
        String newNumber = "222444777888";
        Contact contact = port.getById(DataFixture.CONTACT_1_ID);
        contact.setNumber(newNumber);
        port.update(contact);

        Contact updatedContact = null;
        try{
            updatedContact = port.getById(DataFixture.CONTACT_1_ID);
            assertNotNull(updatedContact);
        }catch (Exception e){
            fail("Method execution aborted due to error getting contact: " + e.getMessage());
        }
        assertEquals(updatedContact.getNumber(), newNumber);
    }


    @Test
    void updateContactWithInvalidParams(){
        String longNumber = "487487418498498f4e98fe98f4ed98f49e8f4e1f49";
        Contact contact = port.getById(DataFixture.CONTACT_1_ID);

        contact.setNumber(longNumber);
        assertThrows(ServerSOAPFaultException.class, () -> {
            port.update(contact);
        });

        contact.setPerson(new Person());
        assertThrows(ServerSOAPFaultException.class, () -> {
            port.update(contact);
        });

        Contact detachedContact = new Contact();
        detachedContact.setId(DataFixture.CONTACT_1_ID);
        detachedContact.setNumber("778");
        assertThrows(ServerSOAPFaultException.class, () -> {
            port.update(detachedContact);
        });
    }

    @Test
    void removeContactWithValidParams(){
        Contact contact = port.getById(DataFixture.CONTACT_2_ID);
        port.remove(contact);

        Contact removedContact = null;
        try {
            removedContact  = port.getById(DataFixture.CONTACT_2_ID);
        }catch (Exception e){
            fail("Method execution aborted due to error getting contact: " + e.getMessage());
        }
        assertNull(removedContact);
    }

    @Test
    void removeContactWithInvalidParams(){
        Contact contact = new Contact();
        contact.setId(DataFixture.CONTACT_2_ID);
        assertThrows(ServerSOAPFaultException.class, () -> {
            port.remove(contact);
        });
    }

    @AfterAll
    static void afterAll(){
        Publisher.stop();
        mockData.delete();
    }

}