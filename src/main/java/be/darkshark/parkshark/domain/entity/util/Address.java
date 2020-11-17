package be.darkshark.parkshark.domain.entity.util;

public class Address {
    private final String street;
    private final String number;
    private final String postalCode;
    private final String city;

    public Address(String street, String number, String postalCode, String city) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return street + " " + number
                + "\n" + postalCode + ", " + city;
    }
}
