package com.chabbay.dataobjects.objects;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * definition of the Adresse entity
 *
 * @author Linus Englert
 */
@Entity
public class Adresse extends Data {
    private String strasse, hausnummer, postleitzahl, ort, adresszusatz;

    public Adresse() {}
    public Adresse(String strasse, String hausnummer, String postleitzahl, String ort, String adresszusatz) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.adresszusatz = adresszusatz;
    }

    public String getStrasse() {
        return strasse;
    }
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }
    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }
    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getAdresszusatz() {
        return adresszusatz;
    }
    public void setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresse value)) return false;
        return Objects.equals(this.id, value.id) && Objects.equals(this.strasse, value.strasse) &&
                Objects.equals(this.hausnummer, value.hausnummer) &&
                Objects.equals(this.postleitzahl, value.postleitzahl) &&
                Objects.equals(this.ort, value.ort) &&
                Objects.equals(this.adresszusatz, value.adresszusatz);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.strasse, this.hausnummer, this.postleitzahl,
                this.ort, this.adresszusatz);
    }
    @Override
    public String toString() {
        return String.format("Adresse{id=%s, strasse=%s, hausnummer=%s, postleitzahl=%s, ort=%s, adresszusatz=%s}",
                this.id, this.strasse, this.hausnummer, this.postleitzahl, this.ort, this.adresszusatz);
    }
}