package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.Division;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
class DivisionRepositoryTest {

    @Autowired
    DivisionRepository divisionRepository;

    @Test
    public void blancSetUp() {
    }

    @Test
    @Sql("insert-division.sql")
    public void whenAddingDivisions_repositorySizeIsTwo() {
        Collection<Division> result = divisionRepository.findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @Sql("insert-division.sql")
    public void whenGettingDivisionById_returnsDivisionWithIdId() {
        Optional<Division> result = divisionRepository.findById(2L);
        Assertions.assertEquals(2L, result.get().getId());
    }

}
