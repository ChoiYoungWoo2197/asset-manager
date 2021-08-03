package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.AssetSpecification;
import kr.co.cmt.assetmanager.model.AssetSpecificationId;
import kr.co.cmt.assetmanager.repository.AssetSpecificationRepository;
import kr.co.cmt.assetmanager.specification.AssetSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetSpecificationService {
    @Autowired
    private AssetService assetService;

    @Autowired
    private CategorySpecificationService categorySpecificationService;

    private final AssetSpecificationRepository assetSpecificationRepository;

    public List<AssetSpecification> findAllAssetSpecification(SearchDto searchDto) {
        return assetSpecificationRepository.findAll(AssetSpecifications.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)
        ));
    }

    public List<AssetSpecification> findAllAssetSpecificationByCode(String code) {
        SearchDto searchDto = new SearchDto();
        searchDto.setCode(code);
        return findAllAssetSpecification(searchDto);
    }

    public List<AssetSpecification> findAllAssetSpecification() {
        return assetSpecificationRepository.findAll();
    }

//    public Optional<AssetSpecification> findAssetSpecificationById(String assetCode, String categorySpecificatoinId) {
////        AssetSpecificationId assetSpecificationId = new AssetSpecificationId(assetCode, categorySpecificatoinId);
//        return assetSpecificationRepository.findById(new AssetSpecificationId());
//    }

    public AssetSpecification createAssetSpecification(AssetSpecification assetSpecification) {
        return assetSpecificationRepository.save(assetSpecification);
    }

    public List<AssetSpecification> createAssetSpecificationByList(Collection<AssetSpecification> assetSpecifications) {
        assetSpecifications.forEach(this::createAssetSpecification);
        return new ArrayList<>(assetSpecifications);
    }

    public AssetSpecification updateAssetSpecification(AssetSpecification assetSpecification) {
        return assetSpecificationRepository.save(assetSpecification);
    }

    public List<AssetSpecification> updateAssetSpecificationByList(Collection<AssetSpecification> assetSpecifications) {
        assetSpecifications.forEach(this::updateAssetSpecification);
        return new ArrayList<>(assetSpecifications);
    }


}
