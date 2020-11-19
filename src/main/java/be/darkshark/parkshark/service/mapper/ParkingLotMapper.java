package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import be.darkshark.parkshark.api.dto.parkinglot.ParkingLotDto;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingCategory;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

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

    public Collection<ParkingLotDto> mapCollectionToParkingLotDto(Collection<ParkingLot> parkingLots) {

        return parkingLots.stream()
                          .map(parkingLot -> new ParkingLotDto(
                                  parkingLot.getId(),
                                  parkingLot.getName(),
                                  parkingLot.getCapacity(),
                                  parkingLot.getContactPerson().getMailAddress().getMailAddress(),
                                  parkingLot.getContactPerson().getPhoneNumber().getCountryCode() + parkingLot.getContactPerson().getPhoneNumber().getPhoneNumber()
                          ))
                          .collect(Collectors.toList());
    }
}
