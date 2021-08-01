package kr.co.cmt.assetmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/")
public class TestController {

    @GetMapping
    public String index() {
        return "/index.html";
    }

    @GetMapping(value = "/logins")
    public String login()  {
        return "/login.html";
    }

    @GetMapping(value = "/login-success")
    public String loginSuccess() {
        return "/login-success.html";
    }
}
