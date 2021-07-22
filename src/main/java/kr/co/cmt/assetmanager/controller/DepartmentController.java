package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.DepartmentDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.service.DepartmentService;
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
@RequestMapping(value = "/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/init")
    public String init() {
/*        for (int i = 0; i < 1000 ; i++) {
            Department department =  Department.builder()
                    .id(null).code("code-"+i)
                    .name("name-"+i).parent(null)
                    .remark("비고").useYn(true)
                    .register("1").registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build();
            departmentService.createDepartment(department);
        }*/
        departmentService.createDepartment(Department.builder().id(1L).code("dev")
                .parent(null).name("dev").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(2L).code("manage")
                .parent(null).name("manage").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(3L).code("web")
                .parent(departmentService.findDepartmentById(1L).get()).name("web").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(4L).code("android")
                .parent(departmentService.findDepartmentById(1L).get()).name("android").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        departmentService.createDepartment(Department.builder().id(5L).code("cloud")
                .parent(departmentService.findDepartmentById(3L).get()).name("cloud").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        return "test";
    }

    /**
     * GET
     * 부서 목록
     */
    @GetMapping
    public List<DepartmentDto> index() {
        List<Department> departments = departmentService.findAllDepartment();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for (Department department : departments) {
            departmentDtos.add(DepartmentDto.builder()
                    .id(department.getId()).parent(department.getParent())
                    .code(department.getCode()).name(department.getName())
                    .remark(department.getRemark()).useYn(department.getUseYn())
                    .register(department.getRegister()).registedDateAt(department.getRegistedDateAt()).updatedDateAt(department.getUpdatedDateAt())
                    .build());
        }
        return departmentDtos;
    }
    
    /**
     * GET
     * 부서 목록(페이징 + 검색)
     */
    @GetMapping(params = "page")
    public Page<DepartmentDto> page(SearchDto searchDto, Pageable pageable) {
        Page<Department> departments = departmentService.findAllDepartment(searchDto, pageable);
        Page<DepartmentDto> departmentDtos = departments.map(authority ->
                modelMapper.map(authority, DepartmentDto.class));
        return departmentDtos;
    }

    /**
     * GET
     * 부서코드 중복검사
     */
    @GetMapping(value = "/{code}/exists")
    public ResponseEntity<Boolean> isExistCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(departmentService.isExistByCode(code));
    }

    /**
     * POST
     * 부서 저장
     */
    @PostMapping
    public DepartmentDto store(@RequestBody DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        if(departmentDto.getParentId() != null) {
            Department parent = departmentService.findDepartmentById(departmentDto.getParentId()).get();
            department.setParent(parent);
        }

        departmentService.createDepartment(department);
        return DepartmentDto.convertEntityToDto(department);
    }

    /**
     * GET
     * 부서 보기
     */
    @GetMapping(value = "/{id}")
    public DepartmentDto show(@PathVariable("id") long departmentId) {
//        Optional<Authority> authority = authorityRepository.findById(authorityId);
        Department department = departmentService.findDepartmentById(departmentId).get();
        return DepartmentDto.convertEntityToDto(department);
    }

    /**
     * PUT
     * 부서 수정
     */
    @PutMapping(value = "/{id}")
    public DepartmentDto update(@PathVariable("id") long departmentId, @RequestBody DepartmentDto departmentDto) {
        Department entity = departmentService.findDepartmentById(departmentId).get();
        entity.setName(departmentDto.getName());
        entity.setRemark(departmentDto.getRemark());
        entity.setUseYn(departmentDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());
        departmentService.updateDepartment(entity);
        return DepartmentDto.convertEntityToDto(entity);
    }

    /**
     * DELETE
     * 부서 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
