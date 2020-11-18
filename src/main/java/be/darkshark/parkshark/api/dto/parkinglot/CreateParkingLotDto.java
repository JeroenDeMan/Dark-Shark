package be.darkshark.parkshark.api.dto.parkinglot;


import be.darkshark.parkshark.api.dto.util.AddressDTO;

public class CreateParkingLotDto {
    private String name;
    private String parkingCategory;
    private int capacity;
    private long contactPersonId;
    private AddressDTO address;
    private double pricePerHour;
    private long divisionId;

    public CreateParkingLotDto() {
    }

    public CreateParkingLotDto(String name, String parkingCategory, int capacity, long contactPersonId, AddressDTO address, double pricePerHour, long divisionId) {
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPersonId = contactPersonId;
        this.address = address;
        this.pricePerHour = pricePerHour;
        this.divisionId = divisionId;
    }

    public String getName() {
        return name;
    }

    public String getParkingCategory() {
        return parkingCategory;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getContactPersonId() {
        return contactPersonId;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public long getDivisionId() {
        return divisionId;
    }
}
