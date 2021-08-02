package com.example.springboot_security403;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/teacher")
    public String admin(){
        return "teacher";
    }
    @RequestMapping("/student")
    public String user(){
        return "student";
    }

}
