package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Anschrift entity
 *
 * @author Linus Englert
 */
@Entity
public class Anschrift extends Data {
    private String anrede;
    private String telefon;
    private String email;

    public Anschrift() {}
    public Anschrift(String anrede, String telefon, String email) {
        this.anrede = anrede;
        this.telefon = telefon;
        this.email = email;
    }

    public String getAnrede() {
        return anrede;
    }
    public void setAnrede(String name) {
        this.anrede = name;
    }

    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
        if (!(o instanceof Anschrift value)) return false;
        return Objects.equals(this.id, value.id) && Objects.equals(this.anrede, value.anrede) &&
                Objects.equals(this.telefon, value.telefon) && Objects.equals(this.email, value.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.anrede, this.telefon, this.email);
    }
    @Override
    public String toString() {
        return String.format("Anschrift{id=%s, anrede=%s, telefon=%s, email=%s}", this.id, this.anrede, this.telefon,
                this.email);
    }
}
