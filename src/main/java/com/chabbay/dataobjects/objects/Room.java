package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * definition of the Room entity
 *
 * @author Linus Englert
 */
@Entity
public class Room extends Data {
    public Room() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room value)) return false;
        return Objects.equals(this.id, value.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
    @Override
    public String toString() {
        return String.format("Room{id=%s}",
                this.id);
    }
}