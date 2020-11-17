package be.darkshark.parkshark.domain.entity.person;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends Person {

    @Id
    private long id;

    public Employee() {
    }
}
