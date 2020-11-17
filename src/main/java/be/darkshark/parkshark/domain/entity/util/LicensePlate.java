package be.darkshark.parkshark.domain.entity.util;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LicensePlate {
    private  String number;
    private  String country;

    public LicensePlate(String number, String country) {
        this.number = number;
        this.country = country;
    }

    public LicensePlate() {

    }

    public String getNumber() {
        return number;
    }

    public String getCountry() {
        return country;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, country);
    }
}
