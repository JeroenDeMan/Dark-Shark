package be.darkshark.parkshark.domain.entity;

import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;

import javax.persistence.*;

@Entity
public class ParkingLot {
    @Id
    @SequenceGenerator(name = "parkinglot_seq", sequenceName = "parkinglot_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkinglot_seq")
    private long id;


    @Column(name = "name")
    private String name;

    @Enumerated
    @Column(name = "category")
    private ParkingCategory parkingCategory;

    @Column(name = "capacity")
    private int capacity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contact_person")
    private Employee contactPerson;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Column(name = "price_per_hour")
    private double pricePerHour;




}
