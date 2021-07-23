package kr.co.cmt.assetmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.AuthorityDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.service.AuthorityService;
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
    @GetMapping(params = "page")
    public Page<AuthorityDto> page(SearchDto searchDto, Pageable pageable) {
        Page<Authority> authoritys = authorityService.findAllAuthority(searchDto, pageable);
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
    public List<AuthorityDto> index() {
        return  authorityService.findAllAuthority().stream().map(authority ->
                modelMapper.map(authority, AuthorityDto.class)).collect(Collectors.toList());
    }

    /**
     * GET
     * 권한코드 중복검사
     */
    @GetMapping(value = "/{code}/exists")
    public ResponseEntity<Boolean> isExistCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(authorityService.isExistByCode(code));
    }

    /**
     * POST
     * 권한 저장
     */
    @PostMapping
    public AuthorityDto store(@RequestBody AuthorityDto authorityDto) {
        Authority authority = modelMapper.map(authorityDto, Authority.class);
        authority.setRegister("1");
        authorityService.createAuthority(authority);
        return  modelMapper.map(authority, AuthorityDto.class);
    }

    /**
     * GET
     * 권한 보기
     */
    @GetMapping(value = "/{id}")
    public AuthorityDto show(@PathVariable("id") long authorityId) {
        Authority authority = authorityService.findAuthorityById(authorityId).get();
        return modelMapper.map(authority, AuthorityDto.class);
    }

    /**
     * PUT
     * 권한 수정
     */
    @PutMapping(value = "/{id}")
    public AuthorityDto update(@PathVariable("id") long authorityId, @RequestBody AuthorityDto authorityDto) {
        Authority entity = authorityService.findAuthorityById(authorityId).get();
        entity.setName(authorityDto.getName());
        entity.setRemark(authorityDto.getRemark());
        entity.setUseYn(authorityDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());
        authorityService.updateAuthority(entity);

        return modelMapper.map(entity, AuthorityDto.class);
    }

    /**
     * DELETE
     * 권한 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long authorityId) {
        /**
         * 원래는 delete함수를 사용해야 하는데 논리적인 삭제이기 때문에 useYn을 false로 변경처리한다.
         * authorityService.deleteAuthority(authorityId);
         * */
        Authority authority = authorityService.findAuthorityById(authorityId).get();
        authority.setUseYn(false);
        authorityService.updateAuthority(authority);
    }
}
