package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.person.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void blancSetUp() {
    }

    @Test
    @Sql("insert-employee.sql")
    public void whenAddingAMember_repositorySizeIsOne() {
        Collection<Employee> result = employeeRepository.findAll();
        Assertions.assertEquals(1, result.size());
    }

}

