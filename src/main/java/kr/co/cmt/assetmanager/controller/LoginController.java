package kr.co.cmt.assetmanager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/login/signup")
public class LoginController {


    @GetMapping
//    public String signUp(@PathVariable("email") String email, @PathVariable("password") String password){
    public String signUp(){
        System.out.println("임시로 해둠.");
        return "test";
    }
}
