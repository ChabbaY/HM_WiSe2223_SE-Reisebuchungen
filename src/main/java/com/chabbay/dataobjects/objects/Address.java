package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Address entity
 *
 * @author Linus Englert
 */
@Entity
public class Address extends Data {
    private String street, houseNumber, postcode, city, addressSupplement;

    //foreign key
    private long addressInformationId;

    public Address() {}
    public Address(String street, String houseNumber, String postcode, String city, String addressSupplement,
                   long addressInformationId) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.addressSupplement = addressSupplement;
        this.addressInformationId = addressInformationId;
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

    public Long getAddressInformationId() {
        return addressInformationId;
    }
    public void setAddressInformationId(Long addressInformationId) {
        this.addressInformationId = addressInformationId;
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
                Objects.equals(this.addressInformationId, value.addressInformationId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.street, this.houseNumber, this.postcode,
                this.city, this.addressSupplement, this.addressInformationId);
    }
    @Override
    public String toString() {
        return String.format("Address{id=%s, street=%s, houseNumber=%s, postcode=%s, city=%s, addressSupplement=%s, addressInformationId=%d}",
                this.id, this.street, this.houseNumber, this.postcode, this.city, this.addressSupplement, this.addressInformationId);
    }
}