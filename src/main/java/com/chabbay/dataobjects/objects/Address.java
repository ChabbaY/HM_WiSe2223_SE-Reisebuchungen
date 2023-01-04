package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * definition of the Address entity
 *
 * @author Linus Englert
 */
@Entity
public final class Address {
    private @Id @GeneratedValue long id;
    private String street, houseNumber, postcode, city, addressSupplement;

    //foreign key
    private long addressInformationId, countryId, timezoneId;

    public Address() {}
    public Address(String street, String houseNumber, String postcode, String city, String addressSupplement,
                   long addressInformationId, long countryId, long timezoneId) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.addressSupplement = addressSupplement;
        this.addressInformationId = addressInformationId;
        this.countryId = countryId;
        this.timezoneId = timezoneId;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String strasse) {
        this.street = strasse;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(String hausnummer) {
        this.houseNumber = hausnummer;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postleitzahl) {
        this.postcode = postleitzahl;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String ort) {
        this.city = ort;
    }

    public String getAddressSupplement() {
        return addressSupplement;
    }
    public void setAddressSupplement(String adresszusatz) {
        this.addressSupplement = adresszusatz;
    }

    public long getAddressInformationId() {
        return addressInformationId;
    }
    public void setAddressInformationId(long addressInformationId) {
        this.addressInformationId = addressInformationId;
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
        if (!(o instanceof Address value)) return false;
        return Objects.equals(this.id, value.id) && Objects.equals(this.street, value.street) &&
                Objects.equals(this.houseNumber, value.houseNumber) &&
                Objects.equals(this.postcode, value.postcode) &&
                Objects.equals(this.city, value.city) &&
                Objects.equals(this.addressSupplement, value.addressSupplement) &&
                Objects.equals(this.addressInformationId, value.addressInformationId) &&
                Objects.equals(this.countryId, value.countryId) &&
                Objects.equals(this.timezoneId, value.timezoneId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.street, this.houseNumber, this.postcode,
                this.city, this.addressSupplement, this.addressInformationId, countryId, timezoneId);
    }
    @Override
    public String toString() {
        return String.format("Address{id=%s, street=%s, houseNumber=%s, postcode=%s, city=%s, addressSupplement=%s," +
                        "addressInformationId=%d, countryId=%s, timezoneId=%s}",
                this.id, this.street, this.houseNumber, this.postcode, this.city, this.addressSupplement,
                this.addressInformationId, this.countryId, this.timezoneId);
    }
}