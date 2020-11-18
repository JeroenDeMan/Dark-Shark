package be.darkshark.parkshark.domain.entity;

import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;

import javax.persistence.*;

@Entity
@Table(name = "parkinglot")
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "division")
    private Division division;

    public ParkingLot(String name, ParkingCategory parkingCategory, int capacity, Employee contactPerson, Address address, double pricePerHour, Division division) {
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.address = address;
        this.pricePerHour = pricePerHour;
        this.division = division;
    }

    public ParkingLot(long id, String name, ParkingCategory parkingCategory, int capacity, Employee contactPerson, Address address, double pricePerHour, Division division) {
        this.id = id;
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.address = address;
        this.pricePerHour = pricePerHour;
        this.division = division;
    }

    public ParkingLot() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParkingCategory getParkingCategory() {
        return parkingCategory;
    }

    public void setParkingCategory(ParkingCategory parkingCategory) {
        this.parkingCategory = parkingCategory;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Employee getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Employee contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
