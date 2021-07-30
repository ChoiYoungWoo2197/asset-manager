package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.Department;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepartmentDto {
    /*
     *  DTO 할일
     *  필수 입력값
     *  유효성(이메일, 패턴, ...)
     *  중복값 확인은 서비스단에서 확인하자.
     * */
    private Long id;
    private Long parentId;
    private String parentCode;
    private String parentName;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String code;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String name;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

/*    public DepartmentDto(Department department) {

    }*/


    @Builder
    public DepartmentDto(Long id, Department parent, String code, String name, String remark, Boolean useYn,
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

    public static DepartmentDto convertEntityToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.id = department.getId();
        departmentDto.parentId = department.getParent() == null ? null : department.getParent().getId();
        departmentDto.parentCode = department.getParent() == null ? null : department.getParent().getCode();
        departmentDto.parentName = department.getParent() == null ? null : department.getParent().getName();
        departmentDto.code = department.getCode();
        departmentDto.name = department.getName();
        departmentDto.remark = department.getRemark();
        departmentDto.useYn = department.getUseYn();
        departmentDto.register = department.getRegister();
        departmentDto.registedDateAt = department.getRegistedDateAt();
        departmentDto.updatedDateAt = department.getUpdatedDateAt();
        return departmentDto;
    }
}
