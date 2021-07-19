package kr.co.cmt.assetmanager.model;

import java.io.Serializable;

//자산상세규격 복합키 설정(테이블 아님)
public class AssetSpecificationId implements Serializable {

    private Long asset;
    private Long categorySpecification;

    public AssetSpecificationId() {

    }
    public AssetSpecificationId(Long asset, Long categorySpecification) {
        this.asset = asset;
        this.categorySpecification = categorySpecification;
    }

}
