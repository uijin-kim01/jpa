package com.jjang051.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping({"/","/home","/index/index"})
@Controller
public class HomeController {

    @GetMapping({"/","/home","/index/index"})
    public String index(){
        return "/index/index";
    }
}
