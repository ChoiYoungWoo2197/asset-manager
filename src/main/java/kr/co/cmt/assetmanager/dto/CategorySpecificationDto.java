package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.Category;
import kr.co.cmt.assetmanager.model.CategorySpecification;
import kr.co.cmt.assetmanager.model.Department;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategorySpecificationDto {

    private Long id;
    private Long parentId;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

    @Builder
    public CategorySpecificationDto(Long id, Category parent, String name, Boolean useYn,
                                    String register, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.parentId = parent == null ? null : parent.getId();
        this.name = name;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

    public static CategorySpecificationDto convertEntityToDto(CategorySpecification categorySpecification) {
        CategorySpecificationDto categorySpecificationDto = new CategorySpecificationDto();
        categorySpecificationDto.id = categorySpecification.getId();
        categorySpecificationDto.parentId = categorySpecification.getCategory() == null ? null : categorySpecification.getCategory().getId();
        categorySpecificationDto.name = categorySpecification.getName();
        categorySpecificationDto.useYn = categorySpecification.getUseYn();
        categorySpecificationDto.register = categorySpecification.getRegister();
        categorySpecificationDto.registedDateAt = categorySpecification.getRegistedDateAt();
        categorySpecificationDto.updatedDateAt = categorySpecification.getUpdatedDateAt();
        return categorySpecificationDto;
    }
}
