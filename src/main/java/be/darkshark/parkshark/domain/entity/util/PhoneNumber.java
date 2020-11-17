package be.darkshark.parkshark.domain.entity.util;

import java.util.Objects;

public class PhoneNumber {
    private final String countryCode;
    private final int number;

    public PhoneNumber(String countryCode, int number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return countryCode + " " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return number == that.number &&
                Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }
}
