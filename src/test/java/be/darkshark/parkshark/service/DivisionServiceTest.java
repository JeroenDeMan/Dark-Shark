package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.CreateDivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DivisionServiceTest {

    @Mock
    DivisionRepository divisionRepository;
    @Mock
    EmployeeRepository employeeRepository;
    private CreateDivisionDto createDivisionDto;
    private Division division;
    private Division parentDivision;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        Employee employee = new Employee("Paul", "WOWO",
                new Address("street", "22", 1000, "City"),
                new PhoneNumber("32", 11111),
                new MailAddress("email@email.com"));
        division = new Division("DivisionName1", "OriginalName1", employee, parentDivision);
        parentDivision = new Division("DivisionName2", "OriginalName2", employee, null);

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
    void createDivision() {
        DivisionMapper divisionMapper = new DivisionMapper();
        Mockito.when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(new Employee()));
        DivisionService divisionService = new DivisionService(divisionRepository, divisionMapper, employeeRepository);

        divisionService.createDivision(createDivisionDto);
    }
}
