package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;

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
}
