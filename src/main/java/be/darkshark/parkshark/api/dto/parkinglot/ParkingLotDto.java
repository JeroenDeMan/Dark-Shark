package be.darkshark.parkshark.api.dto.parkinglot;

import be.darkshark.parkshark.api.dto.util.AddressDTO;

public class ParkingLotDto {
    private long id;
    private String name;
//    private String parkingCategory;
    private int capacity;
//    private long contactPersonId;
    private String ContactPersonEmail;
    private String ContactPersonPhone;
//    private AddressDTO address;
//    private double pricePerHour;
//    private long divisionId;

    public ParkingLotDto() {
    }

    public ParkingLotDto(long id, String name, int capacity, String contactPersonEmail, String contactPersonPhone) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        ContactPersonEmail = contactPersonEmail;
        ContactPersonPhone = contactPersonPhone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getContactPersonEmail() {
        return ContactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        ContactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonPhone() {
        return ContactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        ContactPersonPhone = contactPersonPhone;
    }
}
