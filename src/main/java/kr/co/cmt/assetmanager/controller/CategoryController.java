package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.dto.CategoryDto;
import kr.co.cmt.assetmanager.dto.SearchDto;
import kr.co.cmt.assetmanager.model.Category;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.service.CategoryService;
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
@RequestMapping(value = "/api/categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/init")
    public String init() {
        categoryService.createCategory(Category.builder().id(1L).code("it")
                .parent(null).name("전자기기").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        categoryService.createCategory(Category.builder().id(2L).code("desk")
                .parent(null).name("책상").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        categoryService.createCategory(Category.builder().id(3L).code("desktop")
                .parent(categoryService.findCategoryById(1L).get()).name("데스크탑").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        categoryService.createCategory(Category.builder().id(4L).code("laptop")
                .parent(categoryService.findCategoryById(1L).get()).name("노트북").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        categoryService.createCategory(Category.builder().id(5L).code("cpu")
                .parent(categoryService.findCategoryById(3L).get()).name("cpu").remark("비고").useYn(true).register("1")
                .registedDateAt(LocalDate.now()).updatedDateAt(LocalDate.now()).build());
        return "test";
    }

    /**
     * GET
     * 카테고리 목록
     */
    @GetMapping
    public List<CategoryDto> index() {
        List<Category> categories = categoryService.findAllCategory();
        List<CategoryDto> categoryDtos = categories.stream().map(category ->
                CategoryDto.convertEntityToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    /**
     * GET
     * 카테고리 목록(페이징 + 검색)
     */
    @GetMapping(params = "page")
    public Page<CategoryDto> page(SearchDto searchDto, Pageable pageable) {
        Page<Category> categories = categoryService.findAllCategory(searchDto, pageable);
        Page<CategoryDto> categoryDtos = categories.map(category ->
                CategoryDto.convertEntityToDto(category));
        return categoryDtos;
    }

    /**
     * GET
     * 카테고리코드 중복검사
     */
    @GetMapping(value = "/{code}/exists")
    public ResponseEntity<Boolean> isExistCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(categoryService.isExistByCode(code));
    }

    /**
     * POST
     * 카테고리 저장
     */
    @PostMapping
    public CategoryDto store(@RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        if(categoryDto.getParentId() != null) {
            Category parent = categoryService.findCategoryById(categoryDto.getParentId()).get();
            category.setParent(parent);
        }

        categoryService.createCategory(category);
        return CategoryDto.convertEntityToDto(category);
    }

    /**
     * GET
     * 카테고리 보기
     */
    @GetMapping(value = "/{id}")
    public CategoryDto show(@PathVariable("id") long categoryId) {
//        Optional<Authority> authority = authorityRepository.findById(authorityId);
        if(categoryId == 0) return null;

        Category category = categoryService.findCategoryById(categoryId).get();
        return CategoryDto.convertEntityToDto(category);
    }

    /**
     * PUT
     * 카테고리 수정
     */
    @PutMapping(value = "/{id}")
    public CategoryDto update(@PathVariable("id") long categoryId, @RequestBody CategoryDto categoryDto) {
        Category entity = categoryService.findCategoryById(categoryId).get();
        entity.setName(categoryDto.getName());
        entity.setRemark(categoryDto.getRemark());
        entity.setUseYn(categoryDto.getUseYn());
        entity.setUpdatedDateAt(LocalDate.now());
        categoryService.updateCategory(entity);
        return CategoryDto.convertEntityToDto(entity);
    }

    /**
     * DELETE
     * 카테고리 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
