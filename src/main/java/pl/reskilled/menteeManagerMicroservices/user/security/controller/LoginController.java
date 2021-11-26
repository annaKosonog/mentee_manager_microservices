package pl.reskilled.menteeManagerMicroservices.user.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new LoginRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute LoginRequestDto loginRequestDto) {
        userService.registerNewUserAccount(loginRequestDto);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

}
