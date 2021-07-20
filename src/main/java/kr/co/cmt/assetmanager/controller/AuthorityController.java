package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.AuthorityDto;
import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.service.AuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/authoritys")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/init")
    public String init() {
        for (int i = 0; i < 100; i++) {
            Authority authority = new Authority(null, "이름-"+ i,"code-"+i , "비고",
                    true,"1", LocalDate.now(),LocalDate.now());
            authorityService.createAuthority(authority);
        }
        return "test";
    }


    /**
     * GET
     * 권한 목록(페이징)
     */
/*    @GetMapping(params = "page")
    public Page<AuthorityDto> page(Pageable pageable) {
        Page<Authority> authoritys = authorityService.findAllAuthority(pageable);
        Page<AuthorityDto> authorityDtos = authoritys.map(
                authority -> modelMapper.map(authority, AuthorityDto.class)
        );
        return authorityDtos;
    }*/
    @GetMapping(params = "page")
    public Page<AuthorityDto> page(AuthorityDto authorityDto, Pageable pageable) {

//        Map<String, Object> searchRequest = authorityDto.stream()
////        Page<Authority> authoritys = authorityService.findAllAuthority(AuthoritySpecification.seachWith(searchKeyword), pageable);
        Page<Authority> authoritys = authorityService.findAllAuthority(null, pageable);
        Page<AuthorityDto> authorityDtos = authoritys.map(
                authority -> modelMapper.map(authority, AuthorityDto.class)
        );
        return authorityDtos;
    }

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
        authority.setRegister("1");
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
