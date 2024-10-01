package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

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
    public String register(Model model) {
        UserForm userForm = new UserForm();
        userForm.setName("Pranshu");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping(value = "/do-register")
    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session) {
        System.out.println("Proccessing Form");
        // fetch form data
        System.out.println(userForm.toString());
        // validate form data
        // TODO::Validate userForm
        // save to database
        // userForm --> User
        // User user = User.builder()
        //         .name(userForm.getName())
        //         .email(userForm.getEmail())
        //         .password(userForm.getPassword())
        //         .phoneNumber(userForm.getPhoneNumber())
        //         .profileLink("https://media.licdn.com/dms/image/v2/D4D03AQFU92-cqb4NvQ/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1668202755390?e=2147483647&v=beta&t=Zk5DOuv3zK58Yx_-KQfAXtUodumxsX4tfd51ONulPlw")
        //         .about(userForm.getAbout()).build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfileLink("https://media.licdn.com/dms/image/v2/D4D03AQFU92-cqb4NvQ/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1668202755390?e=2147483647&v=beta&t=Zk5DOuv3zK58Yx_-KQfAXtUodumxsX4tfd51ONulPlw");

        User savedUser = userService.saveUser(user);
        System.out.println("User saved");

        // message = "Registration Successful"
        // add the message:
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        // redirect to login page
        return "redirect:/register";
    }
}
