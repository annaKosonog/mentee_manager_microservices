package pl.reskilled.menteeManagerMicroservices.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import pl.reskilled.menteeManagerMicroservices.user.model.SampleMessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.model.SampleSignUp;
import pl.reskilled.menteeManagerMicroservices.user.model.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.MessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.controller.AuthController;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AuthControllerTest implements SampleSignUp, SampleUser, SampleMessageResponse {


    @Test
    public void should_return_add_user_to_db() {
        //GIVEN
        ResponseEntity<MessageResponse> expectedResponse = okMessageResponse("User registered successfully!");
        final UserRepository userRepository = mock(UserRepository.class);
        final UserMapper userMapper = mock(UserMapper.class);
        final UserService userService = new UserService(userRepository, userMapper);


        final SignUpDto signUpDto= allParameterSignUpDto("user", "user@contact.pl", "test1");
        final User saveToDb = userService.registerNewUserAccount(signUpDto);

        final AuthController authController = new AuthController(null, userRepository, userService);

        //WHEN
        final ResponseEntity<MessageResponse> actualResponse = authController.registerNewUser(signUpDto);

        //THEN
        assertThat(actualResponse.toString()).isEqualTo(expectedResponse.toString());
    }
}
