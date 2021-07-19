package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.AuthorityDto;
import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import kr.co.cmt.assetmanager.service.AuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/authority")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;

    /**
    * GET
    * 권한 목록
    */
    @GetMapping
    public List<Authority> index() {
        return authorityService.findAllAuthority();
    }

    /**
     * POST
     * 권한 저장
     */
    @PostMapping
    public Authority store(@RequestBody AuthorityDto authorityDto) {
        Authority authority = modelMapper.map(authorityDto, Authority.class);
        return authorityService.createAuthority(authority);
    }

    /**
     * GET
     * 권한 보기
     */
    @GetMapping(value = "/{id}")
    public Optional<Authority> show(@PathVariable("id") long authorityId) {
        return authorityService.findAuthorityById(authorityId);
    }

    /**
     * PUT
     * 권한 수정
     */
    @PutMapping(value = "/{id}")
    public Authority update(@PathVariable("id") long authorityId, @RequestBody AuthorityDto authorityDto) {
        Authority entity = authorityService.findAuthorityById(authorityId).get();
        entity.setName(authorityDto.getName());
        entity.setRemark(authorityDto.getRemark());
        entity.setUseYn(authorityDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());

        return authorityService.updateAuthority(entity);
    }

    /**
     * DELETE
     * 권한 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long authorityId) {
        authorityService.deleteAuthority(authorityId);
    }
}
