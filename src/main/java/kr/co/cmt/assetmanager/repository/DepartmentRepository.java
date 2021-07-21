package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    boolean existsByCode(String code);
}
