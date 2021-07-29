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
import java.util.Collection;
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

/*    @PostMapping(value = "/test")
    public String jsonResponse(@RequestBody PartnerCompanyDto[] partnerCompanyDtos) {
        int a = 1;
        int b = 2;
        return "test";
    }*/

    @PostMapping
    public Collection<PartnerCompanyMemberDto> store(@RequestBody Collection<PartnerCompanyMemberDto> partnerCompanyMemberDtos) {

        if(partnerCompanyMemberDtos.size() > 0) {
            Collection<PartnerCompanyMember> partnerCompanyMembers = new ArrayList<>();
            for(PartnerCompanyMemberDto partnerCompanyMemberDto: partnerCompanyMemberDtos) {
                if(partnerCompanyMemberDto.getName() != "") {
                    PartnerCompanyMember entity = modelMapper.map(partnerCompanyMemberDto, PartnerCompanyMember.class);
                    entity.setPartnerCompany(partnerCompanyService.findPartnerCompanyById(partnerCompanyMemberDto.getParentId()).get());
                    partnerCompanyMembers.add(entity);
                }
            }

            partnerCompanyMemberService.createPartnerCompanyMemberByList(partnerCompanyMembers);

            return partnerCompanyMembers.stream().map(partnerCompanyMember ->
                    PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @GetMapping(value = "/{id}")
    public PartnerCompanyMemberDto show(@PathVariable("id") long partnerCompanyMemberId) {
        if(partnerCompanyMemberId == 0) return null;

        PartnerCompanyMember partnerCompanyMember = partnerCompanyMemberService.findPartnerCompanyMemberById(
                partnerCompanyMemberId).get();
        return PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember);
    }

    @PutMapping()
    public Collection<PartnerCompanyMemberDto> update(@RequestBody Collection<PartnerCompanyMemberDto> partnerCompanyMemberDtos) {
        if(partnerCompanyMemberDtos.size() > 0) {
            Collection<PartnerCompanyMember> partnerCompanyMembers = new ArrayList<>();
            for(PartnerCompanyMemberDto partnerCompanyMemberDto: partnerCompanyMemberDtos) {
                if(partnerCompanyMemberDto.getName() != "") {
                    PartnerCompanyMember entity = modelMapper.map(partnerCompanyMemberDto, PartnerCompanyMember.class);
                    entity.setPartnerCompany(partnerCompanyService.findPartnerCompanyById(partnerCompanyMemberDto.getParentId()).get());
                    partnerCompanyMembers.add(entity);
                }
            }

            partnerCompanyMemberService.updatePartnerCompanyMemberByList(partnerCompanyMembers);

            return partnerCompanyMembers.stream().map(partnerCompanyMember ->
                    PartnerCompanyMemberDto.convertEntityToDto(partnerCompanyMember)).collect(Collectors.toList());

        } else {
            return null;
        }
    }

    @DeleteMapping()
    public void delete(@RequestBody Collection<PartnerCompanyMemberDto> partnerCompanyMemberDtos) {
        if(partnerCompanyMemberDtos.size() > 0) {
            Collection<PartnerCompanyMember> partnerCompanyMembers = partnerCompanyMemberDtos.stream().map(partnerCompanyMember ->
                    modelMapper.map(partnerCompanyMember, PartnerCompanyMember.class)).collect(Collectors.toList());
            partnerCompanyMemberService.deletePartnerCompanyMemberByList(partnerCompanyMembers);
        }
    }
}
