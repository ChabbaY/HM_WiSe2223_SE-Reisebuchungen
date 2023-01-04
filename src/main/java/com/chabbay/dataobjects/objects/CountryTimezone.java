package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * definition of the CountryTimezone entity
 *
 * @author Linus Englert
 */
@Entity
public final class CountryTimezone {
    private @Id @GeneratedValue long id;
    private long countryId, timezoneId;

    public CountryTimezone() {}
    public CountryTimezone(long countryId, long timezoneId) {
        this.countryId = countryId;
        this.timezoneId = timezoneId;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public long getCountryId() {
        return countryId;
    }
    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public long getTimezoneId() {
        return timezoneId;
    }
    public void setTimezoneId(long timezoneId) {
        this.timezoneId = timezoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryTimezone value)) return false;
        return Objects.equals(this.id, value.id) &&
                Objects.equals(this.countryId, value.countryId) &&
                Objects.equals(this.timezoneId, value.timezoneId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.countryId, this.timezoneId);
    }
    @Override
    public String toString() {
        return String.format("Country{id=%s, name=%s, language=%s}",
                this.id, this.countryId, this.timezoneId);
    }
}