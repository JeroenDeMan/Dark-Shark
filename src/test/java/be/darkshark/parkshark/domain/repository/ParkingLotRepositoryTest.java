package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
class ParkingLotRepositoryTest {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Test
    public void blancSetUp() {
    }

    @Test
    @Sql("insert-parking-lots.sql")
    public void whenAddingParkingLots_repositorySizeIsOne() {
        Collection<ParkingLot> result = parkingLotRepository.findAll();
        Assertions.assertEquals(1, result.size());
    }


    @Test
    @Sql("insert-parking-lots.sql")
    public void whenGettingParkingLotById_returnsParkingLotWithIdId() {
        Optional<ParkingLot> result = parkingLotRepository.findById(1L);
        Assertions.assertEquals(1L, result.get().getId());
    }
}
