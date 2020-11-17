package be.darkshark.parkshark.domain.entity.person;

import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;

public abstract class Person {
    private  String firstName;
    private  String lastName;
    private  Address address;
    private  PhoneNumber phoneNumber;
    private  MailAddress mailAddress;

    public Person() {
    }

    public Person(String firstName, String lastName, Address address, PhoneNumber phoneNumber, MailAddress mailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }
}
