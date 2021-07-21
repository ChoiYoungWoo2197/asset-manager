package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import kr.co.cmt.assetmanager.specification.AuthoritySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Page<Authority> findAllAuthority(SearchDto searchDto, Pageable pageable) {
        return authorityRepository.findAll(AuthoritySpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public Page<Authority> findAllAuthority(Specification<Authority> spec, Pageable pageable) {
        return authorityRepository.findAll(spec, pageable);
    }
    public Page<Authority> findAllAuthority(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

    public List<Authority> findAllAuthority() {
        return authorityRepository.findAll();
    }

    public Optional<Authority> findAuthorityById(Long id) {
        return id == null ? Optional.empty() : authorityRepository.findById(id);
    }

    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Authority updateAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public void deleteAuthority(Long id) {
        authorityRepository.deleteById(id);
    }

    public boolean isExistByCode(String code) {
        return authorityRepository.existsByCode(code);
    }
}
