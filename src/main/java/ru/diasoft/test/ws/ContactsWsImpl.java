package ru.diasoft.test.ws;


import ru.diasoft.test.entity.Contact;
import ru.diasoft.test.service.EntityService;
import ru.diasoft.test.service.EntityServiceImpl;

import javax.jws.WebService;
import java.util.List;

/**
 * Implements a web service containing operations on an entity {@link Contact} using protocol SOAP.
 *
 * @author Vikhor Mikhail
 */
@WebService(
        endpointInterface = "ru.diasoft.test.ws.ContactsWs"
)
public class ContactsWsImpl implements ContactsWs {
    private final EntityService<Contact> entityService = new EntityServiceImpl<>(Contact.class);

    /**
     * Class constructor
     */
    public ContactsWsImpl(){
    }

    /**
     * Returns a list of all contacts
     *
     * @return List of all contacts {@link List<Contact>}
     */
    @Override
    public Contact getById(Integer id){
        return entityService.getById(id);
    }

    /**
     * Returns a list of all contacts
     *
     * @return List of all contacts {@link List<Contact>}
     */
    @Override
    public List<Contact> getAll(){
        return entityService.getAll();
    }

    /**
     * Creates a new contact.
     *
     * @param contact Contact, that must contain nested {@link ru.diasoft.test.entity.Person}
     *                and {@link ru.diasoft.test.entity.ContactType} objects with existing IDs
     * @return ID of the created contact {@link Integer}
     */
    @Override
    public Integer create(Contact contact) {
        return entityService.save(contact);
    }

    /**
     * Updates an existing contact
     *
     * @param contact Existing contact with nested {@link ru.diasoft.test.entity.Person}
     *               and {@link ru.diasoft.test.entity.ContactType} objects with existing IDs {@link Contact}
     */
    @Override
    public void update(Contact contact){
        entityService.update(contact);
    }

    /**
     * Removes existing contact
     *
     * @param contact Existing contact {@link Contact}
     */
    @Override
    public void remove(Contact contact){
        entityService.remove(contact);
    }

}
