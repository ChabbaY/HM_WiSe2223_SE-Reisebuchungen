package com.chabbay.data;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * definition of the Hotel entity
 *
 * @author Linus Englert
 */
@Entity
public class Hotel {
    private @Id @GeneratedValue Long id;
    private String name;

    public Hotel() {}
    public Hotel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel value)) return false;
        return Objects.equals(this.id, value.id) && Objects.equals(this.name, value.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
    @Override
    public String toString() {
        return String.format("Hotel{id=%s, name=%s}", this.id, this.name);
    }
}