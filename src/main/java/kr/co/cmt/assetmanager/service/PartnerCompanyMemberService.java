package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.CategorySpecification;
import kr.co.cmt.assetmanager.model.PartnerCompanyMember;
import kr.co.cmt.assetmanager.repository.CategorySpecificationRepository;
import kr.co.cmt.assetmanager.repository.PartnerCompanyMemberRepository;
import kr.co.cmt.assetmanager.specification.CategorySpecifications;
import kr.co.cmt.assetmanager.specification.PartnerCompanyMemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PartnerCompanyMemberService {
    private final PartnerCompanyMemberRepository partnerCompanyMemberRepository;

    public Page<PartnerCompanyMember> findAllPartnerCompanyMember(SearchDto searchDto, Pageable pageable) {
        return partnerCompanyMemberRepository.findAll(PartnerCompanyMemberSpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public List<PartnerCompanyMember> findAllPartnerCompanyMember(SearchDto searchDto) {
        return partnerCompanyMemberRepository.findAll(PartnerCompanyMemberSpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)));
    }

    public List<PartnerCompanyMember> findAllPartnerCompanyMember() {
        return partnerCompanyMemberRepository.findAll();
    }

    public Optional<PartnerCompanyMember> findPartnerCompanyMemberById(Long id) {
        return id == null ? Optional.empty() : partnerCompanyMemberRepository.findById(id);
    }

    public PartnerCompanyMember createPartnerCompanyMember(PartnerCompanyMember partnerCompanyMember) {
        return partnerCompanyMemberRepository.save(partnerCompanyMember);
    }

    public List<PartnerCompanyMember> createPartnerCompanyMemberByList(Collection<PartnerCompanyMember> partnerCompanyMembers) {
        partnerCompanyMembers.forEach(this::createPartnerCompanyMember);
        return new ArrayList<>(partnerCompanyMembers);
    }

    public PartnerCompanyMember updatePartnerCompanyMember(PartnerCompanyMember partnerCompanyMember) {
        return partnerCompanyMemberRepository.save(partnerCompanyMember);
    }

    public void deletePartnerCompanyMember(Long id) {
        partnerCompanyMemberRepository.deleteById(id);
    }
}
