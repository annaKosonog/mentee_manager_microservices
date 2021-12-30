package pl.reskilled.menteeManagerMicroservices.user.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.User;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public User registerNewUserAccount(SignUpDto signUpDto) {
        final User user = userMapper.mapRegisterToUser(signUpDto);
        return userRepository.save(user);
    }


}
