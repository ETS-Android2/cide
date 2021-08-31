package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.representation.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPortal(){
        return "login";
    }

}
