package be.darkshark.parkshark.api.dto.util;

public class LicensePlateDTO {
    private String licenseNumber;
    private String licenseCountry;

    public LicensePlateDTO() {
    }

    public LicensePlateDTO(String licenseNumber, String licenseCountry) {
        this.licenseNumber = licenseNumber;
        this.licenseCountry = licenseCountry;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseCountry() {
        return licenseCountry;
    }

    public void setLicenseCountry(String licenseCountry) {
        this.licenseCountry = licenseCountry;
    }
}
