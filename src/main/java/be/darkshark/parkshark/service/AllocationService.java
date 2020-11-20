package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.allocation.CreateAllocationDTO;
import be.darkshark.parkshark.api.dto.allocation.GetAllocationDTO;
import be.darkshark.parkshark.domain.entity.Allocation;
import be.darkshark.parkshark.domain.entity.AllocationStatus;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.MemberShipLevel;
import be.darkshark.parkshark.domain.repository.AllocationRepository;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.domain.repository.ParkingLotRepository;
import be.darkshark.parkshark.service.mapper.AllocationMapper;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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

        if (checkIfMemberHasNoAllocation(createAllocationDTO.getMemberId())
                && checkIfLicencePlateIsCorrect(createAllocationDTO.getLicencePlate(), member)
                && checkIfParkingSpotAvailable(parkingLot)) {
            Allocation allocation = allocationMapper.toEntity(createAllocationDTO);
            allocation.setMember(member);
            allocation.setParkingLot(parkingLot);
            allocationRepository.save(allocation);
            GetAllocationDTO result = allocationMapper.getAllocationDTO(allocation);
            result.setMember(memberMapper.toGetMembersDTO(member));
            return result;
        }
        throw new IllegalArgumentException("Allocation not created, bad luck !");
    }

    private boolean checkIfMemberHasNoAllocation(long memberId) {
        if (allocationRepository.findByMember_IdAndEndTimeIsNull(memberId) == null) {
            return true;
        }
        log.warn("Member already has active allocation");
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
        if (member.getMemberShipLevel() == MemberShipLevel.GOLD || member.getLicensePlate().getLicenseNumber().equals(licencePlate)) {
            return true;
        }
        log.warn("License plate does not match registered member license plate");
        return false;
    }

    private boolean checkIfParkingSpotAvailable(ParkingLot parkingLot) {
        if (allocationRepository.countAllByParkingLot_IdAndEndTimeIsNull(parkingLot.getId()) < parkingLot.getCapacity()) {
            return true;
        }
        log.warn("No parking spots available");
        return false;
    }

    public GetAllocationDTO stopAllocation(long allocationId, long memberId) {
        Allocation result = allocationRepository.findByIdAndMember_IdAndEndTimeIsNull(allocationId, memberId);

        if (result == null)
            throw new IllegalArgumentException("No active allocation found with id " + allocationId + " and for member with id " + memberId);
        result.setEndTime();
        return allocationMapper.getAllocationDTO(result);
    }

    public List<GetAllocationDTO> getAllAllocations(Integer limitController, String status, boolean desc) {
        int limit = limitController != null ? limitController.intValue() : 0;
        List<Allocation> allocations = new ArrayList<>();
        Sort.Direction sortDirection = desc ? Sort.Direction.DESC : Sort.Direction.ASC;

        allocations.addAll(getDefaultAllocations(status, sortDirection));

        if (limit < 1) {
            return allocations.stream().map(allocation -> allocationMapper.getAllocationDTO(allocation)).collect(Collectors.toList());
        }

        return allocations.stream()
                .limit(limit)
                .map(allocation -> allocationMapper.getAllocationDTO(allocation))
                .collect(Collectors.toList());
    }

    private List<Allocation> getDefaultAllocations(String status, Sort.Direction sortDirection) {
        if (!status.isEmpty()) {
            try {
                AllocationStatus allocationStatus = AllocationStatus.valueOf(checkIfAllocationStatusIsValid(status));
                return allocationRepository.findAllByStatus(allocationStatus, Sort.by(sortDirection, "startTime"));
            } catch (IllegalArgumentException exception) {
                log.error("Allocation status is not valid");
            }
        }
        return allocationRepository.findAll(Sort.by(sortDirection, "startTime"));

    }

    public String checkIfAllocationStatusIsValid(String allocationStatus) throws IllegalArgumentException {
        AllocationStatus.valueOf(allocationStatus.toUpperCase());
        return allocationStatus.toUpperCase();

    }
}
