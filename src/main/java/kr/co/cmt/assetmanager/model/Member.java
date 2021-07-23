package kr.co.cmt.assetmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

//계정
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

     /**
     * N:1, 단방향, 연관관계(주인)
     **/
     @ManyToOne
     @JoinColumn(name = "authority_id")
     private Authority authority;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

//    @NotBlank
    @Column(length = 100, unique = true, nullable = false)
    private String email;

//    @NotBlank
    @Column(length = 100, nullable = false)
    private String password;

//    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    private LocalDate birthday;

//    @NotBlank
    @Column(length = 20, nullable = false)
    private String position;

    @Column(length = 20)
    private String phone;

    private String remark;

    @Column(length = 1)
    private Boolean useYn;

    @Column(length = 20)
    private String register;

    private LocalDate registedDateAt;

    private LocalDate updatedDateAt;


    public Member() {
        this.registedDateAt = LocalDate.now();
        this.updatedDateAt = LocalDate.now();
    }

    @Builder
    public Member(Long id, Authority authority, Department department, String email, String password, String name,
                     LocalDate birthday, String position, String phone, String remark, Boolean useYn, String register,
                     LocalDate registedDateAt, LocalDate updatedDateAt) {
        this.id = id;
        this.authority = authority;
        this.department = department;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.position = position;
        this.phone = phone;
        this.remark = remark;
        this.useYn = useYn;
        this.register = register;
        this.registedDateAt = registedDateAt;
        this.updatedDateAt = updatedDateAt;
    }
}
