package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.person.CreateMemberDTO;
import be.darkshark.parkshark.api.dto.person.GetMembersDTO;
import be.darkshark.parkshark.domain.entity.util.MemberShipLevel;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MemberService {

    private final Logger log = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public void createMember(CreateMemberDTO createMemberDTO) {
        try {
            createMemberDTO.setMemberShipLevel(checkMemberShipLevel(createMemberDTO.getMemberShipLevel()));
            memberRepository.save(memberMapper.toEntity(createMemberDTO));
            log.info("Member created");
        } catch (IllegalArgumentException exception) {
            log.error("Failed to create member: Membershiplevel incorrect.");
        }

    }

    public List<GetMembersDTO> getAllMembers() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(), false)
                .map(memberMapper::toGetMembersDTO)
                .collect(Collectors.toList());
    }

    public String checkMemberShipLevel(String memberShipLevel) throws IllegalArgumentException{

        if (memberShipLevel == null || memberShipLevel.equals("")){
            return "BRONZE";
        }
        MemberShipLevel.valueOf(memberShipLevel.toUpperCase());
        return memberShipLevel.toUpperCase();

    }
}
