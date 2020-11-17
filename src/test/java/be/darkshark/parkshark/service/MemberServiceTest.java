package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.person.MemberDTO;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.*;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberRepository mockRepository;
    MemberMapper mockMapper;
    MemberService memberService;
    MemberDTO memberDTO;
    Address address;
    PhoneNumber phoneNumber;
    LicensePlate licensePlate;


    @BeforeEach
    public void setUp() {
        mockRepository = Mockito.mock(MemberRepository.class);
        mockMapper = Mockito.mock(MemberMapper.class);
        memberService = new MemberService(mockRepository, mockMapper);

        address = new Address("some street", "1", 9160, "Lokeren");
        phoneNumber = new PhoneNumber("+32", 477889911);
        licensePlate = new LicensePlate("010-aba", "Belgium");
//        memberEntity = new Member(1L, "Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"),
//                licensePlate, MemberShipLevel.BRONZE);

        memberDTO = new MemberDTO();
        memberDTO.setId(1);
        memberDTO.setAddress(new AddressDTO("some street", "1", 9160, "Lokeren"));
        memberDTO.setPhoneNumber(new PhoneNumberDTO("+32", 477889911));
        memberDTO.setMailAddress("some@Mail.com");
        memberDTO.setLicensePlate(new LicensePlateDTO("010-aba", "Belgium"));
        memberDTO.setMemberShipLevel("Bronze");
    }

//    @Test
//    public void whenCreatingAMember_repositoryMethodSaveIsCalledOnce() {
//        memberService.createMember(memberDTO);
//        Member memberEntity =  new Member(1L, "Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"),
//                licensePlate, MemberShipLevel.BRONZE);
//        Mockito.when(mockMapper.toEntity(memberDTO)).thenReturn(memberEntity);
//
//        Mockito.verify(mockRepository, Mockito.times(1)).save(memberEntity);
//
//    }

    @Test
    public void whenCreatingAMember_mapperMethodToEntityIsCalledOnce() {
        memberService.createMember(memberDTO);

        Mockito.verify(mockMapper, Mockito.times(1)).toEntity(memberDTO);
    }

    @Test
    public void whenCreatingAMember_mapperMethodToEntityIsCalledOnceAndRepositoryMethodSaveIsCalledOnce() {

    }


}
