package java.pl.reskilled.menteeManagerMicroservices.user.service;

import org.junit.jupiter.api.Test;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import java.pl.reskilled.menteeManagerMicroservices.user.model.SampleSignUp;
import java.pl.reskilled.menteeManagerMicroservices.user.model.SampleUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceAddNewUserTest implements SampleUser, SampleSignUp {

    UserRepository userRepository = mock(UserRepository.class);
    UserMapper userMapper = mock(UserMapper.class);

    UserService userService = new UserService(userRepository, userMapper);

    @Test
    public void should_return_add_new_user() {
        //GIVEN
       when(userRepository.save(beforeSaveDb())).thenReturn(saveDb());
        //WHEN
        final User newUser = userService.registerNewUserAccount(registerUser());
        //THEN
        verify(userRepository, times(1)).save(any());
    }
}