package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.Allocation;
import be.darkshark.parkshark.domain.entity.AllocationStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllActiveAllocations_AllocationsSizeIs2AndOrderIs1Then2 () {
        List<Allocation> result = allocationRepository.findAllByStatus(AllocationStatus.ACTIVE, Sort.by(Sort.Direction.ASC, "startTime"));

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1L, result.get(0).getId());
        Assertions.assertEquals(2L, result.get(1).getId());
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllActiveAllocationsDesc_AllocationsSizeIs2AndOrderIs2Then1 () {
        List<Allocation> result = allocationRepository.findAllByStatus(AllocationStatus.ACTIVE, Sort.by(Sort.Direction.DESC, "startTime"));

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(2L, result.get(0).getId());
        Assertions.assertEquals(1L, result.get(1).getId());
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllStoppedAllocations_AllocationsSizeIs2AndOrderIs4Then3 () {
        List<Allocation> result = allocationRepository.findAllByStatus(AllocationStatus.STOPPED, Sort.by(Sort.Direction.ASC, "startTime"));

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(4L, result.get(0).getId());
        Assertions.assertEquals(3L, result.get(1).getId());
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllStoppedAllocationsDesc_AllocationsSizeIs2AndOrderIs3Then4 () {
        List<Allocation> result = allocationRepository.findAllByStatus(AllocationStatus.STOPPED, Sort.by(Sort.Direction.DESC, "startTime"));

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(3L, result.get(0).getId());
        Assertions.assertEquals(4L, result.get(1).getId());
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllAllocations_AllocationsSizeIs4AndOrderIs4Then3Then1Then2 () {
        List<Allocation> result = allocationRepository.findAll(Sort.by(Sort.Direction.ASC, "startTime"));

        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals(4L, result.get(0).getId());
        Assertions.assertEquals(3L, result.get(1).getId());
        Assertions.assertEquals(1L, result.get(2).getId());
        Assertions.assertEquals(2L, result.get(3).getId());
    }

    @Test
    @Sql("insert-allocation.sql")
    public void whenRequestingAllAllocationsDesc_AllocationsSizeIs4AndOrderIs2Then1Then3Then4 () {
        List<Allocation> result = allocationRepository.findAll(Sort.by(Sort.Direction.DESC, "startTime"));

        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals(2L, result.get(0).getId());
        Assertions.assertEquals(1L, result.get(1).getId());
        Assertions.assertEquals(3L, result.get(2).getId());
        Assertions.assertEquals(4L, result.get(3).getId());
    }


}
