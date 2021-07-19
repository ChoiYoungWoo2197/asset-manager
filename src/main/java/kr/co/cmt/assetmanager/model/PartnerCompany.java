package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//업체
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class PartnerCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_company_id")
    private Long id;

//    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

//    @NotBlank
    @Column(length = 100, unique = true, nullable = false)
    private String code;

    @Column(length = 20)
    private String type;

    @Column(length = 20)
    private String companyPhone;

    private String remark;

    @Column(length = 1)
    private Boolean useYn;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;


    public PartnerCompany() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }
}
