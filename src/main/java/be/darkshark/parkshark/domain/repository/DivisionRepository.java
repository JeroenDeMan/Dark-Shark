package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.Division;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {

    @Override
    Collection<Division> findAll();
}
