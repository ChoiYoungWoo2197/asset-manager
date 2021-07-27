package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PartnerCompanyMemberDto {
    private Long id;
    private Long parentId;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    private String phone;
    private String directPhone;
    private Boolean useYn;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

    @Builder
    public PartnerCompanyMemberDto(Long id, PartnerCompany parent, String name, String phone, String directPhone,
                                   Boolean useYn, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.parentId = parent == null ? null : parent.getId();
        this.name = name;
        this.phone = phone;
        this.directPhone = directPhone;
        this.useYn = useYn;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

    public static PartnerCompanyMemberDto convertEntityToDto(PartnerCompanyMember partnerCompanyMember) {
        PartnerCompanyMemberDto partnerCompanyMemberDto = new PartnerCompanyMemberDto();
        partnerCompanyMemberDto.id = partnerCompanyMember.getId();
        partnerCompanyMemberDto.parentId = partnerCompanyMember.getPartnerCompany() == null ? null :partnerCompanyMember.getPartnerCompany().getId();
        partnerCompanyMemberDto.name = partnerCompanyMember.getName();
        partnerCompanyMemberDto.phone = partnerCompanyMember.getPhone();
        partnerCompanyMemberDto.directPhone = partnerCompanyMember.getDirectPhone();
        partnerCompanyMemberDto.useYn = partnerCompanyMember.getUseYn();
        partnerCompanyMemberDto.registedDateAt = partnerCompanyMember.getRegistedDateAt();
        partnerCompanyMemberDto.updatedDateAt = partnerCompanyMember.getUpdatedDateAt();
        return partnerCompanyMemberDto;
    }
}
