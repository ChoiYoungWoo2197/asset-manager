package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.CategoryDto;
import kr.co.cmt.assetmanager.dto.CategorySpecificationDto;
import kr.co.cmt.assetmanager.dto.DepartmentDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Category;
import kr.co.cmt.assetmanager.model.CategorySpecification;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.service.CategoryService;
import kr.co.cmt.assetmanager.service.CategorySpecificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/category-specifications")
public class CategorySpecificationController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategorySpecificationService categorySpecificationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/init")
    public String init() {
        return "test";
    }

    /**
     * GET
     * 카테고리규격 목록
     */
    @GetMapping
    public List<CategorySpecificationDto> index() {
        List<CategorySpecification> categorySpecifications = categorySpecificationService.findAllCategorySpecification();

        List<CategorySpecificationDto> categorySpecificationDtos = categorySpecifications.stream().map(categorySpecification ->
                CategorySpecificationDto.convertEntityToDto(categorySpecification)).collect(Collectors.toList());
        return categorySpecificationDtos;
    }

    /**
     * GET
     * 카테고리규격 목록(페이징 + 검색)
     */
    @GetMapping(params = "page")
    public Page<CategorySpecificationDto> page(SearchDto searchDto, Pageable pageable) {
        Page<CategorySpecification> categorySpecifications = categorySpecificationService.findAllCategorySpecification(searchDto, pageable);
        Page<CategorySpecificationDto> categorySpecificationDtos = categorySpecifications.map(categorySpecification ->
                CategorySpecificationDto.convertEntityToDto(categorySpecification));
        return categorySpecificationDtos;
    }

    /**
     * POST
     * 카테고리규격 저장
     */
    @PostMapping
    public CategorySpecificationDto store(@RequestBody CategorySpecificationDto categorySpecificationDto) {
        CategorySpecification categorySpecification = modelMapper.map(categorySpecificationDto, CategorySpecification.class);
        if(categorySpecificationDto.getParentId() != null) {
            Category parent = categoryService.findCategoryById(categorySpecificationDto.getParentId()).get();
            categorySpecification.setCategory(parent);
            categorySpecificationService.createCategorySpecification(categorySpecification);
            return CategorySpecificationDto.convertEntityToDto(categorySpecification);
        } else {
            return null;
        }
    }

    /**
     * GET
     * 카테고리규격 보기
     */
    @GetMapping(value = "/{id}")
    public CategorySpecificationDto show(@PathVariable("id") long categorySpecificationId) {
//        Optional<Authority> authority = authorityRepository.findById(authorityId);
        if(categorySpecificationId == 0) return null;

        CategorySpecification categorySpecification = categorySpecificationService.findCategorySpecificationById(
                categorySpecificationId).get();
        return CategorySpecificationDto.convertEntityToDto(categorySpecification);
    }

    /**
     * PUT
     * 카테고리규격 수정
     */
    @PutMapping(value = "/{id}")
    public CategorySpecificationDto update(@PathVariable("id") long categorySpecificationId, @RequestBody CategorySpecificationDto categorySpecificationDto) {
        CategorySpecification entity = categorySpecificationService.findCategorySpecificationById(categorySpecificationId).get();
        entity.setName(categorySpecificationDto.getName());
        entity.setUseYn(categorySpecificationDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());
        categorySpecificationService.updateCategorySpecification(entity);
        return CategorySpecificationDto.convertEntityToDto(entity);
    }

    /**
     * DELETE
     * 카테고리규격 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long categorySpecificationId) {
//        categorySpecificationService.deleteCategorySpecification(categorySpecificationId);
        CategorySpecification categorySpecification = categorySpecificationService.findCategorySpecificationById(categorySpecificationId).get();
        categorySpecification.setUseYn(false);
        categorySpecificationService.updateCategorySpecification(categorySpecification);
    }
}
