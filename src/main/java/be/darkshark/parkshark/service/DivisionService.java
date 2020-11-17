package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.DivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class DivisionService {

    private final Logger myLogger = LoggerFactory.getLogger(DivisionService.class);
    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public void createDivision(CreateDivisionDto createDivisionDto) {
        try {
            Employee dummyEmployee = new Employee();
            Division parentDivision = null;
            if (createDivisionDto.getParent_division_id() != null && !createDivisionDto.getParent_division_id().isBlank()) {
                Optional<Division> parentDivisionOptional = divisionRepository.findById(Long.valueOf(createDivisionDto.getParent_division_id()));
                if (parentDivisionOptional.isPresent()) parentDivision = parentDivisionOptional.get();
            }
            Division division = new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), dummyEmployee, parentDivision);
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
