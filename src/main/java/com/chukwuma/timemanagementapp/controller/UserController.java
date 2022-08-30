package com.chukwuma.timemanagementapp.controller;


import com.chukwuma.timemanagementapp.model.Task;
import com.chukwuma.timemanagementapp.model.User;
import com.chukwuma.timemanagementapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

//    @ModelAttribute("user")
//    public User login(){
//        return new User();
//    }
//    @ModelAttribute("register")
//    public User register(){
//        return new User();
//    }

    @GetMapping({"/","/login"})
    public String showLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login-user")
    public String Login(@ModelAttribute("user") User user, HttpSession session){
        User loginUser = userService.login(user.getEmail(), user.getPassword());
        if (loginUser != null){
            System.out.println(user.getEmail() + user.getPassword());
            System.out.println(loginUser);
            session.setAttribute("userSession", loginUser.getFirstName());
            return "redirect:/index";
        }
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model){
        model.addAttribute("register", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String Register(@ModelAttribute("register") User user){
       userService.save(user);
       return "redirect:/login";
    }

    @GetMapping("/log-out")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/index")
    public String homePage(@ModelAttribute ("task") Task task){
        return "index";
    }

}
