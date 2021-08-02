package kr.co.cmt.assetmanager.repository;


import kr.co.cmt.assetmanager.model.AssetUseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetUseLogRepository extends JpaRepository<AssetUseLog, Long> , JpaSpecificationExecutor<AssetUseLog>{
}
