package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
}
