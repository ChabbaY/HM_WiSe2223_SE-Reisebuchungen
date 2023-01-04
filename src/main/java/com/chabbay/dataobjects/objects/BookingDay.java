package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * definition of the BookingDay entity
 *
 * @author Linus Englert
 */
@Entity
public class BookingDay extends Data {
    private String date;

    public BookingDay() {}
    public BookingDay(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingDay value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.date, value.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.date);
    }
    @Override
    public String toString() {
        return String.format("BookingDay{id=%s, date=%s}",
                this.id, this.date);
    }
}