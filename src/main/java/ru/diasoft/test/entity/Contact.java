package ru.diasoft.test.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class, maps to table 'contacts'
 *
 * @author Vikhor Mikhail
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    private Integer id;
    private ContactType contactType;
    private String number;
    private Person person;

    /**
     * Class constructor
     */
    public Contact() {
    }

    /**
     * Gets the value of the id property.
     *
     * @return object {@link Integer}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param id object {@link Integer }
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the value of the contactType property.
     *
     * @return object {@link ContactType}
     */
    @ManyToOne()
    @JoinColumn(name = "contact_type_id", nullable = false)
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * Sets the value of the contactType property.
     *
     * @param contactType {@link ContactType}
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * Gets the value of the number property.
     *
     * @return {@link String}
     */
    @Column(length = 20)
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     *
     * @param number {@link String}
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the value of the person property.
     *
     * @return {@link Person}
     */
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     *
     * @param person {@link Person}
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return Contact string representation {@link String}
     */
    @Override
    public String toString() {
        return String.format("{id: %s, number: %s, contact_type_id: %s, person_id: %s}",
                getId(),
                getNumber(),
                getContactType() != null ? getContactType().getId(): null,
                getPerson() != null ? getPerson().getId(): null);
    }
}
