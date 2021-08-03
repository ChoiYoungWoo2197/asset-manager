package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.MemberDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Asset;
import kr.co.cmt.assetmanager.model.AssetRentalLog;
import kr.co.cmt.assetmanager.model.AssetUseLog;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.*;
import kr.co.cmt.assetmanager.specification.AssetRentalLogSpecification;
import kr.co.cmt.assetmanager.specification.AssetSpecification;
import kr.co.cmt.assetmanager.specification.AssetUseLogSpecification;
import kr.co.cmt.assetmanager.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AssetRentalLogService {
    private final AssetRentalLogRepository assetRentalLogRepository;


    public Page<AssetRentalLog> findAll(SearchDto searchDto, Pageable pageable) {
        return assetRentalLogRepository.findAll(AssetRentalLogSpecification.searchWith(new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public AssetRentalLog createAssetRentalLog(AssetRentalLog assetRentalLog)
    {
        return assetRentalLogRepository.save(assetRentalLog);
    }

/*    public AssetRentalLog findLastAssetRentalLogByCode(String code) {
        SearchDto searchDto = new SearchDto();
        searchDto.set(code);
        Collection<AssetRentalLog> assetRentalLogs = assetRentalLogRepository.findAll(AssetRentalLogSpecification.searchWith(new ObjectMapper().convertValue(searchDto, Map.class)));
        assetRentalLogs.stream().sorted();

        return null;
    }*/
}
