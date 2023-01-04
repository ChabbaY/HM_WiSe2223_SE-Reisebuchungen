package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * definition of the RoomType entity
 *
 * @author Linus Englert
 */
@Entity
public final class RoomType {
    private @Id @GeneratedValue long id;
    private String name;
    private double price;
    private int people;

    public RoomType() {}
    public RoomType(String name, double price, int people) {
        this.name = name;
        this.price = price;
        this.people = people;
    }

    public long getId() {
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

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getPeople() {
        return people;
    }
    public void setPeople(int people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomType value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.name, value.name) &&
                Objects.equals(this.price, value.price) &&
                Objects.equals(this.people, value.people);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.price, this.people);
    }
    @Override
    public String toString() {
        return String.format("RoomType{id=%s, name=%s, price=%s, people=%s}",
                this.id, this.name, this.price, this.people);
    }
}