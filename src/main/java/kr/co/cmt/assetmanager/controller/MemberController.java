package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.MemberDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.service.AuthorityService;
import kr.co.cmt.assetmanager.service.DepartmentService;
import kr.co.cmt.assetmanager.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "init")
    public String init() {
        Authority authority = new Authority(1L, "이름-1","code-1", "비고",
                true,"1", LocalDate.now(),LocalDate.now());
        authorityService.createAuthority(authority);
        departmentService.createDepartment(Department.builder().id(1L).code("dev")
                .parent(null).name("dev").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(2L).code("manage")
                .parent(null).name("manage").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(3L).code("web")
                .parent(departmentService.findDepartmentById(1L).get()).name("web").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(4L).code("android")
                .parent(departmentService.findDepartmentById(1L).get()).name("android").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(5L).code("cloud")
                .parent(departmentService.findDepartmentById(3L).get()).name("cloud").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());


        memberService.createMember(Member.builder().id(1L)
                .authority(authorityService.findAuthorityById(1L).get()).department(departmentService.findDepartmentById(1L).get())
                .email("test1@naver.com").password("sdfsdf").name("김아무개").birthday(LocalDate.of(1992,8,11))
                .position("e").phone("010-1232-6666").remark("비고").useYn(true).register("1").registedDateAt(LocalDate.now())
                .updatedDateAt(LocalDate.now()).build());

        memberService.createMember(Member.builder().id(2L)
                .authority(authorityService.findAuthorityById(1L).get()).department(departmentService.findDepartmentById(3L).get())
                .email("test2@naver.com").password("sdfsdf").name("최아무개").birthday(LocalDate.of(1992,8,11))
                .position("am").phone("010-1232-6666").remark("비고").useYn(true).register("1").registedDateAt(LocalDate.now())
                .updatedDateAt(LocalDate.now()).build());

        memberService.createMember(Member.builder().id(3L)
                .authority(authorityService.findAuthorityById(1L).get()).department(departmentService.findDepartmentById(5L).get())
                .email("test3@naver.com").password("sdfsdf").name("최아무개").birthday(LocalDate.of(1992,8,11))
                .position("m").phone("010-1232-6666").remark("비고").useYn(true).register("1").registedDateAt(LocalDate.now())
                .updatedDateAt(LocalDate.now()).build());

        return "test";
    }

    @GetMapping(params = "page")
    public Page<MemberDto> page(SearchDto searchDto, Pageable pageable) {
        Page<Member> members = memberService.findAllMember(searchDto, pageable);
        Page<MemberDto> memberDtos = members.map(member ->
                MemberDto.convertEntityToDto(member));
        return memberDtos;
    }

    @GetMapping
    public List<Member> index() {
        return memberService.findAllMember();
    }

    /**
     * GET
     * 회원 이메일 중복검사
     */
    @GetMapping(value = "/{email}/exists")
    public ResponseEntity<Boolean> isExistEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(memberService.isExistByEmail(email));
    }

    @PostMapping
    public MemberDto store(@RequestBody MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);

        if (memberDto.getAuthorityId() != null) {
            member.setAuthority(authorityService.findAuthorityById(memberDto.getAuthorityId()).get());
        }

        if (memberDto.getDepartmentId() != null) {
            member.setDepartment(departmentService.findDepartmentById(memberDto.getDepartmentId()).get());
        }

        memberService.createMember(member);

        return MemberDto.convertEntityToDto(member);
    }

    @GetMapping(value = "/{id}")
    public Optional<Member> show(@PathVariable("id") long memberId) {
        return memberService.findMemberById(memberId);
    }

    @PutMapping(value = "/{id}")
    public MemberDto update(@PathVariable("id") long memberId, @RequestBody MemberDto memberDto) {
        Member member = memberService.findMemberById(memberId).get();
        member.setName(memberDto.getName());
        member.setPhone(memberDto.getPhone());
        member.setBirthday(memberDto.getBirthday());
        member.setPosition(memberDto.getPosition());
        member.setRemark(memberDto.getRemark());
        member.setUseYn(memberDto.getUseYn());
        member.setUpdatedDateAt(LocalDate.now());

        if (memberDto.getAuthorityId() != null) {
            member.setAuthority(authorityService.findAuthorityById(memberDto.getAuthorityId()).get());
        }

        if (memberDto.getDepartmentId() != null) {
            member.setDepartment(departmentService.findDepartmentById(memberDto.getDepartmentId()).get());
        }

        memberService.updateMember(member);

        return MemberDto.convertEntityToDto(member);
    }

    /**
     * DELETE
     * 회원 삭제
     **/
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long memberId) {
//        memberService.deleteMemberById(memberId);
        Member member = memberService.findMemberById(memberId).get();
        member.setUseYn(false);
        memberService.updateMember(member);
    }
}
