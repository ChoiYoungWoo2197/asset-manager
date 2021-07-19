package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//권한
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
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

    public Authority() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }
}
