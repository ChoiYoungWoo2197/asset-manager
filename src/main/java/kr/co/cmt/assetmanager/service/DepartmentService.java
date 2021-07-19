package kr.co.cmt.assetmanager.service;

import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

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
}
