package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.division.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.division.DivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DivisionService {

    private final Logger myLogger = LoggerFactory.getLogger(DivisionService.class);
    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;
    private EmployeeRepository employeeRepository;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper, EmployeeRepository employeeRepository) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.employeeRepository = employeeRepository;
    }

    public void createDivision(CreateDivisionDto createDivisionDto) {
        assertDirectorId(createDivisionDto);

        Optional<Employee> directorOptional = employeeRepository
                .findById(Long.valueOf(createDivisionDto.getDirector_id()));
        assertDirectorOptional(createDivisionDto, directorOptional);

        Division parentDivision = getParentDivision(createDivisionDto);

        Division division = divisionMapper.maptoDivision(createDivisionDto, directorOptional.get(), parentDivision);

        divisionRepository.save(division);

        myLogger.info("Division created: name = {}, original name = {}, director = {}, parent division = {} {}", division
                .getName(), division.getOriginalName(), "dummyEMployee", division.getParentDivision(), System
                .lineSeparator());
    }

    private Division getParentDivision(CreateDivisionDto createDivisionDto) {
        if (createDivisionDto.getParent_division_id() != null && !createDivisionDto.getParent_division_id().isBlank()) {
            Optional<Division> parentDivisionOptional = divisionRepository
                    .findById(Long.valueOf(createDivisionDto.getParent_division_id()));
            assertParentDivisionOptional(createDivisionDto, parentDivisionOptional);
            return parentDivisionOptional.get();
        }
        return null;
    }

    private void assertParentDivisionOptional(CreateDivisionDto createDivisionDto, Optional<Division> parentDivisionOptional) {
        if (parentDivisionOptional.isEmpty()) {
            myLogger.error("No Division found for Id {}!", createDivisionDto.getDirector_id());
            throw new IllegalArgumentException(String
                    .format("No Division found for Id %s!", createDivisionDto.getDirector_id()));
        }
    }

    private void assertDirectorOptional(CreateDivisionDto createDivisionDto, Optional<Employee> directorOptional) {
        if (directorOptional.isEmpty()) {
            myLogger.error("No director found for Id {}!", createDivisionDto.getDirector_id());
            throw new IllegalArgumentException(String
                    .format("No director found for Id %s!", createDivisionDto.getDirector_id()));
        }
    }

    private void assertDirectorId(CreateDivisionDto createDivisionDto) {
        if (createDivisionDto.getDirector_id() == null || createDivisionDto.getDirector_id().isBlank()) {
            myLogger.error("Invalid Director Id {}!", createDivisionDto.getDirector_id());
            throw new IllegalArgumentException("Invalid Director Id");
        }
    }

    public Collection<DivisionDto> getAll() {
        return divisionMapper.mapCollectionToDivisionDto(divisionRepository.findAll());
    }

}
