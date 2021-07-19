package kr.co.cmt.assetmanager.dto;

import lombok.*;

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
    private Long departmentId;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private LocalDate birthday;
    private String position;
    private String phone;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;
}
