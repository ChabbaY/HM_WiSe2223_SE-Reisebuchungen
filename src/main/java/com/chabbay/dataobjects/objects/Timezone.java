package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Timezone entity
 *
 * @author Linus Englert
 */
@Entity
public class Timezone extends Data {
    private String designation;
    /**
     * difference to UTC time zone
     */
    private int diffUtc;

    public Timezone() {}
    public Timezone(String designation, int diffUtc) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String name) {
        this.designation = name;
    }

    public int getDiffUtc() {
        return diffUtc;
    }
    public void setDiffUtc(int diffUtc) {
        this.diffUtc = diffUtc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timezone value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.designation, value.designation) &&
                Objects.equals(this.diffUtc, value.diffUtc);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.designation, this.diffUtc);
    }
    @Override
    public String toString() {
        return String.format("Timezone{id=%s, designation=%s, diffUtc=%s}",
                this.id, this.designation, this.diffUtc);
    }
}