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
    private String number, firstname, lastname, birthdate;

    //foreign key
    private long addressInformationId;

    public Customer() {}
    public Customer(String number, String firstname, String lastname, String birthdate, long addressInformationId) {
        this.number = number;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.addressInformationId = addressInformationId;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
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

    public long getAddressInformationId() {
        return addressInformationId;
    }
    public void setAddressInformationId(long addressinformationId) {
        this.addressInformationId = addressinformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.number, value.number) &&
                Objects.equals(this.firstname, value.firstname) &&
                Objects.equals(this.lastname, value.lastname) &&
                Objects.equals(this.birthdate, value.birthdate) &&
                Objects.equals(this.addressInformationId, value.addressInformationId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.number, this.firstname, this.lastname, this.birthdate,
                this.addressInformationId);
    }
    @Override
    public String toString() {
        return String.format("Customer{id=%s, number=%s, firstname=%s, lastname=%s, birthdate=%s, addressinformationId=%s}",
                this.id, this.number, this.firstname, this.lastname, this.birthdate, this.addressInformationId);
    }
}