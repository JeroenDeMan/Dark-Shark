package be.darkshark.parkshark.api.dto.person;

import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;

public class GetMembersDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String licensePlateNumber;
    private PhoneNumberDTO phoneNumberDTO;
    private String mailAddress;
    private String registrationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public PhoneNumberDTO getPhoneNumberDTO() {
        return phoneNumberDTO;
    }

    public void setPhoneNumberDTO(PhoneNumberDTO phoneNumberDTO) {
        this.phoneNumberDTO = phoneNumberDTO;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
