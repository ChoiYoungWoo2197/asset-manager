package kr.co.cmt.assetmanager.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {
    /*
     *  DTO 할일
     *  필수 입력값
     *  유효성(이메일, 패턴, ...)
     *  중복값 확인은 서비스단에서 확인하자.
     * */
    private Long id;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String code;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String name;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

/*    @Builder
    public AuthorityDto( Long id) {

    }*/
}
