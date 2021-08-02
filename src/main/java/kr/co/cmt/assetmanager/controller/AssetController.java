package kr.co.cmt.assetmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.AssetDto;
import kr.co.cmt.assetmanager.dto.AuthorityDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.*;
import kr.co.cmt.assetmanager.service.*;
import kr.co.cmt.assetmanager.specification.AuthoritySpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/assets")
public class AssetController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PartnerCompanyService partnerCompanyService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetUseLogService assetUseLogService;

    @Autowired
    private AssetRentalLogService assetRentalLogService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AssetDto> index() {
        return assetService.findAllAsset().stream().map(asset ->
                AssetDto.convertEntityToDto(asset)).collect(Collectors.toList());
    }

    @GetMapping(params = "page")
    public Page<AssetDto> page(SearchDto searchDto, Pageable pageable) {
        Page<Asset> assets = assetService.findAllAsset(searchDto, pageable);
        Page<AssetDto> assetDtos = assets.map(asset ->
                AssetDto.convertEntityToDto(asset));
        return assetDtos;
    }

    @GetMapping(value = "/{code}/exists")
    public ResponseEntity<Boolean> isExistCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(assetService.isExistByCode(code));
    }

    @PostMapping
    public AssetDto store(@RequestBody AssetDto assetDto) {
        Asset asset = modelMapper.map(assetDto, Asset.class);

        if(assetDto.getCategoryId() == null || assetDto.getDepartmentId() == null || assetDto.getPartnerCompanyId() == null) {
            return null;
        }

        if(assetDto.getCategoryId() != null) {
            asset.setCategory(categoryService.findCategoryById(assetDto.getCategoryId()).get());
        }

        if(assetDto.getDepartmentId() != null) {
            asset.setDepartment(departmentService.findDepartmentById(assetDto.getDepartmentId()).get());
        }

        if(assetDto.getMemberId() != null) {
            asset.setMember(memberService.findMemberById(assetDto.getMemberId()).get());
        }

        if(assetDto.getPartnerCompanyId() != null) {
            asset.setPartnerCompany(partnerCompanyService.findPartnerCompanyById(assetDto.getPartnerCompanyId()).get());
        }

        //자산 테이블 저장
        assetService.createAsset(asset);

        //자산 변경내역 테이블 저장
        assetUseLogService.createAssetUseLog(
                AssetUseLog.builder()
                        .asset(asset).type("create").content("").register("1").registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now())
                        . build());

        //자산 렌탈내역
        if(assetDto.getType().equals("rental")) {
            assetRentalLogService.createAssetRentalLog(
                    AssetRentalLog.builder()
                            .asset(asset).contractedDateAt(assetDto.getContractDateAt()).expiredDateAt(assetDto.getExpireDateAt())
                            .rentalCount(0L).returnedDateAt(null).register("1").registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build()
            );

            asset.setBuyDateAt(null);
            assetService.updateAsset(asset);
        }

        return AssetDto.convertEntityToDto(asset);
    }

    @GetMapping(value = "/{code}")
    public AssetDto show(@PathVariable("code") String assetCode) {
        Asset asset = assetService.findAssetByCode(assetCode).get();
        return AssetDto.convertEntityToDto(asset);
    }

    @PutMapping(value = "/{code}")
    public AssetDto update(@PathVariable("code") String assetCode, @RequestBody AssetDto assetDto) {
        Asset asset = assetService.findAssetByCode(assetCode).get();
        asset.setRemark(assetDto.getRemark());
        asset.setUseYn(assetDto.getUseYn());
        assetService.updateAsset(asset);
        return AssetDto.convertEntityToDto(asset);
    }

    @DeleteMapping(value = "/{code}")
    public void delete(@PathVariable("code") String assetCode) {
        Asset asset = assetService.findAssetByCode(assetCode).get();
        asset.setUseYn(false);
        assetService.updateAsset(asset);
    }
}
