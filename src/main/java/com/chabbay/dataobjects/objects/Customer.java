package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * definition of the Customer entity
 *
 * @author Linus Englert
 */
@Entity
public final class Customer {
    private @Id @GeneratedValue long id;
    private String firstname, lastname, birthdate;

    //foreign key
    private long addressinformationId;

    public Customer() {}
    public Customer(String firstname, String lastname, String birthdate, long addressinformationId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.addressinformationId = addressinformationId;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String amtssprache) {
        this.lastname = amtssprache;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public long getAddressinformationId() {
        return addressinformationId;
    }
    public void setAddressinformationId(long addressinformationId) {
        this.addressinformationId = addressinformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.firstname, value.firstname) &&
                Objects.equals(this.lastname, value.lastname) &&
                Objects.equals(this.birthdate, value.birthdate) &&
                Objects.equals(this.addressinformationId, value.addressinformationId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstname, this.lastname, this.birthdate,
                this.addressinformationId);
    }
    @Override
    public String toString() {
        return String.format("Customer{id=%s, firstname=%s, lastname=%s, birthdate=%s, addressinformationId=%s}",
                this.id, this.firstname, this.lastname, this.birthdate, this.addressinformationId);
    }
}