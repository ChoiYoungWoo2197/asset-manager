package kr.co.cmt.assetmanager.dto;
import kr.co.cmt.assetmanager.model.Category;
import kr.co.cmt.assetmanager.model.PartnerCompany;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PartnerCompanyDto {
    private Long id;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String code;
    @NotBlank(message = "코드는 필수 입력 값입니다.")
    private String name;
    private String type;
    private String companyPhone;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

    @Builder
    public PartnerCompanyDto(Long id, String code, String name, String type, String companyPhone, String remark, Boolean useYn,
                       String register, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.companyPhone = companyPhone;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

    public static PartnerCompanyDto convertEntityToDto(PartnerCompany partnerCompany) {
        PartnerCompanyDto partnerCompanyDto = new PartnerCompanyDto();
        partnerCompanyDto.id = partnerCompany.getId();
        partnerCompanyDto.code = partnerCompany.getCode();
        partnerCompanyDto.name = partnerCompany.getName();
        partnerCompanyDto.type = partnerCompany.getType();
        partnerCompanyDto.companyPhone = partnerCompany.getCompanyPhone();
        partnerCompanyDto.remark = partnerCompany.getRemark();
        partnerCompanyDto.useYn = partnerCompany.getUseYn();
        partnerCompanyDto.register = partnerCompany.getRegister();
        partnerCompanyDto.registedDateAt = partnerCompany.getRegistedDateAt();
        partnerCompanyDto.updatedDateAt = partnerCompany.getUpdatedDateAt();
        return partnerCompanyDto;
    }
}
