package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * definition of the Country entity
 *
 * @author Linus Englert
 */
@Entity
public final class Country {
    private @Id @GeneratedValue long id;
    private String name, language, iso2;

    public Country() {}
    public Country(String name, String language, String iso2) {
        this.name = name;
        this.language = language;
        this.iso2 = iso2;
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

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
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
        if (!(o instanceof Country value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.name, value.name) &&
                Objects.equals(this.language, value.language) &&
                Objects.equals(this.iso2, value.iso2);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.language, this.iso2);
    }
    @Override
    public String toString() {
        return String.format("Country{id=%s, name=%s, language=%s, iso2=%s}",
                this.id, this.name, this.language, this.iso2);
    }
}