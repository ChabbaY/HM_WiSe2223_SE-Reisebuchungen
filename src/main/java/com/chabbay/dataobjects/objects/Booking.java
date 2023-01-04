package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * definition of the Booking entity
 *
 * @author Linus Englert
 */
@Entity
public final class Booking {
    private @Id @GeneratedValue long id;
    private String date, status;

    //foreign key
    private long customerId;

    public Booking() {}
    public Booking(String date, String status, long customerId) {
        this.date = date;
        this.status = status;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.date, value.date) &&
                Objects.equals(this.status, value.status) &&
                Objects.equals(this.customerId, value.customerId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.date, this.status, this.customerId);
    }
    @Override
    public String toString() {
        return String.format("Booking{id=%s, date=%s, status=%s, customerId=%s}",
                this.id, this.date, this.status, this.customerId);
    }
}