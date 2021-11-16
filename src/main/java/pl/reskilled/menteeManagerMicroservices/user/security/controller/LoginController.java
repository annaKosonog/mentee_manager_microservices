package pl.reskilled.menteeManagerMicroservices.user.security.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reskilled.menteeManagerMicroservices.user.security.model.UserDto;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute UserDto userDto) {
        userService.registerNewUserAccount(userDto);
        LOG.info("Saving user.");
        return "redirect:/login";

    }

    @PostMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
