package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Zeitzone entity
 *
 * @author Linus Englert
 */
@Entity
public class Zeitzone extends Data {
    private String bezeichnung;
    /**
     * difference to UTC time zone
     */
    private int diffUtc;

    public Zeitzone() {}
    public Zeitzone(String bezeichnung, int diffUtc) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
    public void setBezeichnung(String name) {
        this.bezeichnung = name;
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
        if (!(o instanceof Zeitzone value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.bezeichnung, value.bezeichnung) &&
                Objects.equals(this.diffUtc, value.diffUtc);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.bezeichnung, this.diffUtc);
    }
    @Override
    public String toString() {
        return String.format("Zeitzone{id=%s, bezeichnung=%s, diffUtc=%s}",
                this.id, this.bezeichnung, this.diffUtc);
    }
}