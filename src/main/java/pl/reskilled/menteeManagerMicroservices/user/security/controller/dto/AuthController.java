package pl.reskilled.menteeManagerMicroservices.user.security.controller.dto;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.reskilled.menteeManagerMicroservices.user.security.MessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserDetailsImpl;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        LOGGER.info("------------ auth 1 -------- ");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }


    @PostMapping("/students/add")
    public ResponseEntity<MessageResponse> registerAddNewUser(@Valid @RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        userService.registerNewUserAccount(signUpDto);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/students")
    public ResponseEntity<List<SignUpDto>> getAllStudents() {
        return ResponseEntity.ok(userService.getAllStudents());
    }
}