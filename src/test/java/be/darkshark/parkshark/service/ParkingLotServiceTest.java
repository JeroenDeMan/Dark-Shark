package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.division.DivisionDto;
import be.darkshark.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import be.darkshark.parkshark.api.dto.parkinglot.DetailedParkingLotDto;
import be.darkshark.parkshark.api.dto.parkinglot.ParkingLotDto;
import be.darkshark.parkshark.api.dto.util.AddressDTO;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingCategory;
import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.domain.repository.ParkingLotRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import be.darkshark.parkshark.service.mapper.ParkingLotMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ParkingLotServiceTest {
    @Mock
    DivisionRepository divisionRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    private DivisionMapper divisionMapper;
    @Mock
    private ParkingLotMapper parkingLotMapper;
    @Mock
    ParkingLotRepository parkingLotRepository;
    private ParkingLotService parkingLotService;

    private Division division;
    private Division parentDivision;
    private Employee employee;
    private DivisionService divisionService;
    private CreateParkingLotDto createParkingLotDto;
    private ParkingCategory parkingCategory;
    private ParkingLot parkingLot;
    private ParkingLotDto parkingLotDto;
    private Address address;

    @BeforeEach
    public void setUp() {
        parkingLotService = new ParkingLotService(employeeRepository, divisionRepository, parkingLotMapper, parkingLotRepository);

        employee = new Employee(1L, "Paul", "WOWO",
                new Address("street", "22", 1000, "City"),
                new PhoneNumber("32", 11111),
                new MailAddress("email@email.com"));
        parentDivision = new Division("DivisionName2", "OriginalName2", employee, null);
        division = new Division("DivisionName1", "OriginalName1", employee, parentDivision);

        parkingCategory = ParkingCategory.UNDERGROUND_BUILDING;
        createParkingLotDto = new CreateParkingLotDto(
                "ParkingLot11",
                parkingCategory.toString(),
                5,
                1,
                new AddressDTO("street", "22", 1000, "City"),
                55.33,
                1);

        address = new Address("street", "22", 1000, "City");
        parkingLot = new ParkingLot("parkingLot", parkingCategory, 5, employee, address, 33.33, division);
        parkingLotDto = new ParkingLotDto(1L, "pppp", 66, "email@email.com", "02-000.000.000");
    }

    @Test
    void loadContext() {
    }

    @Test
    void whenCreatingAParkingLot_repositoryMethodSaveIsCalledOnce() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));
        Mockito.when(parkingLotMapper.mapToParkingLot(createParkingLotDto, parkingCategory, employee, division))
               .thenReturn(parkingLot);
        Mockito.when(parkingLotRepository.save(parkingLot)).thenReturn(null);

        parkingLotService.createParkingLot(createParkingLotDto);
        Mockito.verify(parkingLotRepository, Mockito.times(1)).save(parkingLot);
    }

    @Test
    void whenCreatingAParkingLot_parkingLotMapperMethodSaveIsCalledOnce() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));
        Mockito.when(parkingLotMapper.mapToParkingLot(createParkingLotDto, parkingCategory, employee, division))
               .thenReturn(parkingLot);
        Mockito.when(parkingLotRepository.save(parkingLot)).thenReturn(null);

        parkingLotService.createParkingLot(createParkingLotDto);
        Mockito.verify(parkingLotMapper, Mockito.times(1)).mapToParkingLot(createParkingLotDto, parkingCategory, employee, division);
    }




    @Test
    void whenRequestingAllParkingLots_repositoryMethodSaveIsCalledOnce() {
        List<ParkingLot> parkingLots = List.of(this.parkingLot);
        Mockito.when(parkingLotRepository.findAll()).thenReturn(parkingLots);
        Mockito.when(parkingLotMapper.mapCollectionToParkingLotDto(parkingLots))
               .thenReturn(List.of(parkingLotDto));

        parkingLotService.getAll();
        Mockito.verify(parkingLotRepository, Mockito.times(1)).findAll();
    }

    @Test
    void whenRequestingAllParkingLots_parkingLotMapperMethodSaveIsCalledOnce() {
        List<ParkingLot> parkingLots = List.of(this.parkingLot);
        Mockito.when(parkingLotRepository.findAll()).thenReturn(parkingLots);
        Mockito.when(parkingLotMapper.mapCollectionToParkingLotDto(parkingLots))
               .thenReturn(List.of(parkingLotDto));

        parkingLotService.getAll();
        Mockito.verify(parkingLotMapper, Mockito.times(1)).mapCollectionToParkingLotDto(parkingLots);
    }



    @Test
    public void whenRequestingAParkingLot_repositoryMethodIsCalledOnce() {
        Mockito.when(parkingLotRepository.findById(1L)).thenReturn(Optional.of(parkingLot));
        DetailedParkingLotDto parkingLotDto = new DetailedParkingLotDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getParkingCategory().toString(),
                parkingLot.getCapacity(),
                parkingLot.getContactPerson().getId(),
                new AddressDTO(
                        parkingLot.getAddress().getStreet(),
                        parkingLot.getAddress().getHouseNumber(),
                        parkingLot.getAddress().getPostalCode(),
                        parkingLot.getAddress().getCity()),
                parkingLot.getPricePerHour(),
                parkingLot.getDivision().getId()
        );
        Mockito.when(parkingLotMapper.mapToDetailedParkingLotDto(parkingLot)).thenReturn(parkingLotDto);

        parkingLotService.getAParkingLotById(1L);

        Mockito.verify(parkingLotRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenRequestingAParkingLot_MapperMethodIsCalledOnce() {
        Mockito.when(parkingLotRepository.findById(1L)).thenReturn(Optional.of(parkingLot));
        DetailedParkingLotDto parkingLotDto = new DetailedParkingLotDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getParkingCategory().toString(),
                parkingLot.getCapacity(),
                parkingLot.getContactPerson().getId(),
                new AddressDTO(
                        parkingLot.getAddress().getStreet(),
                        parkingLot.getAddress().getHouseNumber(),
                        parkingLot.getAddress().getPostalCode(),
                        parkingLot.getAddress().getCity()),
                parkingLot.getPricePerHour(),
                parkingLot.getDivision().getId()
        );
        Mockito.when(parkingLotMapper.mapToDetailedParkingLotDto(parkingLot)).thenReturn(parkingLotDto);

        parkingLotService.getAParkingLotById(1L);

        Mockito.verify(parkingLotMapper, Mockito.times(1)).mapToDetailedParkingLotDto(parkingLot);
    }


}
