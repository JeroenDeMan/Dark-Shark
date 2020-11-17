package be.darkshark.parkshark.api.dto.util;

public class PhoneNumberDTO {
    private String countryCode;
    private int phoneNumber;

    public PhoneNumberDTO() {
    }

    public PhoneNumberDTO(String countryCode, int phoneNumber) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
