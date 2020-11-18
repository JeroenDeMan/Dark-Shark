package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.person.CreateMemberDTO;
import be.darkshark.parkshark.api.dto.person.GetMembersDTO;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.*;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity (CreateMemberDTO createMemberDTO) {
        return new Member(
                createMemberDTO.getFirstName(),
                createMemberDTO.getLastName(),
                new Address(createMemberDTO.getAddress().getStreet(),
                        createMemberDTO.getAddress().getHouseNumber(),
                        createMemberDTO.getAddress().getPostalCode(),
                        createMemberDTO.getAddress().getCity()),
                new PhoneNumber(createMemberDTO.getPhoneNumber().getCountryCode(),
                        createMemberDTO.getPhoneNumber().getPhoneNumber()),
                new MailAddress(createMemberDTO.getMailAddress()),
                new LicensePlate(createMemberDTO.getLicensePlate().getLicenseNumber(), createMemberDTO.getLicensePlate().getLicenseCountry()),
                MemberShipLevel.valueOf(createMemberDTO.getMemberShipLevel().toUpperCase())
        );

    }

    public CreateMemberDTO toCreateDTO(Member memberEntity) {
        AddressDTO addressDTO = new AddressDTO(memberEntity.getAddress().getStreet(),
                memberEntity.getAddress().getHouseNumber(),
                memberEntity.getAddress().getPostalCode(),
                memberEntity.getAddress().getCity());

        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO(memberEntity.getPhoneNumber().getCountryCode(),
                memberEntity.getPhoneNumber().getPhoneNumber());

        LicensePlateDTO licensePlateDTO = new LicensePlateDTO(memberEntity.getLicensePlate().getLicenseNumber(),
                memberEntity.getLicensePlate().getLicenseCountry());

        CreateMemberDTO result = new CreateMemberDTO();
        result.setFirstName(memberEntity.getFirstName());
        result.setLastName(memberEntity.getLastName());
        result.setAddress(addressDTO);
        result.setPhoneNumber(phoneNumberDTO);
        result.setMailAddress(memberEntity.getMailAddress().getMailAddress());
        result.setLicensePlate(licensePlateDTO);
        result.setMemberShipLevel(memberEntity.getMemberShipLevel().toString());


        return result;
    }

    public GetMembersDTO toGetMembersDTO (Member memberEntity) {

        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO(memberEntity.getPhoneNumber().getCountryCode(),
                memberEntity.getPhoneNumber().getPhoneNumber());

        GetMembersDTO result = new GetMembersDTO();
        result.setId(memberEntity.getId());
        result.setFirstName(memberEntity.getFirstName());
        result.setLastName(memberEntity.getLastName());
        result.setPhoneNumberDTO(phoneNumberDTO);
        result.setMailAddress(memberEntity.getMailAddress().getMailAddress());
        result.setLicensePlateNumber(memberEntity.getLicensePlate().getLicenseNumber());
        result.setRegistrationDate(memberEntity.getRegistrationDate().toString());

        return result;
    }
}
