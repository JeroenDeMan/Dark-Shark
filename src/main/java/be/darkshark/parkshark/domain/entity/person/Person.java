package be.darkshark.parkshark.domain.entity.person;

import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.MemberShipLevel;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;

import javax.persistence.*;

@MappedSuperclass
public abstract class Person {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private  Address address;

    @Embedded
    private  PhoneNumber phoneNumber;

    @Embedded
    private  MailAddress mailAddress;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public MailAddress getMailAddress() {
        return mailAddress;
    }

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
