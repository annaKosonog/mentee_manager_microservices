package pl.reskilled.menteeManagerMicroservices.user.security.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.reskilled.menteeManagerMicroservices.user.security.MessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LOGGER.info("------------ auth 1 -------- ");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    ResponseEntity<MessageResponse> registerNewUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        if (userRepository.existsByEmail(loginRequestDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        userService.registerNewUserAccount(loginRequestDto);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
