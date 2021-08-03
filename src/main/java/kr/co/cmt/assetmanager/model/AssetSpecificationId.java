package kr.co.cmt.assetmanager.model;

import java.io.Serializable;
import java.util.Objects;

//자산상세규격 복합키 설정(테이블 아님)
public class AssetSpecificationId implements Serializable {

    private String asset;
    private Long categorySpecification;



    public AssetSpecificationId() { }
   public AssetSpecificationId(String asset, Long categorySpecification) {
        this.asset = asset;
        this.categorySpecification = categorySpecification;
    }



}
