package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Land entity
 *
 * @author Linus Englert
 */
@Entity
public class Land extends Data {
    private String name, amtssprache, iso2;

    public Land() {}
    public Land(String name, String amtssprache, String iso2) {
        this.name = name;
        this.amtssprache = amtssprache;
        this.iso2 = iso2;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAmtssprache() {
        return amtssprache;
    }
    public void setAmtssprache(String amtssprache) {
        this.amtssprache = amtssprache;
    }

    public String getIso2() {
        return iso2;
    }
    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Land value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.name, value.name) &&
                Objects.equals(this.amtssprache, value.amtssprache) &&
                Objects.equals(this.iso2, value.iso2);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.amtssprache, this.iso2);
    }
    @Override
    public String toString() {
        return String.format("Land{id=%s, name=%s, amtssprache=%s, iso2=%s}",
                this.id, this.name, this.amtssprache, this.iso2);
    }
}