package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.ParkingCategory;
import be.darkshark.parkshark.domain.entity.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotMapper {

    public ParkingLot mapToParkingLot(CreateParkingLotDto createParkingLotDto, ParkingCategory parkingCategory, Employee contactPerson, Division division) {
        AddressDTO addressDTO = createParkingLotDto.getAddress();
        Address address = new Address(addressDTO.getStreet(),
                addressDTO.getHouseNumber(),
                addressDTO.getPostalCode(),
                addressDTO.getCity());

        return new ParkingLot(createParkingLotDto.getName(),
                parkingCategory,
                createParkingLotDto.getCapacity(),
                contactPerson,
                address,
                createParkingLotDto.getPricePerHour(),
                division
        );
    }
}
