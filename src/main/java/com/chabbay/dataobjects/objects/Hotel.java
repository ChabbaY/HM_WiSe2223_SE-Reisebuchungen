package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Hotel entity
 *
 * @author Linus Englert
 */
@Entity
public class Hotel extends Data {
    private String name;

    //foreign key
    private long addressinformationId;

    public Hotel() {}
    public Hotel(String name, long addressinformationId) {
        this.name = name;
        this.addressinformationId = addressinformationId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Hotel value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.name, value.name) &&
                Objects.equals(this.addressinformationId, value.addressinformationId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.addressinformationId);
    }
    @Override
    public String toString() {
        return String.format("Hotel{id=%s, name=%s, addressinformationId=%s}",
                this.id, this.name, this.addressinformationId);
    }
}