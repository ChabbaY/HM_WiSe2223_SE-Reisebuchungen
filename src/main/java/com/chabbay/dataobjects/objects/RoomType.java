package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * definition of the RoomType entity
 *
 * @author Linus Englert
 */
@Entity
public class RoomType extends Data {
    private String name;
    private double price;
    private int people;

    public RoomType() {}
    public RoomType(String name, double price, int people) {
        this.name = name;
        this.price = price;
        this.people = people;
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