package be.darkshark.parkshark.api.dto.parkinglot;

import be.darkshark.parkshark.api.dto.util.AddressDTO;

public class DetailedParkingLotDto {
    private long id;
    private String name;
    private String parkingCategory;
    private int capacity;
    private long contactPersonId;
    private AddressDTO address;
    private double pricePerHour;
    private long divisionId;

    public DetailedParkingLotDto(long id, String name, String parkingCategory, int capacity, long contactPersonId, AddressDTO address, double pricePerHour, long divisionId) {
        this.id = id;
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPersonId = contactPersonId;
        this.address = address;
        this.pricePerHour = pricePerHour;
        this.divisionId = divisionId;
    }

    public DetailedParkingLotDto() {
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

    public String getParkingCategory() {
        return parkingCategory;
    }

    public void setParkingCategory(String parkingCategory) {
        this.parkingCategory = parkingCategory;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(long contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(long divisionId) {
        this.divisionId = divisionId;
    }
}
