package be.darkshark.parkshark.service;

import be.darkshark.parkshark.domain.entity.person.Employee;
import be.darkshark.parkshark.domain.entity.util.Address;
import be.darkshark.parkshark.domain.entity.util.MailAddress;
import be.darkshark.parkshark.domain.entity.util.PhoneNumber;
import be.darkshark.parkshark.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreateEmployees {
    private EmployeeRepository employeeRepository;

    @Autowired
    public CreateEmployees(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

//        Employee employee1 = new Employee("Paul", "WOWO",
//                new Address("street", "22", 1000, "City"),
//                new PhoneNumber("32", 11111),
//                new MailAddress("email@email.com"));
//        employeeRepository.save(employee1);
//        System.out.println("EMPLOYEE CREATED");
    }
}
