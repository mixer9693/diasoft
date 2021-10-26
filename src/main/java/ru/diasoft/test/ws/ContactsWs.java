package ru.diasoft.test.ws;

import ru.diasoft.test.entity.Contact;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Describes a web service containing operations on an entity {@link Contact} using protocol SOAP.
 *
 * @author Vikhor Mikhail
 */
@WebService(name = "ContactsWs")
public interface ContactsWs {

    /**
     * Returns a contact by ID or <code>null</code> if not found.
     *
     * @param id  Contact ID {@link Integer}
     * @return    {@link Contact}
     */
    @WebMethod
    Contact getById(@WebParam(name = "id") Integer id);

    /**
     * Returns a list of all contacts
     *
     * @return List of all contacts {@link List<Contact>}
     */
    @WebMethod
    List<Contact> getAll();

    /**
     * Creates a new contact.
     *
     * @param contact Contact, that must contain nested {@link ru.diasoft.test.entity.Person}
     *                and {@link ru.diasoft.test.entity.ContactType} objects with existing IDs
     * @return ID of the created contact {@link Integer}
     */
    @WebMethod
    Integer create(@WebParam Contact contact);

    /**
     * Updates an existing contact
     *
     * @param contact Existing contact with nested {@link ru.diasoft.test.entity.Person}
     *                and {@link ru.diasoft.test.entity.ContactType} objects with existing IDs {@link Contact}
     */
    @WebMethod
    void update(@WebParam Contact contact);

    /**
     * Removes existing contact
     *
     * @param contact Existing contact {@link Contact}
     */
    @WebMethod
    void remove(@WebParam Contact contact);

}
