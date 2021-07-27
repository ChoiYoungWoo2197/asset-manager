package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//업체담당자
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class PartnerCompanyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_company_member_id")
    private Long id;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "partner_company_id")
    private PartnerCompany partnerCompany;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    private String directPhone;

    @Column(length = 1)
    private Boolean useYn;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public PartnerCompanyMember() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public PartnerCompanyMember(Long id, PartnerCompany partnerCompany, String name, String phone, String directPhone,
                                 Boolean useYn, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.partnerCompany = partnerCompany;
        this.name = name;
        this.phone = phone;
        this.directPhone = directPhone;
        this.useYn = useYn;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
