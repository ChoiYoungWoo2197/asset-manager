package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.PartnerCompany;
import kr.co.cmt.assetmanager.repository.PartnerCompanyRepository;
import kr.co.cmt.assetmanager.specification.PartnerCompanySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartnerCompanyService {
    private final PartnerCompanyRepository partnerCompanyRepository;

    public Page<PartnerCompany> findAllPartnerCompany(SearchDto searchDto, Pageable pageable) {
        return partnerCompanyRepository.findAll(PartnerCompanySpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public List<PartnerCompany> findAllPartnerCompany() {
        return partnerCompanyRepository.findAll();
    }

    public Optional<PartnerCompany> findPartnerCompanyById(Long id) {
        return id == null ? Optional.empty() : partnerCompanyRepository.findById(id);
    }

    public PartnerCompany createPartnerCompany(PartnerCompany partnerCompany) {
        return partnerCompanyRepository.save(partnerCompany);
    }

    public PartnerCompany updatePartnerCompany(PartnerCompany partnerCompany) {
        return partnerCompanyRepository.save(partnerCompany);
    }

    public void deletePartnerCompany(Long id) {
        partnerCompanyRepository.deleteById(id);
    }

    public boolean isExistByCode(String code) {
        return partnerCompanyRepository.existsByCode(code);
    }
}
