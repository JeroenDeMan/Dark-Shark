package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.person.CreateMemberDTO;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.api.dto.util.LicensePlateDTO;
import be.darkshark.parkshark.api.dto.util.PhoneNumberDTO;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.*;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

class MemberServiceTest {
    MemberRepository mockRepository;
    MemberMapper mockMapper;
    MemberService memberService;
    CreateMemberDTO createMemberDTO;
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

        createMemberDTO = new CreateMemberDTO();
        createMemberDTO.setId(1);
        createMemberDTO.setAddress(new AddressDTO("some street", "1", 9160, "Lokeren"));
        createMemberDTO.setPhoneNumber(new PhoneNumberDTO("+32", 477889911));
        createMemberDTO.setMailAddress("some@Mail.com");
        createMemberDTO.setLicensePlate(new LicensePlateDTO("010-aba", "Belgium"));
        createMemberDTO.setMemberShipLevel("Bronze");
    }

    @Test
    public void whenCreatingAMember_repositoryMethodSaveIsCalledOnce() {
        Member memberEntity =  new Member(1L, "Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"),
                licensePlate, MemberShipLevel.BRONZE);
        Mockito.when(mockMapper.toEntity(createMemberDTO)).thenReturn(memberEntity);

        memberService.createMember(createMemberDTO);

        Mockito.verify(mockRepository, Mockito.times(1)).save(memberEntity);

    }

    @Test
    public void whenCreatingAMember_mapperMethodToEntityIsCalledOnce() {
        memberService.createMember(createMemberDTO);

        Mockito.verify(mockMapper, Mockito.times(1)).toEntity(createMemberDTO);
    }

    @Test
    public void whenCreatingAMember_theRegistrationDateIsSetToCurrentDate() {
        Member member = new Member(1L, "Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"), licensePlate, MemberShipLevel.BRONZE);
        Assertions.assertEquals(LocalDate.now(), member.getRegistrationDate());
    }

    @Test
    public void whenRequestingAllMembers_repositoryMethodIsCalledOnce() {
        memberService.getAllMembers();

        Mockito.verify(mockRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void whenRequestingAllMembers_listOfMemberDTOIsSize1() {
        Member memberEntity =  new Member(1L, "Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"),
                licensePlate, MemberShipLevel.BRONZE);
        Iterable<Member> iterable = List.of(memberEntity);
        Mockito.when(mockRepository.findAll()).thenReturn(iterable);

        Assertions.assertEquals(1, memberService.getAllMembers().size());
    }




}
