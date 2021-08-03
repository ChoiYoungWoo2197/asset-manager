package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.AssetSpecification;
import kr.co.cmt.assetmanager.model.AssetSpecificationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AssetSpecificationRepository extends JpaRepository<AssetSpecification, AssetSpecificationId>,
        JpaSpecificationExecutor<AssetSpecification> {

//    Optional<AssetSpecification> findAssetSpecificationByCodeAndId(String code, Long id);

}
