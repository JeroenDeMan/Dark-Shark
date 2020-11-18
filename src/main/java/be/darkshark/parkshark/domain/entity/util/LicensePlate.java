package be.darkshark.parkshark.domain.entity.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LicensePlate {
    @Column(name = "license_number")
    private  String licenseNumber;
    @Column(name = "license_country")
    private  String licenseCountry;

    public LicensePlate(String licenseNumber, String licenseCountry) {
        this.licenseNumber = licenseNumber;
        this.licenseCountry = licenseCountry;
    }

    public LicensePlate() {

    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getLicenseCountry() {
        return licenseCountry;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(licenseNumber, that.licenseNumber) &&
                Objects.equals(licenseCountry, that.licenseCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber, licenseCountry);
    }
}
