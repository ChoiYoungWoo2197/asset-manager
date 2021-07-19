package kr.co.cmt.assetmanager.service;

import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

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
}
