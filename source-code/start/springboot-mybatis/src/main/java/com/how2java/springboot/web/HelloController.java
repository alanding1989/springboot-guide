package com.how2java.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/boot")
    public String hello(Model m) {
        m.addAttribute("name", "thymeleaf");
        return "boot";
    }
}
