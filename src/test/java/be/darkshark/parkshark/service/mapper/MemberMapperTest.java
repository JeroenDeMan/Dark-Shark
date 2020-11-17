package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.person.CreateMemberDTO;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberMapperTest {
    MemberMapper memberMapper;
    Member memberEntity;
    CreateMemberDTO createMemberDTO;

    @BeforeEach
    public void setUp() {
        memberMapper = new MemberMapper();
        Address address = new Address("some street", "1", 9160, "Lokeren");
        PhoneNumber phoneNumber = new PhoneNumber("+32", 477889911);
        LicensePlate licensePlate = new LicensePlate("010-aba", "Belgium" );
        memberEntity = new Member(1L, "Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"),
                licensePlate, MemberShipLevel.BRONZE);

        createMemberDTO = new CreateMemberDTO();
        createMemberDTO.setId(1);
        createMemberDTO.setAddress(new AddressDTO("some street", "1", 9160, "Lokeren"));
        createMemberDTO.setPhoneNumber(new PhoneNumberDTO("+32", 477889911));
        createMemberDTO.setMailAddress("some@Mail.com");
        createMemberDTO.setLicensePlate(new LicensePlateDTO("010-aba", "Belgium" ));
        createMemberDTO.setMemberShipLevel("Bronze");
    }

    @Test
    public void whenGivenMemberDTO_memberEntityIsCreated() {


        Assertions.assertEquals(memberEntity, memberMapper.toEntity(createMemberDTO));
    }

    @Test
    public void whenGivenMemberEntity_memberDTOIsCreated() {

        Assertions.assertEquals(1, memberMapper.toCreateDTO(memberEntity).getId());

    }

}
