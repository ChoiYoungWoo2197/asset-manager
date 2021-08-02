package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//자산렌탈내역
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class AssetRentalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_rental_log_id")
    private Long id;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "asset_code")
    private Asset asset;

    private LocalDate contractedDateAt;

    private LocalDate expiredDateAt;

    private Long rentalCount;

    private LocalDate returnedDateAt;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public AssetRentalLog() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }


    @Builder
    public AssetRentalLog(Asset asset, LocalDate contractedDateAt, LocalDate expiredDateAt, Long rentalCount,
            LocalDate returnedDateAt, String register, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.asset = asset;
        this.contractedDateAt = contractedDateAt;
        this.expiredDateAt = expiredDateAt;
        this.rentalCount = rentalCount;
        this.returnedDateAt = returnedDateAt;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
