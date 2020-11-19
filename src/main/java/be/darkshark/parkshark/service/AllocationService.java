package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.allocation.CreateAllocationDTO;
import be.darkshark.parkshark.api.dto.allocation.GetAllocationDTO;
import be.darkshark.parkshark.domain.entity.Allocation;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.repository.AllocationRepository;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.domain.repository.ParkingLotRepository;
import be.darkshark.parkshark.service.mapper.AllocationMapper;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AllocationService {
    private final Logger log = LoggerFactory.getLogger(AllocationService.class);
    private final AllocationRepository allocationRepository;
    private final AllocationMapper allocationMapper;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public AllocationService(AllocationRepository allocationRepository, AllocationMapper allocationMapper, MemberRepository memberRepository, MemberMapper memberMapper, ParkingLotRepository parkingLotRepository) {
        this.allocationRepository = allocationRepository;
        this.allocationMapper = allocationMapper;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.parkingLotRepository = parkingLotRepository;
    }

    public GetAllocationDTO createAllocation(CreateAllocationDTO createAllocationDTO) {
        Member member = checkIfMemberExists(createAllocationDTO.getMemberId());
        ParkingLot parkingLot = checkIfParkingLotExists(createAllocationDTO.getParkingLotId());

        if (checkIfMemberActiveAllocation(createAllocationDTO.getMemberId())
                && checkIfLicencePlateIsCorrect(createAllocationDTO.getLicencePlate(), member)
                && checkIfParkingSpotAvailable(parkingLot)) {
            Allocation allocation = allocationMapper.toEntity(createAllocationDTO);
            allocation.setMember(member);
            allocation.setParkingLot(parkingLot);
            allocationRepository.save(allocation);
            GetAllocationDTO result = allocationMapper.getAllocationDTO(allocationRepository.findByMember_IdAndEndTimeIsNull(member.getId()));
            result.setMember(memberMapper.toGetMembersDTO(member));
            return result;
        }
        throw new IllegalArgumentException("Allocation not created, bad luck !");
    }

    private boolean checkIfMemberActiveAllocation(long memberId) {
        if (allocationRepository.findByMember_IdAndEndTimeIsNull(memberId) != null) {
            return true;
        }
        return false;
    }

    private Member checkIfMemberExists(long memberId) {
        Optional<Member> optional = memberRepository.findById(memberId);
        if (optional.isEmpty()) {
            log.error("No member found with this Id " + memberId);
            throw new EntityNotFoundException("No member found with this Id" + " " + memberId);
        }
        return optional.get();
    }

    private ParkingLot checkIfParkingLotExists(long parkingLotId) {
        Optional<ParkingLot> optional = parkingLotRepository.findById(parkingLotId);
        if (optional.isEmpty()) {
            log.error("No parking lot found with this Id " + parkingLotId);
            throw new EntityNotFoundException("No parking lot found with this Id" + " " + parkingLotId);
        }
        return optional.get();
    }

    private boolean checkIfLicencePlateIsCorrect(String licencePlate, Member member) {
        return member.getLicensePlate().getLicenseNumber().equals(licencePlate);
    }

    private boolean checkIfParkingSpotAvailable(ParkingLot parkingLot) {
        return (allocationRepository.countAllByParkingLot_IdAndEndTimeIsNull(parkingLot.getId()) < parkingLot.getCapacity());
    }
}
