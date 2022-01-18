package pl.reskilled.menteeManagerMicroservices.user.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleAuthentication;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleJwtResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleLoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.LoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.JwtUtils;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.UserDetailsImpl;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.UserService;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.JwtResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority.STUDENT;

public class AuthControllerTest implements SampleLoginDto, SampleUser, SampleJwtResponse, SampleAuthentication {


    @Test
    public void should_return_correct_response_with_generated_jwt_token_and_status() {
        //GIVEN
        final ResponseEntity<JwtResponse> expectedResponse = sampleJwtResponse("token", "jwtToken","1234dsa","wacek", "admin@wp.pl", Collections.singleton("STUDENT"));
        final UserDetailsImpl principal = getUser("1234dsa", "admin@wp.pl", "wacek", "adminpassword", Collections.singleton(STUDENT));
        final Authentication authentication = sampleAuthentication(principal);

        final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationManager.authenticate((any()))).thenReturn(authentication);

        final JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.generateJwtToken(principal)).thenReturn("jwtToken");

        final SecurityContext securityContextHolder = mock(SecurityContext.class);
        when(securityContextHolder.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContextHolder);

        final UserRepository userRepository = mock(UserRepository.class);
        final UserMapper userMapper = mock(UserMapper.class);
        final UserService userService = new UserService(userRepository, userMapper);


        final LoginDto loginDto = allParameterLoginDto("user", "user@contact.pl");
        final AuthController authController = new AuthController(authenticationManager, userRepository, jwtUtils, userService, null);

        //WHEN
        final ResponseEntity<JwtResponse> actualResponse = authController.authenticateUser(loginDto);

        //THEN
        assertThat(actualResponse.toString()).isEqualTo(expectedResponse.toString());
    }
}
