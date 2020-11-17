package be.darkshark.parkshark.domain.entity.person;

import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    private long id;

    private String firstName;

    private String lastName;

    @Embedded
    private  Address address;

    @Embedded
    private  PhoneNumber phoneNumber;

    @Embedded
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
