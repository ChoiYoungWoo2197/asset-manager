package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//자산할당현황
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class AssetAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_allocation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_code")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String location;

    private LocalDate returnedDateAt;

    @Column(length = 1)
    private Boolean useYn;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public AssetAllocation() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }
}
