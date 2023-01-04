package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * definition of the Room entity
 *
 * @author Linus Englert
 */
@Entity
public final class Room {
    private @Id @GeneratedValue long id;

    //foreign key
    private long hotelId, roomTypeId;
    public Room() {}

    public Room(long hotelId, long roomTypeId) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public long getHotelId() {
        return hotelId;
    }
    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public long getRoomTypeId() {
        return roomTypeId;
    }
    public void setRoomTypeId(long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.hotelId, value.hotelId) &&
                Objects.equals(this.roomTypeId, value.roomTypeId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.hotelId, this.roomTypeId);
    }
    @Override
    public String toString() {
        return String.format("Room{id=%s, hotelId=%s, roomTypeId=%s}",
                this.id, this.hotelId, this.roomTypeId);
    }
}