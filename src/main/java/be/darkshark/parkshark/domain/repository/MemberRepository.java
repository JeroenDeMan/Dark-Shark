package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.person.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
}
