package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * definition of the Customer entity
 *
 * @author Linus Englert
 */
@Entity
public class Customer extends Data {
    private String firstname, lastname, birthdate;

    public Customer() {}
    public Customer(String firstname, String lastname, String birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.firstname, value.firstname) &&
                Objects.equals(this.lastname, value.lastname) &&
                Objects.equals(this.birthdate, value.birthdate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstname, this.lastname, this.birthdate);
    }
    @Override
    public String toString() {
        return String.format("Customer{id=%s, firstname=%s, lastname=%s, birthdate=%s}",
                this.id, this.firstname, this.lastname, this.birthdate);
    }
}