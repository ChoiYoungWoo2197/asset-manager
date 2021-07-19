package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//카테고리
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

//    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

//    @NotBlank
    @Column(length = 100, unique = true, nullable = false)
    private String code;

    private String remark;

    @Column(length = 1)
    private Boolean useYn;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public Category() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }
}
