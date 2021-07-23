package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.MemberRepository;
import kr.co.cmt.assetmanager.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
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
}
