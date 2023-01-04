package com.chabbay.dataobjects.objects;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Country entity
 *
 * @author Linus Englert
 */
@Entity
public class Country extends Data {
    private String name, language, iso2;

    //foreign key
    private List<Long> timezoneIds;

    public Country() {}
    public Country(String name, String language, String iso2, List<Long> timezoneIds) {
        this.name = name;
        this.language = language;
        this.iso2 = iso2;
        this.timezoneIds = timezoneIds;
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
    public void setLanguage(String amtssprache) {
        this.language = amtssprache;
    }

    public String getIso2() {
        return iso2;
    }
    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public List<Long> getTimezoneIds() {
        return timezoneIds;
    }
    public void setTimezoneIds(List<Long> timezoneIds) {
        this.timezoneIds = timezoneIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.name, value.name) &&
                Objects.equals(this.language, value.language) &&
                Objects.equals(this.iso2, value.iso2) &&
                Objects.equals(this.timezoneIds, value.timezoneIds);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.language, this.iso2, this.timezoneIds);
    }
    @Override
    public String toString() {
        return String.format("Country{id=%s, name=%s, language=%s, iso2=%s, timezoneIds=%s}",
                this.id, this.name, this.language, this.iso2, Arrays.toString(this.timezoneIds.toArray()));
    }
}