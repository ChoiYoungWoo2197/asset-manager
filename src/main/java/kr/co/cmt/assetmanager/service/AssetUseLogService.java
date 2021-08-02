package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.MemberDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Asset;
import kr.co.cmt.assetmanager.model.AssetUseLog;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.AssetRepository;
import kr.co.cmt.assetmanager.repository.AssetUseLogRepository;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import kr.co.cmt.assetmanager.repository.MemberRepository;
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
public class AssetUseLogService {
    @Autowired
    private final AssetService assetService;

    private final AssetUseLogRepository assetUseLogRepository;

    public Page<AssetUseLog> findAll(SearchDto searchDto, Pageable pageable) {
        return assetUseLogRepository.findAll(AssetUseLogSpecification.searchWith(new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public AssetUseLog createAssetUseLog(AssetUseLog assetUseLog)
    {
        return assetUseLogRepository.save(assetUseLog);
    }

}
