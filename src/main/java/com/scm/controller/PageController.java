package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page controller");
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn code with Durgesh");
        model.addAttribute("githubRepo", "https://github.com/pkabra1");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);
        System.out.println("About Page Loading...");
        return "about";
    }

    // services route
    @RequestMapping("/services")
    public String services(Model model) {
        System.out.println("services page loading...");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
}
