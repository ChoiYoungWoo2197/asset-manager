package kr.co.cmt.assetmanager.controller;

import kr.co.cmt.assetmanager.model.Member;
import kr.co.cmt.assetmanager.repository.MemberRepository;
import kr.co.cmt.assetmanager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api")
public class LoginController implements UserDetailsService {
    final private static Logger LOG = Logger.getGlobal();

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/logins")
    public String login(){
        LOG.info("GET successfully called on /login resource");
        return "login";
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<Boolean> signIn(@PathVariable("email") String email, @PathVariable("password") String password){
        System.out.println("임시로 해둠.");
        UserDetails userDetails = loadUserByUsername(email);

        return userDetails != null ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findMemberByEmail(email) != null ? memberRepository.findMemberByEmail(email) : null;
    }
}
