package be.darkshark.parkshark.domain.entity.util;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String houseNumber;
    private int postalCode;
    private String city;

    public Address() {
    }

    public Address(String street, String houseNumber, int postalCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return street + " " + houseNumber
                + "\n" + postalCode + ", " + city;
    }
}
