package be.darkshark.parkshark.api.dto.person;

import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.domain.entity.util.LicensePlate;


public class MemberDTO extends PersonDTO {

    private long id;
    private LicensePlateDTO licensePlate;
    private String memberShipLevel;
    private String registrationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LicensePlateDTO getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlateDTO licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMemberShipLevel() {
        return memberShipLevel;
    }

    public void setMemberShipLevel(String memberShipLevel) {
        this.memberShipLevel = memberShipLevel;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
