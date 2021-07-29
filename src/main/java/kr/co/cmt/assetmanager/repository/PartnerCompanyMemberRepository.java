package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.PartnerCompanyMember;
import kr.co.cmt.assetmanager.specification.PartnerCompanyMemberSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface PartnerCompanyMemberRepository extends JpaRepository<PartnerCompanyMember, Long>, JpaSpecificationExecutor<PartnerCompanyMemberSpecification> {
}
