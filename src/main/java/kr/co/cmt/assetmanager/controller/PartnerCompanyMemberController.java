package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.*;
import kr.co.cmt.assetmanager.model.*;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/partner-company-members")
public class PartnerCompanyMemberController {
    @Autowired
    private PartnerCompanyService partnerCompanyService;

    @Autowired
    private PartnerCompanyMemberService partnerCompanyMemberService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PartnerCompanyMemberDto> index() {
        List<PartnerCompanyMember> partnerCompanyMembers = partnerCompanyMemberService.findAllPartnerCompanyMember();

        List<PartnerCompanyMemberDto> partnerCompanyMemberDtos = partnerCompanyMembers.stream().map(partnerCompanyMember ->
                PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember)).collect(Collectors.toList());
        return partnerCompanyMemberDtos;
    }

//    @GetMapping
//    public List<PartnerCompanyMemberDto> index(SearchDto searchDto) {
//        List<PartnerCompanyMember> partnerCompanyMembers = partnerCompanyMemberService.findAllPartnerCompanyMember(searchDto);
//        List<PartnerCompanyMemberDto> partnerCompanyMemberDtos = partnerCompanyMembers.stream().map(partnerCompanyMember ->
//                PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember)).collect(Collectors.toList());
//        return partnerCompanyMemberDtos;
//    }
//
//
//    @GetMapping(params = "page")
//    public Page<PartnerCompanyMemberDto> page(SearchDto searchDto, Pageable pageable) {
//        Page<PartnerCompanyMember> partnerCompanyMembers = partnerCompanyMemberService.findAllPartnerCompanyMember(searchDto, pageable);
//        Page<PartnerCompanyMemberDto> partnerCompanyMemberDtos = partnerCompanyMembers.map(partnerCompanyMember ->
//                PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember));
//        return partnerCompanyMemberDtos;
//    }
//
//    @PostMapping
//    public PartnerCompanyMemberDto store(@RequestBody PartnerCompanyMemberDto partnerCompanyMemberDto) {
//        PartnerCompanyMember partnerCompanyMember = modelMapper.map(partnerCompanyMemberDto, PartnerCompanyMember.class);
//        if(partnerCompanyMemberDto.getParentId() != null) {
//            PartnerCompany parent = partnerCompanyService.findPartnerCompanyById(partnerCompanyMemberDto.getParentId()).get();
//            partnerCompanyMember.setPartnerCompany(parent);
//            partnerCompanyMemberService.createPartnerCompanyMember(partnerCompanyMember);
//            return PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember);
//        } else {
//            return null;
//        }
//    }
//
//    @GetMapping(value = "/{id}")
//    public PartnerCompanyMemberDto show(@PathVariable("id") long partnerCompanyMemberId) {
//        if(partnerCompanyMemberId == 0) return null;
//
//        PartnerCompanyMember partnerCompanyMember = partnerCompanyMemberService.findPartnerCompanyMemberById(
//                partnerCompanyMemberId).get();
//        return PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember);
//    }
//
//    @PutMapping(value = "/{id}")
//    public PartnerCompanyMemberDto update(@PathVariable("id") long partnerCompanyMemberId, @RequestBody PartnerCompanyMemberDto partnerCompanyMemberDto) {
//        PartnerCompanyMember entity = partnerCompanyMemberService.findPartnerCompanyMemberById(partnerCompanyMemberId).get();
//        entity.setName(partnerCompanyMemberDto.getName());
//        entity.setPhone(partnerCompanyMemberDto.getPhone());
//        entity.setDirectPhone(partnerCompanyMemberDto.getDirectPhone());
//        entity.setUseYn(partnerCompanyMemberDto.getUseYn());
//        entity.setUpdatedDateAt(LocalDate.now());
//        partnerCompanyMemberService.updatePartnerCompanyMember(entity);
//        return PartnerCompanyMemberDto.convertEntityToDto(entity);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void delete(@PathVariable("id") long partnerCompanyMemberId) {
////        categorySpecificationService.deleteCategorySpecification(categorySpecificationId);
//        PartnerCompanyMember partnerCompanyMember = partnerCompanyMemberService.findPartnerCompanyMemberById(partnerCompanyMemberId).get();
//        partnerCompanyMember.setUseYn(false);
//        partnerCompanyMemberService.updatePartnerCompanyMember(partnerCompanyMember);
//    }
}
