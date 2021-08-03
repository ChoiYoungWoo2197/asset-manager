package kr.co.cmt.assetmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Category;
import kr.co.cmt.assetmanager.repository.CategoryRepository;
import kr.co.cmt.assetmanager.specification.CategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategorySpecificationService categorySpecificationService;

    private final CategoryRepository categoryRepository;

    public Page<Category> findAllCategory(SearchDto searchDto, Pageable pageable) {
        return categoryRepository.findAll(CategorySpecification.searchWith(
                new ObjectMapper().convertValue(searchDto, Map.class)), pageable);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(Long id) {
        return id == null ? Optional.empty() : categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public boolean isExistByCode(String code) {
        return categoryRepository.existsByCode(code);
    }


}
