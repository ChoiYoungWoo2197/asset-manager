package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
