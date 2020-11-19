package be.darkshark.parkshark.service;


import be.darkshark.parkshark.domain.repository.AllocationRepository;
import be.darkshark.parkshark.service.mapper.AllocationMapper;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class AllocationServiceTest {

    AllocationRepository mockAllocationRepository;
    AllocationMapper mockAllocationMapper;
    AllocationService allocationService;

    @BeforeEach
    public void setUp() {

    }

//    public GetAllocationDTO createAllocation(CreateAllocationDTO createAllocationDTO) {
//        Member member = checkIfMemberExists(createAllocationDTO.getMemberId());
//        ParkingLot parkingLot = checkIfParkingLotExists(createAllocationDTO.getParkingLotId());
//
//        if (checkIfMemberActiveAllocation(createAllocationDTO.getMemberId())
//                && checkIfLicencePlateIsCorrect(createAllocationDTO.getLicencePlate(), member)
//                && checkIfParkingSpotAvailable(parkingLot)) {
//            Allocation allocation = allocationMapper.toEntity(createAllocationDTO);
//            allocation.setMember(member);
//            allocation.setParkingLot(parkingLot);
//            allocationRepository.save(allocation);
//            GetAllocationDTO result = allocationMapper.getAllocationDTO(allocationRepository.findByMember_IdAndEndTimeIsNull(member.getId()));
//            result.setMember(memberMapper.toGetMembersDTO(member));
//            return result;
//        }
//        throw new IllegalArgumentException("Allocation not created, bad luck !");
//    }

}
