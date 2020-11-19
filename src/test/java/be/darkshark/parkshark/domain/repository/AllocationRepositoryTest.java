package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.Allocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AllocationRepositoryTest {

    @Autowired
    AllocationRepository allocationRepository;

    @Test
    public void blancSetUp() {

    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllocationForSpecificMember_AllocationIsNotNull() {
        Allocation result = allocationRepository.findByMember_IdAndEndTimeIsNull(1);

        Assertions.assertNotNull(result);
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllAllocationsForASpecificParkingLot_theAmountOfAllocationsIsTwo() {
        long result = allocationRepository.countAllByParkingLot_IdAndEndTimeIsNull(1);

        Assertions.assertEquals(2L, result);
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllocationForSpecificMember_whenAllocationIsStopped_AllocationIsNull() {
        Assertions.assertNull(allocationRepository.findByMember_IdAndEndTimeIsNull(3));
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenStoppingAllocation_OneActiveAllocationIsProvided() {
        Assertions.assertNotNull(allocationRepository.findByIdAndMember_IdAndEndTimeIsNull(2,2));
    }

}
