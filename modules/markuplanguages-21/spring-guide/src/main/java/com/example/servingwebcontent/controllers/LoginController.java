package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.representation.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/logme", method = RequestMethod.POST)
    public String login(String email,String password, Model model){
        User u = new User(email,password);
        model.addAttribute("email",u.getEmail());
        model.addAttribute("password",u.getPassword());
        return "welcome";
    }


}
