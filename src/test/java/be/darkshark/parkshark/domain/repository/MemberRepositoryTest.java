package be.darkshark.parkshark.domain.repository;

import be.darkshark.parkshark.domain.entity.person.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void blancSetUp() {}

    @Test
    @Sql("insert-members.sql")
    public void whenAddingAMember_repositorySizeIsOne() {
        List<Member> result = StreamSupport.stream(memberRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(1, result.size());
    }

}
