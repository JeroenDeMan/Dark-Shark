package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.CreateParkingLotDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.ParkingCategory;
import be.darkshark.parkshark.domain.entity.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.domain.repository.ParkingLotRepository;
import be.darkshark.parkshark.service.mapper.ParkingLotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingLotService {

    private final Logger myLogger = LoggerFactory.getLogger(ParkingLotService.class);
    private EmployeeRepository employeeRepository;
    private DivisionRepository divisionRepository;
    private ParkingLotMapper parkingLotMapper;
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(EmployeeRepository employeeRepository, DivisionRepository divisionRepository, ParkingLotMapper parkingLotMapper, ParkingLotRepository parkingLotRepository) {
        this.employeeRepository = employeeRepository;
        this.divisionRepository = divisionRepository;
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotRepository = parkingLotRepository;
    }

    public void createParkingLot(CreateParkingLotDto createParkingLotDto) {
        if (createParkingLotDto.getName() == null || createParkingLotDto.getName().isBlank()) {
            throw new IllegalArgumentException("You need a name to create a parking lot!");
        }
        ParkingCategory parkingCategory = assertParkingCategory(createParkingLotDto.getParkingCategory());
        if (createParkingLotDto.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity can't be 0 or lower");
        }
        Employee contactPerson = assertEmployeeId(createParkingLotDto.getContactPersonId());
        if (createParkingLotDto.getPricePerHour() <= 0) {
            throw new IllegalArgumentException("Price can't be 0 or lower");
        }
        Division division = assertDivisionId(createParkingLotDto.getDivisionId());

        ParkingLot parkingLot = parkingLotMapper.mapToParkingLot(createParkingLotDto, parkingCategory, contactPerson, division);

        parkingLotRepository.save(parkingLot);

        myLogger.info("Parking Lot created: name = {}, parking category = {}, capacity = {}, contact person = {}, address = {}, price = {}, division = {}{}",
                parkingLot.getName(), parkingLot.getParkingCategory(), parkingLot.getCapacity(), parkingLot.getContactPerson(), parkingLot.getAddress(), parkingLot.getPricePerHour(), parkingLot.getDivision(),
                System.lineSeparator());
    }

    private Division assertDivisionId(long divisionId) {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);
        if (divisionOptional.isEmpty()) {
            throw new IllegalArgumentException("Wrong Division Id!");
        }
        return divisionOptional.get();
    }

    private Employee assertEmployeeId(long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new IllegalArgumentException("Wrong Contact Person Id!");
        }
        return employeeOptional.get();
    }

    private ParkingCategory assertParkingCategory(String parkingCategory) {
        return ParkingCategory.valueOf(parkingCategory.toUpperCase());
    }
}
