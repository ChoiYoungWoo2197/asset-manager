package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.MemberDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Asset;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.AssetRepository;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import kr.co.cmt.assetmanager.repository.MemberRepository;
import kr.co.cmt.assetmanager.specification.AssetSpecification;
import kr.co.cmt.assetmanager.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AssetService {
    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final PartnerCompanyService partnerCompanyService;

    private final AssetRepository assetRepository;

    public List<Asset> findAllAsset() {
        return assetRepository.findAll();
    }

    public Page<Asset> findAllAsset(SearchDto searchDto, Pageable pageable) {
        return assetRepository.findAll(AssetSpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public Optional<Asset> findAssetByCode(String code) {
        return code == null ? Optional.empty() : assetRepository.findAssetByCode(code);
    }

    public boolean isExistByCode(String code) {
        return assetRepository.existsByCode(code);
    }

    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Asset asset) {
        if(asset.getCode() == null) {
            throw new IllegalArgumentException("asset code must not be null");
        }

        return assetRepository.save(asset);
    }

    public void deleteAssetByCode(String code) {
        assetRepository.deleteByCode(code);
    }

}
