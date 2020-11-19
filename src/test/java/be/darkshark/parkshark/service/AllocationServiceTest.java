package be.darkshark.parkshark.service;


import be.darkshark.parkshark.api.dto.allocation.CreateAllocationDTO;
import be.darkshark.parkshark.api.dto.allocation.GetAllocationDTO;
import be.darkshark.parkshark.domain.entity.Allocation;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingCategory;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.*;
import be.darkshark.parkshark.domain.repository.AllocationRepository;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.domain.repository.ParkingLotRepository;
import be.darkshark.parkshark.service.mapper.AllocationMapper;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AllocationServiceTest {

    AllocationRepository mockAllocationRepository;
    AllocationMapper mockAllocationMapper;
    AllocationService allocationService;

    MemberRepository mockMemberRepository;
    MemberMapper mockMemberMapper;

    ParkingLotRepository mockParkingLotRepository;

    Employee mockEmployee;
    Division mockDivision;

    CreateAllocationDTO createAllocationDTO;
    Address address;
    PhoneNumber phoneNumber;
    LicensePlate licensePlate;
    Member memberEntity;
    ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        mockAllocationMapper = Mockito.mock(AllocationMapper.class);
        mockAllocationRepository = Mockito.mock(AllocationRepository.class);
        mockMemberRepository = Mockito.mock(MemberRepository.class);
        mockMemberMapper = Mockito.mock(MemberMapper.class);
        mockParkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        mockEmployee = Mockito.mock(Employee.class);
        mockDivision = Mockito.mock(Division.class);

        allocationService = new AllocationService(mockAllocationRepository,
                mockAllocationMapper, mockMemberRepository, mockMemberMapper, mockParkingLotRepository);

        createAllocationDTO = new CreateAllocationDTO();
        createAllocationDTO.setMemberId(1L);
        createAllocationDTO.setLicencePlate("010-aba");
        createAllocationDTO.setParkingLotId(1L);

        address = new Address("some street", "1", 9160, "Lokeren");
        phoneNumber = new PhoneNumber("+32", 477889911);
        licensePlate = new LicensePlate("010-aba", "Belgium");
        memberEntity = new Member("Jeroen", "De Man", address, phoneNumber,new MailAddress("some@Mail.com"),
                licensePlate, MemberShipLevel.BRONZE);

        parkingLot = new ParkingLot("parkingLot", ParkingCategory.ABOVE_GROUND_BUILDING, 5, mockEmployee, address, 33.33, mockDivision);
    }

    @Test
    public void whenCreatingAllocation_ASpecificOrderOfRepositoriesIsCalled() {
        Allocation allocation = new Allocation();
        GetAllocationDTO getAllocationDTO = new GetAllocationDTO();
        Optional<Member> memberOptional = Optional.of(memberEntity);
        Optional<ParkingLot> parkingLotOptional = Optional.of(parkingLot);

        Mockito.when(mockMemberRepository.findById(1L)).thenReturn(memberOptional);
        Mockito.when(mockParkingLotRepository.findById(1L)).thenReturn(parkingLotOptional);
        Mockito.when(mockAllocationMapper.toEntity(createAllocationDTO)).thenReturn(allocation);
        //Mockito.when(mockAllocationRepository.findByMember_IdAndEndTimeIsNull(1L)).thenReturn(null).thenReturn(allocation);
        Mockito.when(mockAllocationMapper.getAllocationDTO(allocation)).thenReturn(getAllocationDTO);

        allocationService.createAllocation(createAllocationDTO);

        InOrder expectedFlow = Mockito.inOrder(mockMemberRepository, mockParkingLotRepository, mockAllocationRepository, mockAllocationRepository,
                mockAllocationMapper,mockAllocationRepository, mockAllocationMapper, mockMemberMapper);

        expectedFlow.verify(mockMemberRepository).findById(1L);
        expectedFlow.verify(mockParkingLotRepository).findById(1L);
        expectedFlow.verify(mockAllocationRepository).findByMember_IdAndEndTimeIsNull(1L);
        expectedFlow.verify(mockAllocationRepository).countAllByParkingLot_IdAndEndTimeIsNull(1L);
        expectedFlow.verify(mockAllocationMapper).toEntity(createAllocationDTO);
        expectedFlow.verify(mockAllocationRepository).save(allocation);
        expectedFlow.verify(mockAllocationMapper).getAllocationDTO(allocation);
        expectedFlow.verify(mockMemberMapper).toGetMembersDTO(memberEntity);
    }



}
