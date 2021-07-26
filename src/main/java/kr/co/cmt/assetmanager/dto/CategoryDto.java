package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private Long parentId;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String code;
    private String name;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;


    @Builder
    public CategoryDto(Long id, Category parent, String code, String name, String remark, Boolean useYn,
                       String register, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.parentId = parent == null ? null : parent.getId();
        this.code = code;
        this.name = name;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

    public static CategoryDto convertEntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.id = category.getId();
        categoryDto.parentId = category.getParent() == null ? null : category.getParent().getId();
        categoryDto.code = category.getCode();
        categoryDto.name = category.getName();
        categoryDto.remark = category.getRemark();
        categoryDto.useYn = category.getUseYn();
        categoryDto.register = category.getRegister();
        categoryDto.registedDateAt = category.getRegistedDateAt();
        categoryDto.updatedDateAt = category.getUpdatedDateAt();
        return categoryDto;
    }
}
