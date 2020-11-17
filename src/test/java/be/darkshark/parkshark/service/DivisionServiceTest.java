package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.CreateDivisionDto;
import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.person.Member;
import be.darkshark.parkshark.domain.repository.DivisionRepository;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import be.darkshark.parkshark.service.mapper.DivisionMapper;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void loadContext() {
    }

    @Test
    void createDivision() {

        DivisionMapper divisionMapper = new DivisionMapper();
        Mockito.when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(new Employee()));
        DivisionService divisionService = new DivisionService(divisionRepository, divisionMapper, employeeRepository);

        divisionService.createDivision(new CreateDivisionDto()
        .setName("Division")
        .setOriginalName("Original")
        .setDirector_id("1")
        .setParent_division_id(""));
    }
}
