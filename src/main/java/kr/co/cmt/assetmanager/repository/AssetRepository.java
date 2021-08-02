package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, String>, JpaSpecificationExecutor<Asset> {
    boolean existsByCode(String code);
    void deleteByCode(String code);
    Optional<Asset> findAssetByCode(String code);
}
