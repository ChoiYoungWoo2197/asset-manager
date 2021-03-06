package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.MemberDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import kr.co.cmt.assetmanager.repository.MemberRepository;
import kr.co.cmt.assetmanager.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final AuthorityService authorityService;

    @Autowired
    private final DepartmentService departmentService;

    private final MemberRepository memberRepository;

    public Page<Member> findAllMember(SearchDto searchDto, Pageable pageable) {
        return memberRepository.findAll(MemberSpecification.searchWith(new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberById(Long id) {
        return id == null ? Optional.empty() : memberRepository.findById(id);
    }

    public Member createMember(Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        if(member.getId() == null) {
            throw new IllegalArgumentException("member id must not be null");
        }

        return memberRepository.save(member);
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public boolean isExistByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Collection<Member> storeByFile(Collection<MemberDto> memberDtos) {
        Collection<Member> members = new ArrayList<>();

        for (MemberDto memberDto : memberDtos) {
            //????????? ????????????
            if(memberDto.getName() == null || memberDto.getPassword() == null || memberDto.getEmail() == null
                    || memberDto.getUseYn() == null || memberDto.getDepartmentCode() == null || memberDto.getPosition() == null
                    || memberDto.getAuthorityCode() == null) {
                continue;
            }

            //?????? ???????????? ??? ????????? ?????????
            if(isValidEmail(memberDto.getEmail()) == true
                    ||  departmentService.isExistByCode(memberDto.getDepartmentCode()) == false
                    || authorityService.isExistByCode(memberDto.getAuthorityCode()) == false
                    || isValidPosition(memberDto.getEmail()) == true) {
                continue;
            }

            Boolean isExistMember = memberRepository.existsByEmail(memberDto.getEmail());
            Member member = null;

            if(isExistMember == false) {
                member = Member.builder()
                        .id(null).name(memberDto.getName()).email(memberDto.getEmail()).password(memberDto.getPassword())
                        .birthday(memberDto.getBirthday()).position(memberDto.getPosition()).phone(memberDto.getPhone()).remark(memberDto.getRemark())
                        .useYn(memberDto.getUseYn()).register("1").registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now())
                        .build();
            } else {
                member = memberRepository.findMemberByEmail(memberDto.getEmail());
            }

//            member.setAuthority(authorityService.find);
//            member.setDepartment(departmentService.find);



        }
        return members;
    }

    public Member passwordInit(Long id) {
        Member member = findMemberById(id).get();
        String[] birthdays = member.getBirthday().toString().split("-");

        member.setPassword(birthdays[0]+"/"+birthdays[1]+"/"+birthdays[2]);
        return createMember(member);
    }

    public boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);

        if(m.matches()) {
            err = true;
        }
        return err;
    }

    //????????? ??????.
    public boolean isValidPosition(String position) {
        boolean err = false;
        ArrayList<String> positions = new ArrayList<String>() {{
            add("e");  //??????
            add("am"); //??????
            add("m");  //??????
            add("dgm");//??????
            add("gm"); //??????
            add("d");  //??????
            add("md"); //??????
            add("smd");//??????
            add("p");  //??????
        }};

        if(! positions.contains(position)) {
            err = true;
        }

        return err;
    }
}
