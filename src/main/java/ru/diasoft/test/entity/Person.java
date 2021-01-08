package ru.diasoft.test.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class, maps to table 'person'
 *
 * @author Vikhor Mikhail
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String position;
    private List<Contact> contacts = new ArrayList<>();

    /**
     * Class constructor
     */
    public Person() {
    }

    /**
     * Gets the value of the id property.
     *
     * @return {@link Integer}
     */
    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param id {@link Integer}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return {@link String}
     */
    @Column(name = "first_name", length = 80)
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param firstName {@link String}
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return {@link String}
     */
    @Column(name = "last_name", length = 80)
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param lastName {@link String}
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the value of the middleName property.
     *
     * @return {@link String}
     */
    @Column(name = "middle_name", length = 80)
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     *
     * @param middleName {@link String}
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the value of the position property.
     *
     * @return {@link String}
     */
    @Column
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     *
     * @param position {@link String}
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the value of the contacts property.
     *
     * @return List of contacts {@link List<Contact>}
     */
    @XmlTransient
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets the value of the contacts property.
     *
     * @param contacts {@link List<Contact>}
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return Person string representation {@link String}
     */
    @Override
    public String toString() {
        return String.format("{id: %s, firstName: %s, last_name: %s, middle_name: %s, position: %s}",
                getId(), getFirstName(), getLastName(), getMiddleName(), getPosition());
    }
}
