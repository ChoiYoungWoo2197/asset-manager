package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.DepartmentDto;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.repository.DepartmentRepository;
import kr.co.cmt.assetmanager.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/department")
public class DepartmentController {
//    @Autowired
//    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;
    /**
     * GET
     * 부서 목록
     */
    @GetMapping
    public List<Department> index() {
        return departmentService.findAllDepartment();
    }

    /**
     * POST
     * 부서 저장
     */
    @PostMapping
    public Department store(@RequestBody DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        return departmentService.createDepartment(department);
    }

    /**
     * GET
     * 부서 보기
     */
    @GetMapping(value = "/{id}")
    public Optional<Department> show(@PathVariable("id") long departmentId) {
//        Optional<Authority> authority = authorityRepository.findById(authorityId);
        return departmentService.findDepartmentById(departmentId);
    }

    /**
     * PUT
     * 부서 수정
     */
    @PutMapping(value = "/{id}")
    public Department update(@PathVariable("id") long departmentId, @RequestBody DepartmentDto departmentDto) {
        Department entity = departmentService.findDepartmentById(departmentId).get();
        entity.setName(departmentDto.getName());
        entity.setRemark(departmentDto.getRemark());
        entity.setUseYn(departmentDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());
        return departmentService.updateDepartment(entity);
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
