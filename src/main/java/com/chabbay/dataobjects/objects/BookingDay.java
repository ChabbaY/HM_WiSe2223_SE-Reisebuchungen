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

    //foreign key
    private long bookingId, roomId;

    public BookingDay() {}
    public BookingDay(String date, long bookingId, long roomId) {
        this.date = date;
        this.bookingId = bookingId;
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public long getBookingId() {
        return bookingId;
    }
    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getRoomId() {
        return roomId;
    }
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingDay value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.date, value.date) &&
                Objects.equals(this.bookingId, value.bookingId) &&
                Objects.equals(this.roomId, value.roomId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.date, this.bookingId, this.roomId);
    }
    @Override
    public String toString() {
        return String.format("BookingDay{id=%s, date=%s, bookingId=%s, roomId=%s}",
                this.id, this.date, this.bookingId, this.roomId);
    }
}