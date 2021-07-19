package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;


//자산할당현황 변경내역
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class AssetAllocationUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_allocation_use_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_allocation_id")
    private AssetAllocation assetAllocation;

    @Column(length = 10)
    private String type;

    private String content;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public AssetAllocationUseLog() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }
}
