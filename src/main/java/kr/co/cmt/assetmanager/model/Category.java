package kr.co.cmt.assetmanager.model;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade ={CascadeType.ALL})
    private List<Category> children = new ArrayList<>();

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

    @Builder
    public Category(Long id, Category parent, String name, String code, String remark, Boolean useYn,
                    String register, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.code = code;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
