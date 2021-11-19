package pl.reskilled.menteeManagerMicroservices.user.security.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.UserDto;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User registerNewUserAccount(UserDto register) {
        if (register.getPassword().equals(register.getConfirmPassword())) {
            User user = userMapper.mapRegister(register);
            LOG.info("Saving user.");
            return userRepository.save(user);
        } else {
            LOG.info("You entered incorrect data: " + register.getEmail());
        }
        return null;
    }

}
