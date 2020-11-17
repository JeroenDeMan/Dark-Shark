package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.DivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
//@Transactional
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
        try {
            Employee director = null;
//            System.out.println(employeeRepository.findAll().size());
//            System.out.println("aa" + employeeRepository.findAll().get(0).getFirstName());
//            System.out.println("aa" + employeeRepository.findAll().get(0).getId());
////            System.out.println("bb" + employeeRepository.findById(1L).get().getFirstName());
//            System.out.println("cc" + employeeRepository.findById(0L).get().getFirstName());
////            System.out.println("dd" + employeeRepository.findById(2L).get().getFirstName());


//            employeeRepository.save(new Employee()); // TO REMOVE LATER
//            director = employeeRepository.findById(1L).get(); // TO REMOVE LATER

            if (createDivisionDto.getDirector_id() != null && !createDivisionDto.getDirector_id().isBlank()) {
                Optional<Employee> directorOptional = employeeRepository
                        .findById(Long.valueOf(createDivisionDto.getDirector_id()));
                if (directorOptional.isPresent()) {
                    director = directorOptional.get();
                } else {
                    myLogger.warn("No director found for Id {}!", createDivisionDto.getDirector_id());
                    throw new IllegalArgumentException(String.format("No director found for Id %s!", createDivisionDto.getDirector_id()));
                }
            }



            Division parentDivision = null;
            if (createDivisionDto.getParent_division_id() != null && !createDivisionDto.getParent_division_id().isBlank()) {
                Optional<Division> parentDivisionOptional = divisionRepository.findById(Long.valueOf(createDivisionDto.getParent_division_id()));
                if (parentDivisionOptional.isPresent()) parentDivision = parentDivisionOptional.get();
            }
            Division division = new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), director, parentDivision);
            divisionRepository.save(division);
            myLogger.info("Division created: name = {}, original name = {}, director = {}, parent division = {} {}", division.getName(), division.getOriginalName(), "dummyEMployee", division.getParentDivision(), System.lineSeparator());
        } catch (Exception exception) {
            myLogger.warn(exception.getMessage());
        }
    }

    public Collection<DivisionDto> getAll() {
        myLogger.info("Get all divisions called");
        return divisionMapper.mapCollectionToDivisionDto(divisionRepository.findAll());
    }

}
