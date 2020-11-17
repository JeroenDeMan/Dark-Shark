package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.person.MemberDTO;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.*;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class MemberMapper {

    public Member toEntity (MemberDTO memberDTO) {
        return new Member(
                memberDTO.getId(),
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                new Address(memberDTO.getAddress().getStreet(),
                        memberDTO.getAddress().getHouseNumber(),
                        memberDTO.getAddress().getPostalCode(),
                        memberDTO.getAddress().getCity()),
                new PhoneNumber(memberDTO.getPhoneNumber().getCountryCode(),
                        memberDTO.getPhoneNumber().getPhoneNumber()),
                new MailAddress(memberDTO.getMailAddress()),
                new LicensePlate(memberDTO.getLicensePlate().getLicenseNumber(), memberDTO.getLicensePlate().getLicenseCountry()),
                MemberShipLevel.valueOf(memberDTO.getMemberShipLevel().toUpperCase())
        );

    }

    public MemberDTO toDTO(Member memberEntity) {
        AddressDTO addressDTO = new AddressDTO(memberEntity.getAddress().getStreet(),
                memberEntity.getAddress().getHouseNumber(),
                memberEntity.getAddress().getPostalCode(),
                memberEntity.getAddress().getCity());

        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO(memberEntity.getPhoneNumber().getCountryCode(),
                memberEntity.getPhoneNumber().getPhoneNumber());

        LicensePlateDTO licensePlateDTO = new LicensePlateDTO(memberEntity.getLicensePlate().getLicenseNumber(),
                memberEntity.getLicensePlate().getLicenseCountry());

        MemberDTO result = new MemberDTO();
        result.setId(memberEntity.getId());
        result.setAddress(addressDTO);
        result.setPhoneNumber(phoneNumberDTO);
        result.setMailAddress(memberEntity.getMailAddress().getMailAddress());
        result.setLicensePlate(licensePlateDTO);
        result.setMemberShipLevel(memberEntity.getMemberShipLevel().toString());

        return result;
    }
}
