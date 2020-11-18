package be.darkshark.parkshark.api.dto.person;

import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.domain.entity.util.LicensePlate;


public class CreateMemberDTO extends PersonDTO {

    private LicensePlateDTO licensePlate;
    private String memberShipLevel;


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


}
