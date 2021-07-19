package kr.co.cmt.assetmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class TestController {

    @GetMapping
//    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String index()  {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("message", "Index");
//        mav.setViewName("index");
        return "/index.html";
    }
}
