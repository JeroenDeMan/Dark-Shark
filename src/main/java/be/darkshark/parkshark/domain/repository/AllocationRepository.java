package be.darkshark.parkshark.domain.repository;


import be.darkshark.parkshark.domain.entity.Allocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends CrudRepository<Allocation, Long> {

    public Allocation findByMember_IdAndEndTimeIsNull(long memberId);

    public long countAllByParkingLot_IdAndEndTimeIsNull(long parkingLotId);

}
