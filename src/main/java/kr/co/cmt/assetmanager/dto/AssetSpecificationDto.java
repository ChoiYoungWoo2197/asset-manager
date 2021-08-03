package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.AssetSpecification;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AssetSpecificationDto {
    String assetCode;
    Long categorySpecificationId;
    String categorySpecificationName;
    String content;

    @Builder
    public AssetSpecificationDto(String assetCode, Long categorySpecificationId, String content) {
        this.assetCode = assetCode;
        this.categorySpecificationId = categorySpecificationId;
        this.content = content;
    }

    public static AssetSpecificationDto convertEntityToDto(AssetSpecification assetSpecification) {
        AssetSpecificationDto assetSpecificationDto = new AssetSpecificationDto();
        assetSpecificationDto.assetCode = assetSpecification.getAsset() == null ? null : assetSpecification.getAsset().getCode();
        assetSpecificationDto.categorySpecificationId = assetSpecification.getCategorySpecification() == null ? null : assetSpecification.getCategorySpecification().getId();
        assetSpecificationDto.categorySpecificationName = assetSpecification.getCategorySpecification() == null ? null : assetSpecification.getCategorySpecification().getName();
        assetSpecificationDto.content = assetSpecification.getContent();
        return assetSpecificationDto;
    }
}
