package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;


//자산변경내역
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class AssetUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_use_log_id")
    private Long id;

    /**
     * N:1, 단방향, 연관관계(주인)
     **/
    @ManyToOne
    @JoinColumn(name = "asset_code")
    private Asset asset;

    @Column(length = 10)
    private String type;

    private String content;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;

    public AssetUseLog() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public AssetUseLog(Asset asset, String type, String content, String register, LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.asset = asset;
        this.type = type;
        this.content = content;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
