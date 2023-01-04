package com.chabbay.dataobjects.objects;

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
public final class Hotel {
    private @Id @GeneratedValue long id;
    private String name;

    //foreign key
    private long addressInformationId;

    public Hotel() {}
    public Hotel(String name, long addressInformationId) {
        this.name = name;
        this.addressInformationId = addressInformationId;
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

    public long getAddressInformationId() {
        return addressInformationId;
    }
    public void setAddressInformationId(long addressInformationId) {
        this.addressInformationId = addressInformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.name, value.name) &&
                Objects.equals(this.addressInformationId, value.addressInformationId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.addressInformationId);
    }
    @Override
    public String toString() {
        return String.format("Hotel{id=%s, name=%s, addressinformationId=%s}",
                this.id, this.name, this.addressInformationId);
    }
}