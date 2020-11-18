package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DataJpaTest
class DivisionRepositoryTest {

    @Autowired
    DivisionRepository divisionRepository;

    @Test
    public void blancSetUp() {}

    @Test
    @Sql("insert-division.sql")
    public void whenAddingDivisions_repositorySizeIsTwo() {
        Collection<Division> result = divisionRepository.findAll();
        Assertions.assertEquals(2, result.size());
    }

}
