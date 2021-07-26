package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.CategorySpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategorySpecificationRepository extends JpaRepository<CategorySpecification, Long>, JpaSpecificationExecutor<CategorySpecification>{
}
