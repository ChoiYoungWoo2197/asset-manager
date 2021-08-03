package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//자산상세규격
@Data
//@AllArgsConstructor
@IdClass(AssetSpecificationId.class)
@Entity
public class AssetSpecification {
    @Id
    @ManyToOne
    @JoinColumn(name = "asset_code")
    private Asset asset;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_specification_id")
    private CategorySpecification categorySpecification;

    private String content;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public AssetSpecification() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public AssetSpecification(Asset asset, CategorySpecification categorySpecification, String content, String register,
                              LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.asset = asset;
        this.categorySpecification = categorySpecification;
        this.content = content;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }

}
