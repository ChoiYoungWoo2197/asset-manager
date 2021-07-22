package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//부서
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "deparatment_id")
    private Department parent;

    @OneToMany(mappedBy = "parent", cascade ={CascadeType.ALL})
    private List<Department> children = new ArrayList<>();

//    private Long parentId;

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

    public Department() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public Department(Long id, Department parent, String name, String code, String remark, Boolean useYn, String register,
                      LocalDate registedDateAt, LocalDate updatedDateAt) {
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
