package be.darkshark.parkshark.domain.repository;


import be.darkshark.parkshark.domain.entity.Allocation;
import be.darkshark.parkshark.domain.entity.AllocationStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends CrudRepository<Allocation, Long> {

    Allocation findByMember_IdAndEndTimeIsNull(long memberId);

    Allocation findByIdAndMember_IdAndEndTimeIsNull(long allocationId, long memberID);

    long countAllByParkingLot_IdAndEndTimeIsNull(long parkingLotId);

    List<Allocation> findAllByStatus (AllocationStatus allocationStatus, Sort sort);

    List<Allocation> findAll (Sort sort);



}
