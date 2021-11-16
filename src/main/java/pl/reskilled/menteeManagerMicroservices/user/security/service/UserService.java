package pl.reskilled.menteeManagerMicroservices.user.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.UserDto;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User registerNewUserAccount(UserDto register) {
        final User user = userMapper.mapRegister(register);
        return userRepository.save(user);
    }

}
