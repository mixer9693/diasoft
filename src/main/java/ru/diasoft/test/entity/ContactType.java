package ru.diasoft.test.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class, maps to table 'contact_type'
 *
 * @author Vikhor Mikhail
 */
@Entity
@Table(name = "contact_type")
public class ContactType implements Serializable {
    private Integer id;
    private String type;

    /**
     * Class constructor
     */
    public ContactType() {
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
     * Gets the value of the type property.
     *
     * @return {@link String}
     */
    @Column
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param type {@link String}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return ContactType string representation {@link String}
     */
    @Override
    public String toString() {
        return String.format("{id: %s, type: %s}", getId(), getType());
    }
}
