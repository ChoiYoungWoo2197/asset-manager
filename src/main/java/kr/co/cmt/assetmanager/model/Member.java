package kr.co.cmt.assetmanager.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

//계정
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Member  implements UserDetails {
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


    //"/logins/sign-in"로 요청하면 시큐리티가 인터셉트 한다.(컨트롤러를 생성 안해도 되는거 같네..)
    // 로그인을 성공하면 시큐리티 session을 만듬 (Security ContextHolder ). 일반 세션이랑은 다른가?
    // 저 세션에다가 저장하기 위해선 타입이 Authentication객체 타입이여야 함. >> 오키 이건 정해진 객체가 있네
    // Authentication객체 내부에 User정보가 있어야 한다. >> 이것도 정해진 객체
    // User객체는 UserDetails객체 이다.

    // 결론은 세션에다가 Authentical <- UserDetails(PrincipalDetails)를 넣어야 한다.

    //유저에 속한 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return authority.getCode();
            }
        });

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
