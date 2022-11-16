package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the AddressInformation entity
 *
 * @author Linus Englert
 */
@Entity
public class AddressInformation extends Data {
    private String title, telephone, email;

    public AddressInformation() {}
    public AddressInformation(String title, String telephone, String email) {
        this.title = title;
        this.telephone = telephone;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telefon) {
        this.telephone = telefon;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressInformation value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.title, value.title) &&
                Objects.equals(this.telephone, value.telephone) &&
                Objects.equals(this.email, value.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.telephone, this.email);
    }
    @Override
    public String toString() {
        return String.format("AddressInformation{id=%s, title=%s, telephone=%s, email=%s}",
                this.id, this.title, this.telephone, this.email);
    }
}