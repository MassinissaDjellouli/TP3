package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String getIndex(Model model){
        return "index";
    }
    @GetMapping("/users")
    public String getUsers(Model model){
        return "users";
    }
    @GetMapping("/employees")
    public String getEmployes(Model model){
        return "employees";
    }
    @GetMapping("/newLivre")
    public String getNewLivre(Model model){
        return "newLivre";
    }
    @GetMapping("/newMedia")
    public String getNewMedia(Model model){
        return "newMedia";
    }
}
