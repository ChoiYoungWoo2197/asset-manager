//package kr.co.cmt.assetmanager;
//
//import kr.co.cmt.assetmanager.model.Authority;
//import kr.co.cmt.assetmanager.model.Department;
//import kr.co.cmt.assetmanager.model.Member;
//import kr.co.cmt.assetmanager.repository.AuthorityRepository;
//import kr.co.cmt.assetmanager.repository.DepartmentRepository;
//import kr.co.cmt.assetmanager.repository.MemberRepository;
//import org.junit.Assert;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.Optional;
//
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@Transactional
//public class MemberTest {
//    @Autowired
//    private AuthorityRepository authorityRepository;
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    public void init() {
//        Department department = new Department(1L, null,"개발팀",null, "developer", "비고",
//                true, "32", LocalDate.now(), LocalDate.now());
//
//        departmentRepository.save(department);
//
//        Authority authority = new Authority(1L, "관리자", "admin", "비고비고", true,
//                "32", LocalDate.now(), LocalDate.now());
//
//        authorityRepository.save(authority);
//    }
//
//    @Test
//    @Order(1)
//    public void create() {
////        init();
////        Member member = new Member(null, null, null, "duddn2197@cmtinfo.co.kr", "dsfkjsdfle", "최영우", LocalDate.of(1992,8,11),
////                "사원", "010-2032-2197", "비고", true, "32", LocalDate.now(), LocalDate.now());
////        member.setAuthority(authorityRepository.findAll().get(0));
////        member.setDepartment(departmentRepository.findAll().get(0));
////        memberRepository.save(member);
////
////        Member entity = memberRepository.findById(Long.parseLong("1")).get();
////        System.out.println(entity.toString());
////
////        Assert.assertEquals("최영우", entity.getName());
//    }
//
//    @Test
//    @Order(2)
//    public void read() {
//        init();
//        Member member = new Member(null, null, null, "duddn2197@cmtinfo.co.kr", "dsfkjsdfle", "최영우", LocalDate.of(1992,8,11),
//                "사원", "010-2032-2197", "비고", true, "32", LocalDate.now(), LocalDate.now());
//        member.setAuthority(authorityRepository.findAll().get(0));
//        member.setDepartment(departmentRepository.findAll().get(0));
//        memberRepository.save(member);
//
//        Optional<Member> entity = memberRepository.findById(1L);
//        entity.ifPresent(selectMember -> {
//            System.out.println("name : " + selectMember.getName());
//            System.out.println("email : " + selectMember.getEmail());
//        });
//    }
//
//    @Test
//    @Order(3)
//    public void update(){
//        init();
//        Member member = new Member(null, null, null, "duddn2197@cmtinfo.co.kr", "dsfkjsdfle", "최영우", LocalDate.of(1992,8,11),
//                "사원", "010-2032-2197", "비고", true, "32", LocalDate.now(), LocalDate.now());
//        member.setAuthority(authorityRepository.findAll().get(0));
//        member.setDepartment(departmentRepository.findAll().get(0));
//        memberRepository.save(member);
//
//        Optional<Member> entity = memberRepository.findById(1L);
//        entity.ifPresent(selectMember -> {
//            selectMember.setPassword("1111111111");
//            selectMember.setPosition("대리");
//            selectMember.setUpdatedDateAt(LocalDate.now());
//            memberRepository.save(selectMember);
//
//            Optional<Member> entity2 = memberRepository.findById(1L);
//            Assert.assertEquals(selectMember.getPassword(), entity2.get().getPassword());
//        });
//    }
//
//    @Test
//    @Order(4)
//    public void delete() {
//        init();
//        Member member = new Member(null, null, null, "duddn2197@cmtinfo.co.kr", "dsfkjsdfle", "최영우", LocalDate.of(1992,8,11),
//                "사원", "010-2032-2197", "비고", true, "32", LocalDate.now(), LocalDate.now());
//        member.setAuthority(authorityRepository.findAll().get(0));
//        member.setDepartment(departmentRepository.findAll().get(0));
//        memberRepository.save(member);
//
//        Optional<Member> entity = memberRepository.findById(1L);
//        entity.ifPresent(selectMember -> {
//            memberRepository.delete(selectMember);
//        });
//
//        Optional<Member> deleteEntity = memberRepository.findById(1L);
//
//        if(deleteEntity == null) {
//            System.out.println("삭제됨.");
//        }
//    }
//}
