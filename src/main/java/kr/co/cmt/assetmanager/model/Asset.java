package kr.co.cmt.assetmanager.model;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//자산
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Asset {
    @Id
    @Column(name = "asset_code")
    private String code;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "partner_company_id")
    private PartnerCompany partnerCompany;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * 1:N, 단방향
     **/
    @OneToMany(mappedBy = "asset")
    private List<AssetSpecification> assetSpecificationList = new ArrayList<>();

//    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10)
    private String type;

    private LocalDate buyDateAt;

    private LocalDate receivedDateAt;

    @Column(length = 30)
    private String count;

    @Column(length = 30)
    private  String price;

    private String remark;

    @Column(length = 1)
    private Boolean useYn;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public Asset() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public Asset(String code, Category category, PartnerCompany partnerCompany, Member member, Department department,
                 String name, String count, String price, String type, LocalDate buyDateAt, LocalDate receivedDateAt,
                 String remark, Boolean useYn, String register, LocalDate registedDateAt,
                 LocalDate updatedDateAt) {
        this.code = code;
        this.category = category;
        this.partnerCompany = partnerCompany;
        this.member = member;
        this.department = department;
        this.name = name;
        this.count = count;
        this.price = price;
        this.type = type;
        this.buyDateAt = buyDateAt;
        this.receivedDateAt = receivedDateAt;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
