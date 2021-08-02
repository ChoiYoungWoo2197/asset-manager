package kr.co.cmt.assetmanager.dto;


import kr.co.cmt.assetmanager.model.*;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AssetDto {
    private String code;
    private Long categoryId;
    private String categoryName;
    private Long memberId;
    private String memberName;
    private Long departmentId;
    private String departmentName;
    private Long partnerCompanyId;
    private String partnerCompanyName;
    private String name;
    private String count;
    private String price;
    private LocalDate buyDateAt;
    private LocalDate receivedDateAt;
    private String type;
    private LocalDate contractDateAt;
    private LocalDate expireDateAt;
    private String remark;
    private Boolean useYn;
    private String register;
    private LocalDate registedDateAt;
    private LocalDate updatedDateAt;

    @Builder
    public AssetDto(String code, Category category, PartnerCompany partnerCompany, Member member, Department department,
                 String name, String count, String price, String type, LocalDate buyDateAt, LocalDate receivedDateAt,
                 String remark, Boolean useYn, String register, LocalDate registedDateAt,
                 LocalDate updatedDateAt) {
        this.code = code;
        this.categoryId = category == null ? null : category.getId();
        this.partnerCompanyId  = partnerCompany == null ? null : partnerCompany.getId();
        this.memberId = member == null ? null : member.getId();
        this.departmentId = department == null ? null : department.getId();
        this.name = name;
        this.count = count;
        this.price = price;
        this.type = type;
        this.buyDateAt = buyDateAt;
        this.receivedDateAt = receivedDateAt;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

    public static AssetDto convertEntityToDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.code = asset.getCode();
        assetDto.categoryId = asset.getCategory() == null ? null : asset.getCategory().getId();
        assetDto.categoryName = asset.getCategory() == null ? null : asset.getCategory().getName();
        assetDto.partnerCompanyId  = asset.getPartnerCompany() == null ? null : asset.getPartnerCompany().getId();
        assetDto.partnerCompanyName = asset.getPartnerCompany() == null ? null : asset.getPartnerCompany().getName();
        assetDto.memberId = asset.getMember() == null ? null : asset.getMember().getId();
        assetDto.memberName = asset.getMember() == null ? null : asset.getMember().getName();
        assetDto.departmentId = asset.getDepartment() == null ? null : asset.getDepartment().getId();
        assetDto.departmentName = asset.getDepartment() == null ? null : asset.getDepartment().getName();
        assetDto.name = asset.getName();
        assetDto.count = asset.getCount();
        assetDto.price = asset.getPrice();
        assetDto.type = asset.getType();
        assetDto.buyDateAt = asset.getBuyDateAt();
        assetDto.receivedDateAt = asset.getReceivedDateAt();
        assetDto.remark = asset.getRemark();
        assetDto.useYn = asset.getUseYn();
        assetDto.register = asset.getRegister();
        assetDto.registedDateAt = asset.getRegistedDateAt();
        assetDto.updatedDateAt = asset.getUpdatedDateAt();

        return assetDto;
    }
}
