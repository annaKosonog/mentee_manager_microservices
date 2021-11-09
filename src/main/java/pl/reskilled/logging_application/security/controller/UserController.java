package pl.reskilled.logging_application.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reskilled.logging_application.security.model.UserDto;

@Controller
public class UserController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("userForm", new UserDto());
        return "register";
    }

    @PostMapping("/login")
    public String showLoginForm(){
        return "login";
    }

}
