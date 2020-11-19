package be.darkshark.parkshark.api.dto.allocation;

import be.darkshark.parkshark.api.dto.person.GetMembersDTO;

public class GetAllocationDTO {
    private long allocationId;
    private String startTime;
    private String endTime;
    private long parkingLotId;
    private String parkingLotName;
    private GetMembersDTO member;
    private String status;


    public long getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(long allocationId) {
        this.allocationId = allocationId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public GetMembersDTO getMember() {
        return member;
    }

    public void setMember(GetMembersDTO member) {
        this.member = member;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
