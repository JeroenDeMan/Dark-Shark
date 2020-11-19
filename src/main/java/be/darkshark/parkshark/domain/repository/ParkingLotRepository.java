package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ParkingLotRepository extends CrudRepository<ParkingLot, Long> {
    @Override
    Collection<ParkingLot> findAll();
}
