package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.api.dto.person.CreateMemberDTO;
import be.darkshark.parkshark.api.dto.person.GetMembersDTO;
import be.darkshark.parkshark.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetMembersDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember(@RequestBody CreateMemberDTO createMemberDTO) {
        memberService.createMember(createMemberDTO);
    }

    @PutMapping(path = "/{id}/{membershipLevel}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateMemberShipLevel(@PathVariable long id, @PathVariable String membershipLevel){
        memberService.updateMemberShipLevel(id, membershipLevel);
    }
}
