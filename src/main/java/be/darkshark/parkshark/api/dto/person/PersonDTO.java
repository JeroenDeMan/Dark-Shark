package be.darkshark.parkshark.api.dto.person;

import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;
import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;


public abstract class PersonDTO {
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private PhoneNumberDTO phoneNumber;
    private String mailAddress;

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public PhoneNumberDTO getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberDTO phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
