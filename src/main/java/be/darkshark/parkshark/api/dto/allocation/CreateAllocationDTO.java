package be.darkshark.parkshark.api.dto.allocation;

public class CreateAllocationDTO {

        private String licencePlate;

        private long memberId;

        private long parkingLotId;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}



