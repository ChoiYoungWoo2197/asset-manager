package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.CategorySpecification;
import kr.co.cmt.assetmanager.repository.CategorySpecificationRepository;
import kr.co.cmt.assetmanager.specification.CategorySpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategorySpecificationService {
    private final CategorySpecificationRepository categorySpecificationRepository;

    public Page<CategorySpecification> findAllCategorySpecification(SearchDto searchDto, Pageable pageable) {
        return categorySpecificationRepository.findAll(CategorySpecifications.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public List<CategorySpecification> findAllCategorySpecification() {
        return categorySpecificationRepository.findAll();
    }

    public Optional<CategorySpecification> findCategorySpecificationById(Long id) {
        return id == null ? Optional.empty() : categorySpecificationRepository.findById(id);
    }

    public CategorySpecification createCategorySpecification(CategorySpecification categorySpecification) {
        return categorySpecificationRepository.save(categorySpecification);
    }

    public CategorySpecification updateCategorySpecification(CategorySpecification categorySpecification) {
        return categorySpecificationRepository.save(categorySpecification);
    }

    public void deleteCategorySpecification(Long id) {
        categorySpecificationRepository.deleteById(id);
    }
}
