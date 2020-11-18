package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.division.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.division.DivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DivisionServiceTest {

    @Mock
    DivisionRepository divisionRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    private DivisionMapper divisionMapper;

    private CreateDivisionDto createDivisionDto;
    private Division division;
    private Division parentDivision;
    private Employee employee;
    private DivisionService divisionService;

    @BeforeEach
    public void setUp() {
        // divisionMapper = new DivisionMapper();
        divisionService = new DivisionService(divisionRepository, divisionMapper, employeeRepository);

        employee = new Employee(1L, "Paul", "WOWO",
                new Address("street", "22", 1000, "City"),
                new PhoneNumber("32", 11111),
                new MailAddress("email@email.com"));
        parentDivision = new Division("DivisionName2", "OriginalName2", employee, null);
        division = new Division("DivisionName1", "OriginalName1", employee, parentDivision);

        createDivisionDto = new CreateDivisionDto()
                .setName("Division")
                .setOriginalName("Original")
                .setDirector_id("1")
                .setParent_division_id("");
    }

    @Test
    void loadContext() {
    }

    @Test
    public void whenCreatingADivision_repositoryMethodSaveIsCalledOnce() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(divisionMapper.maptoDivision(createDivisionDto, employee, null)).thenReturn(division);
        Mockito.when(divisionRepository.save(division)).thenReturn(null);

        divisionService.createDivision(createDivisionDto);

        Mockito.verify(divisionRepository, Mockito.times(1)).save(division);
    }

    @Test
    public void whenCreatingADivision_mapperMethodMapToDivisionIsCalledOnce() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(divisionMapper.maptoDivision(createDivisionDto, employee, null)).thenReturn(division);

        Mockito.when(divisionRepository.save(division)).thenReturn(null);

        divisionService.createDivision(createDivisionDto);

        Mockito.verify(divisionMapper, Mockito.times(1)).maptoDivision(createDivisionDto, employee, null);
    }

    @Test
    public void whenRequestingAllDivisions_mappingMethodmapCollectionToDivisionDtoIsCalledOnce() {
        Collection<Division> divisions = List.of(division);
        Mockito.when(divisionRepository.findAll()).thenReturn(divisions);
        DivisionDto divisionDto = new DivisionDto();
        divisionDto.setName(division.getName()).setOriginalName(division.getOriginalName()).setDirector_id(1L).setParent_division_id("1");
        Collection<DivisionDto> divisionDtos = List.of(divisionDto);
        Mockito.when(divisionMapper.mapCollectionToDivisionDto(divisions)).thenReturn(divisionDtos);

        divisionService.getAll();

        Mockito.verify(divisionMapper, Mockito.times(1)).mapCollectionToDivisionDto(divisions);
    }

    @Test
    public void whenRequestingAllDivisions_repositoryMethodIsCalledOnce() {
        Collection<Division> divisions = List.of(division);
        Mockito.when(divisionRepository.findAll()).thenReturn(divisions);
        DivisionDto divisionDto = new DivisionDto();
        divisionDto.setName(division.getName()).setOriginalName(division.getOriginalName()).setDirector_id(1L).setParent_division_id("1");
        Collection<DivisionDto> divisionDtos = List.of(divisionDto);
        Mockito.when(divisionMapper.mapCollectionToDivisionDto(divisions)).thenReturn(divisionDtos);

        divisionService.getAll();

        Mockito.verify(divisionRepository, Mockito.times(1)).findAll();
    }

}
