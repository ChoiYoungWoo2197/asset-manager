package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.*;
import kr.co.cmt.assetmanager.model.*;
import kr.co.cmt.assetmanager.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/asset-specifications")
public class AssetSpecificationController {
    @Autowired
    private AssetService assetService;

    @Autowired
    private CategorySpecificationService categorySpecificationService;

    @Autowired
    private AssetSpecificationService assetSpecificationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AssetSpecificationDto> index() {
        List<AssetSpecification> assetSpecifications = assetSpecificationService.findAllAssetSpecification();
        List<AssetSpecificationDto> assetSpecificationDtos = assetSpecifications.stream().map(assetSpecification ->
                AssetSpecificationDto.convertEntityToDto(assetSpecification)).collect(Collectors.toList());
        return assetSpecificationDtos;
    }

    @PostMapping
    public Collection<AssetSpecificationDto> store(@RequestBody Collection<AssetSpecificationDto> assetSpecificationDtos) {
        if(assetSpecificationDtos.size() > 0) {
            Collection<AssetSpecification> assetSpecifications = new ArrayList<>();
            for (AssetSpecificationDto assetSpecificationDto : assetSpecificationDtos) {
                if(assetSpecificationDto.getAssetCode() == null || assetSpecificationDto.getCategorySpecificationId() == null) {
                    continue;
                }

                AssetSpecification assetSpecification = AssetSpecification.builder()
                        .asset(assetService.findAssetByCode(assetSpecificationDto.getAssetCode()).get())
                        .categorySpecification(categorySpecificationService.findCategorySpecificationById(assetSpecificationDto.getCategorySpecificationId()).get())
                        .content(assetSpecificationDto.getContent())
                        .register("1")
                        .registedDateAt(LocalDate.now())
                        .updatedDateAt(LocalDate.now())
                        .build();
                assetSpecifications.add(assetSpecification);
            }
            assetSpecificationService.createAssetSpecificationByList(assetSpecifications);
            return assetSpecifications.stream().map(assetSpecification ->
                    AssetSpecificationDto.convertEntityToDto(assetSpecification)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @PutMapping
    public Collection<AssetSpecificationDto> update(@RequestBody Collection<AssetSpecificationDto> assetSpecificationDtos) {
        if(assetSpecificationDtos.size() > 0) {
            Collection<AssetSpecification> assetSpecifications = new ArrayList<>();
            for (AssetSpecificationDto assetSpecificationDto : assetSpecificationDtos) {
                if(assetSpecificationDto.getAssetCode() == null || assetSpecificationDto.getCategorySpecificationId() == null) {
                    continue;
                }

                AssetSpecification assetSpecification = assetSpecificationService.findAssetSpecificationByCodeAndId(
                        assetSpecificationDto.getAssetCode(),assetSpecificationDto.getCategorySpecificationId()
                );

                if(assetSpecification != null) {
                    assetSpecification.setContent(assetSpecificationDto.getContent());
                    assetSpecifications.add(assetSpecification);
                }
            }

            assetSpecificationService.updateAssetSpecificationByList(assetSpecifications);
            return assetSpecifications.stream().map(assetSpecification ->
                    AssetSpecificationDto.convertEntityToDto(assetSpecification)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
/*
    @GetMapping(value = "/{code}")
    public Collection<AssetSpecificationDto> show(@PathVariable("code") String assetCode) {
        if(assetCode == null) return null;

        Collection<AssetSpecification> selectAssetSpecifications = new ArrayList<>();
        Collection<AssetSpecification> assetSpecifications = assetSpecificationService.findAllAssetSpecification();

        if(assetSpecifications.size() > 0) {
            for (AssetSpecification assetSpecification : assetSpecifications) {
                if(assetSpecification.getAsset().getCode().equals(assetCode) == true) {
                    selectAssetSpecifications.add(assetSpecification);
                }
            }
        }
        return selectAssetSpecifications.stream().map(assetSpecification ->
                AssetSpecificationDto.convertEntityToDto(assetSpecification)).collect(Collectors.toList());
    }*/

}
