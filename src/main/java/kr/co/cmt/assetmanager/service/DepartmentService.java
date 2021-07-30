package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.DepartmentDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.repository.DepartmentRepository;
import kr.co.cmt.assetmanager.specification.AuthoritySpecification;
import kr.co.cmt.assetmanager.specification.DepartmentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Page<Department> findAllDepartment(SearchDto searchDto, Pageable pageable) {
        return departmentRepository.findAll(DepartmentSpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public List<Department> findAllDepartment() {

        return departmentRepository.findAll();
    }

    public Optional<Department> findDepartmentById(Long id) {
        return id == null ? Optional.empty() : departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public boolean isExistByCode(String code) {
        return departmentRepository.existsByCode(code);
    }

    public Collection<Department> storeByFile(Collection<DepartmentDto> departmentDtos) {
        Collection<Department> departments = new ArrayList<>();
        for(DepartmentDto departmentDto : departmentDtos) {

            if(departmentDto.getCode() == null || departmentDto.getName() == null || departmentDto.getUseYn() == null) {
                continue;
            }

            Boolean isExistDepartment = departmentRepository.existsByCode(departmentDto.getCode());
            Department department = null;
            if(isExistDepartment == false) {
                department =  Department.builder()
                        .id(null).code(departmentDto.getCode())
                        .name(departmentDto.getName()).remark(departmentDto.getRemark()).useYn(departmentDto.getUseYn())
                        .register("1").registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build();
            } else {
                department = departmentRepository.findDepartmentByCode(departmentDto.getCode());

                //일딴 코드는 못바꾸게 하자.
                department.setName(departmentDto.getName());
                department.setRemark(departmentDto.getRemark());
                department.setUseYn(departmentDto.getUseYn());
                department.setUpdatedDateAt(LocalDate.now());
            }

            if(departmentDto.getParentCode() != null) {
                Boolean isExistParent = departmentRepository.existsByCode(departmentDto.getParentCode());

                if(isExistParent == true) {
                    department.setParent(departmentRepository.findDepartmentByCode(departmentDto.getParentCode()));
                }
            }

            departments.add(createDepartment(department));
        }
        return departments;
    }
}
