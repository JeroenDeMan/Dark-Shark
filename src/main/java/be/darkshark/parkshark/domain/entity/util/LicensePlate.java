package be.darkshark.parkshark.domain.entity.util;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LicensePlate {
    private  String licenseNumber;
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

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setLicenseCountry(String licenseCountry) {
        this.licenseCountry = licenseCountry;
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
