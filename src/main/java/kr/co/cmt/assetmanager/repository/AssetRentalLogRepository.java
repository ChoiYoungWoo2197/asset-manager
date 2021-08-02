package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.AssetRentalLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface AssetRentalLogRepository  extends JpaRepository<AssetRentalLog, Long> , JpaSpecificationExecutor<AssetRentalLog>{

}
