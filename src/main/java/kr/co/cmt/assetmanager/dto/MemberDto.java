package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.model.Member;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    /*
     *  DTO 할일
     *  필수 입력값
     *  유효성(이메일, 패턴, ...)
     *  중복값 확인은 서비스단에서 확인하자.
     * */
    private Long id;
    private Long authorityId;
    private String authorityCode;
    private String authorityName;
    private Long departmentId;
    private String departmentCode;
    private String departmentName;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private LocalDate birthday;
    private String position;
    private String positionName;
    private String phone;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

    @Builder
    public MemberDto(Long id, Authority authority, Department department, String email, String password, String name,
                     LocalDate birthday, String position, String phone, String remark, Boolean useYn, String register,
                     LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.authorityId = authority == null ? null : authority.getId();
        this.departmentId = department == null ? null : department.getId();
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.position = position;
        this.phone = phone;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

    public static MemberDto convertEntityToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.id = member.getId();
        memberDto.authorityId = member.getAuthority() == null ? null : member.getAuthority().getId();
        memberDto.authorityCode = member.getAuthority() == null ? null : member.getAuthority().getCode();
        memberDto.authorityName = member.getAuthority() == null ? null : member.getAuthority().getName();
        memberDto.departmentId = member.getDepartment() == null ? null : member.getDepartment().getId();
        memberDto.departmentCode = member.getDepartment() == null ? null : member.getDepartment().getCode();
        memberDto.departmentName = member.getDepartment() == null ? null : member.getDepartment().getName();
        memberDto.email = member.getEmail();
        memberDto.password = member.getPassword();
        memberDto.name = member.getName();
        memberDto.birthday = member.getBirthday();
        memberDto.position = member.getPosition();
        memberDto.phone = member.getPhone();
        memberDto.remark = member.getRemark();
        memberDto.useYn = member.getUseYn();
        memberDto.register = member.getRegister();
        memberDto.registedDateAt = member.getRegistedDateAt();
        memberDto.updatedDateAt = member.getUpdatedDateAt();
        return memberDto;
    }
}
