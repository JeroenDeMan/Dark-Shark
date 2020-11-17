package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.person.CreateMemberDTO;
import be.darkshark.parkshark.api.dto.person.GetMembersDTO;
import be.darkshark.parkshark.domain.entity.util.MemberShipLevel;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MemberService {
    Logger log = Logger.getLogger("memberService.logging");
    MemberRepository memberRepository;
    MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public void createMember(CreateMemberDTO createMemberDTO) {
        try {
            MemberShipLevel.valueOf(createMemberDTO.getMemberShipLevel().toUpperCase());
            memberRepository.save(memberMapper.toEntity(createMemberDTO));
            log.info("Member created");
        } catch (IllegalArgumentException exception) {

        }

    }

    public List<GetMembersDTO> getAllMembers() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(), false)
                .map(member -> memberMapper.toGetMembersDTO(member))
                .collect(Collectors.toList());
    }
}
