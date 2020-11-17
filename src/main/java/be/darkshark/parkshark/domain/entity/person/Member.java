package be.darkshark.parkshark.domain.entity.person;

import be.darkshark.parkshark.domain.entity.util.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Member extends Person{

    @Id
    private long id;

    @Embedded
    private LicensePlate licensePlate;

    @Enumerated
    private MemberShipLevel memberShipLevel;

    private LocalDate registrationDate;

    public Member() {
        registrationDate = LocalDate.now();
    }

    public Member(String firstName, String lastName, Address address, PhoneNumber phoneNumber, MailAddress mailAddress) {
        super(firstName, lastName, address, phoneNumber, mailAddress);
        registrationDate = LocalDate.now();
    }
}
