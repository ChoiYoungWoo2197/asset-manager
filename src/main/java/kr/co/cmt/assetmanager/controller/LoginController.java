package kr.co.cmt.assetmanager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/logins")
public class LoginController {

    @GetMapping
    public String login(){
        System.out.println("임시로 해둠.");
        return "login";
    }

    @GetMapping(value = "/signin")
    public String signIn(){
        System.out.println("임시로 해둠.");
        return "login";
    }
}
