package be.darkshark.parkshark.domain.entity.util;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    public Address() {
    }

    public Address(String street, String houseNumber, String postalCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return street + " " + houseNumber
                + "\n" + postalCode + ", " + city;
    }
}
