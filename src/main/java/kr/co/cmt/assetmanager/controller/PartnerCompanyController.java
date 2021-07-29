package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.PartnerCompanyDto;
import kr.co.cmt.assetmanager.dto.PartnerCompanyMemberDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Category;
import kr.co.cmt.assetmanager.model.PartnerCompany;
import kr.co.cmt.assetmanager.model.PartnerCompanyMember;
import kr.co.cmt.assetmanager.service.PartnerCompanyMemberService;
import kr.co.cmt.assetmanager.service.PartnerCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/partner-companys")
public class PartnerCompanyController {
    @Autowired
    private PartnerCompanyService partnerCompanyService;

    @Autowired
    private PartnerCompanyMemberService partnerCompanyMemberService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/init")
    public String init() {

        return "test";
    }

    /**
     * GET
     * 업체 목록
     */
    @GetMapping
    public List<PartnerCompanyDto> index() {
        List<PartnerCompany> partnerCompanies = partnerCompanyService.findAllPartnerCompany();
        List<PartnerCompanyDto> partnerCompanyDtos = partnerCompanies.stream().map(partnerCompany ->
                PartnerCompanyDto.convertEntityToDto(partnerCompany)).collect(Collectors.toList());
        return partnerCompanyDtos;
    }

    /**
     * GET
     * 업체 목록(페이징 + 검색)
     */
    @GetMapping(params = "page")
    public Page<PartnerCompanyDto> page(SearchDto searchDto, Pageable pageable) {
        Page<PartnerCompany> partnerCompanies = partnerCompanyService.findAllPartnerCompany(searchDto, pageable);
        Page<PartnerCompanyDto> partnerCompanyDtos = partnerCompanies.map(partnerCompany ->
                PartnerCompanyDto.convertEntityToDto(partnerCompany));
        return partnerCompanyDtos;
    }

    /**
     * GET
     * 업체코드 중복검사
     */
    @GetMapping(value = "/{code}/exists")
    public ResponseEntity<Boolean> isExistCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(partnerCompanyService.isExistByCode(code));
    }

    /**
     * POST
     * 업체 저장
     */
    @PostMapping
    public PartnerCompanyDto store(@RequestBody PartnerCompanyDto partnerCompanyDto) {
        PartnerCompany partnerCompany = modelMapper.map(partnerCompanyDto, PartnerCompany.class);
/*        if(categoryDto.getParentId() != null) {
            Category parent = categoryService.findCategoryById(categoryDto.getParentId()).get();
            category.setParent(parent);
        }*/

        partnerCompanyService.createPartnerCompany(partnerCompany);
        return PartnerCompanyDto.convertEntityToDto(partnerCompany);
    }

    /**
     * GET
     * 업체 보기
     */
    @GetMapping(value = "/{id}")
    public PartnerCompanyDto show(@PathVariable("id") long partnerCompanyId) {
//        Optional<Authority> authority = authorityRepository.findById(authorityId);
        if(partnerCompanyId == 0) return null;

        PartnerCompany partnerCompany = partnerCompanyService.findPartnerCompanyById(partnerCompanyId).get();
        return PartnerCompanyDto.convertEntityToDto(partnerCompany);
    }

    /**
     * PUT
     * 업체 수정
     */
    @PutMapping(value = "/{id}")
    public PartnerCompanyDto update(@PathVariable("id") long partnerCompanyId, @RequestBody PartnerCompanyDto partnerCompanyDto) {
        PartnerCompany entity = partnerCompanyService.findPartnerCompanyById(partnerCompanyId).get();
        entity.setName(partnerCompanyDto.getName());
        entity.setCompanyPhone(partnerCompanyDto.getCompanyPhone());
        entity.setRemark(partnerCompanyDto.getRemark());
        entity.setUseYn(partnerCompanyDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());
        partnerCompanyService.updatePartnerCompany(entity);
        return PartnerCompanyDto.convertEntityToDto(entity);
    }

    /**
     * DELETE
     * 업체 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long partnerCompanyId) {
        PartnerCompany partnerCompany = partnerCompanyService.findPartnerCompanyById(partnerCompanyId).get();
        partnerCompany.setUseYn(false);
        partnerCompanyService.updatePartnerCompany(partnerCompany);
    }

    @GetMapping(value = "/{id}/members")
    public Collection<PartnerCompanyMemberDto> getMembersById(@PathVariable("id") long partnerCompanyId) {
        Collection<PartnerCompanyMember> partnerCompanyMembers = partnerCompanyMemberService.findAllPartnerCompanyMembersByParentId(partnerCompanyId);
        return  partnerCompanyMembers.stream().map(partnerCompanyMember ->
                PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember)).collect(Collectors.toList());

    }
}
