package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.person.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
