package kr.co.cmt.assetmanager.dto;

import kr.co.cmt.assetmanager.model.AssetSpecification;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AssetSpecificationDto {
    String assetCode;
    String categorySpecificationId;
    String content;

    @Builder
    public AssetSpecificationDto(String assetCode, String categorySpecificationId, String content) {
        this.assetCode = assetCode;
        this.categorySpecificationId = categorySpecificationId;
        this.content = content;
    }

    public static AssetSpecificationDto convertEntityToDto(AssetSpecification assetSpecification) {
        AssetSpecificationDto assetSpecificationDto = new AssetSpecificationDto();
        assetSpecificationDto.assetCode = assetSpecification.getAsset() == null ? null : assetSpecificationDto.getAssetCode();
        assetSpecificationDto.categorySpecificationId = assetSpecification.getCategorySpecification() == null ? null : assetSpecificationDto.getCategorySpecificationId();
        assetSpecificationDto.content = assetSpecificationDto.getContent();
        return assetSpecificationDto;
    }
}
