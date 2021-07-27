package kr.co.cmt.assetmanager.repository;


import kr.co.cmt.assetmanager.model.PartnerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartnerCompanyRepository extends JpaRepository<PartnerCompany, Long>, JpaSpecificationExecutor<PartnerCompany> {
    boolean existsByCode(String code);
}
