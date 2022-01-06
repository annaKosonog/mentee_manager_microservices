package pl.reskilled.menteeManagerMicroservices.user.security.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.RefreshToken;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.LogOutDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.LoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.TokenRefreshRequest;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.TokenRefreshException;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.JwtUtils;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.RefreshTokenService;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.UserDetailsImpl;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.UserService;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.JwtResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.MessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.TokenRefreshResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        LOGGER.info("------------ auth 1 -------- ");


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);
        LOGGER.info("                      ");
        LOGGER.info("Bearer " + jwt);


        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        LOGGER.info("                      ");
        LOGGER.info("\b You have logged in correctly");

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }


    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerNewUser(@Valid @RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!", HttpStatus.CONFLICT));
        }
        userService.registerNewUserAccount(signUpDto);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!", HttpStatus.CREATED));
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.getUserNameFromJwtToken(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutDto logOutDto) {
        refreshTokenService.deleteByUserId(logOutDto.getUserId());
        LOGGER.info("\b You logged out correctly");
        return ResponseEntity.ok(new MessageResponse("Log out successful!", HttpStatus.OK));
    }
}
