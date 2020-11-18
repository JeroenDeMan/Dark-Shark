package be.darkshark.parkshark.domain.entity.person;

import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;

import javax.persistence.*;

@Entity
public class Employee extends Person {

    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    private long id;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Address address, PhoneNumber phoneNumber, MailAddress mailAddress) {
        super(firstName, lastName, address, phoneNumber, mailAddress);
    }

    public Employee(long id, String firstName, String lastName, Address address, PhoneNumber phoneNumber, MailAddress mailAddress) {
        super(firstName, lastName, address, phoneNumber, mailAddress);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
