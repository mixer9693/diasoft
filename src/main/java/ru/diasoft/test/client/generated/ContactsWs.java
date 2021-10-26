
package ru.diasoft.test.client.generated;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ContactsWs", targetNamespace = "http://ws.test.diasoft.ru/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ContactsWs {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "remove", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.Remove")
    @ResponseWrapper(localName = "removeResponse", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.RemoveResponse")
    @Action(input = "http://ws.test.diasoft.ru/ContactsWs/removeRequest", output = "http://ws.test.diasoft.ru/ContactsWs/removeResponse")
    public void remove(
        @WebParam(name = "arg0", targetNamespace = "")
        Contact arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "update", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.UpdateResponse")
    @Action(input = "http://ws.test.diasoft.ru/ContactsWs/updateRequest", output = "http://ws.test.diasoft.ru/ContactsWs/updateResponse")
    public void update(
        @WebParam(name = "arg0", targetNamespace = "")
        Contact arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.CreateResponse")
    @Action(input = "http://ws.test.diasoft.ru/ContactsWs/createRequest", output = "http://ws.test.diasoft.ru/ContactsWs/createResponse")
    public Integer create(
        @WebParam(name = "arg0", targetNamespace = "")
        Contact arg0);

    /**
     * 
     * @param id
     * @return
     *     returns ru.diasoft.test.client.generated.Contact
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getById", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.GetById")
    @ResponseWrapper(localName = "getByIdResponse", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.GetByIdResponse")
    @Action(input = "http://ws.test.diasoft.ru/ContactsWs/getByIdRequest", output = "http://ws.test.diasoft.ru/ContactsWs/getByIdResponse")
    public Contact getById(
        @WebParam(name = "id", targetNamespace = "")
        Integer id);

    /**
     * 
     * @return
     *     returns java.util.List<ru.diasoft.test.client.generated.Contact>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://ws.test.diasoft.ru/", className = "ru.diasoft.test.client.generated.GetAllResponse")
    @Action(input = "http://ws.test.diasoft.ru/ContactsWs/getAllRequest", output = "http://ws.test.diasoft.ru/ContactsWs/getAllResponse")
    public List<Contact> getAll();

}