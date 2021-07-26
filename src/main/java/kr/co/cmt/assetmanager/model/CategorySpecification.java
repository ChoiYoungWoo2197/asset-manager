package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//카테고리 규격
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class CategorySpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_specification_id")
    private Long id;

    /**
     * N:1, 단방향, 연관관계(주인)
     * */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 1:N, 단방향
     * */
    @OneToMany(mappedBy = "categorySpecification")
    private List<AssetSpecification> assetSpecificationList = new ArrayList<>();

//    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 1)
    private Boolean useYn;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public CategorySpecification() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public CategorySpecification(Long id, Category category, String name, Boolean useYn, String register,
                                 LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
