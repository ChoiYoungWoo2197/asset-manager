package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.MemberDto;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import kr.co.cmt.assetmanager.repository.DepartmentRepository;
import kr.co.cmt.assetmanager.repository.MemberRepository;
import kr.co.cmt.assetmanager.service.AuthorityService;
import kr.co.cmt.assetmanager.service.DepartmentService;
import kr.co.cmt.assetmanager.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/member")
public class MemberController {

//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private AuthorityRepository authorityRepository;
//
//    @Autowired
//    private DepartmentRepository departmentRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Member> index() {
        return memberService.findAllMember();
    }

    @PostMapping
    public Member store(@RequestBody MemberDto memberDto) {
        //임시
//        Authority authority = authorityRepository.findById(Long.parseLong("1")));
//        member.setAuthority();

        Member member = modelMapper.map(memberDto, Member.class);

        if (memberDto.getAuthorityId() != null) {
            member.setAuthority(authorityService.findAuthorityById(memberDto.getAuthorityId()).get());
        }

        if (memberDto.getDepartmentId() != null) {
            member.setDepartment(departmentService.findDepartmentById(memberDto.getDepartmentId()).get());
        }

        return memberService.createMember(member);
    }

    @GetMapping(value = "/{id}")
    public Optional<Member> show(@PathVariable("id") long memberId) {
        return memberService.findMemberById(memberId);
    }

    @PutMapping(value = "/{id}")
    public Member update(@PathVariable("id") long memberId, @RequestBody MemberDto memberDto) {
        Member member = memberService.findMemberById(memberId).get();
        member.setName(memberDto.getName());
        member.setPassword(memberDto.getPassword());
        member.setPhone(memberDto.getPhone());
        member.setBirthday(memberDto.getBirthday());
        member.setPosition(memberDto.getPosition());
        member.setRemark(memberDto.getRemark());
        member.setUseYn(memberDto.getUseYn());
        member.setUpdatedDateAt(LocalDate.now());

        return memberService.updateMember(member);
    }

    /**
     * DELETE
     * 회원 삭제
     **/
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long memberId) {
        memberService.deleteMemberById(memberId);
    }
}
