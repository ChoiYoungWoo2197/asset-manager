package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
//    boolean existsByCode(String code);
}
