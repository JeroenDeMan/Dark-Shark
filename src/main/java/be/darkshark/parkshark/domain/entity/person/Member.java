package be.darkshark.parkshark.domain.entity.person;

import be.darkshark.parkshark.domain.entity.util.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "member")
public class Member extends Person {

    @Id
    @Column(name = "id")
    private long id;
    @Embedded
    private LicensePlate licensePlate;

    @Enumerated
    @Column(name = "member_ship_level")
    private MemberShipLevel memberShipLevel;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public Member() {
        registrationDate = LocalDate.now();
    }

    public Member(Long id, String firstName, String lastName, Address address, PhoneNumber phoneNumber,
                  MailAddress mailAddress, LicensePlate licensePlate, MemberShipLevel memberShipLevel) {
        super(firstName, lastName, address, phoneNumber, mailAddress);
        this.id = id;
        this.licensePlate = licensePlate;
        this.memberShipLevel = memberShipLevel;
        registrationDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public MemberShipLevel getMemberShipLevel() {
        return memberShipLevel;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id &&
                Objects.equals(licensePlate, member.licensePlate) &&
                memberShipLevel == member.memberShipLevel &&
                Objects.equals(registrationDate, member.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, memberShipLevel, registrationDate);
    }
}
