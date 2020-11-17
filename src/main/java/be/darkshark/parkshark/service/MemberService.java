package be.darkshark.parkshark.service;

import be.darkshark.parkshark.api.dto.person.MemberDTO;
import be.darkshark.parkshark.domain.entity.util.MemberShipLevel;
import be.darkshark.parkshark.domain.repository.MemberRepository;
import be.darkshark.parkshark.service.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

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

    public void createMember (MemberDTO memberDTO) {
        try{
            MemberShipLevel.valueOf(memberDTO.getMemberShipLevel().toUpperCase());
            memberRepository.save(memberMapper.toEntity(memberDTO));
            log.info("Member created");
        }catch (IllegalArgumentException exception) {

        }

    }
}
