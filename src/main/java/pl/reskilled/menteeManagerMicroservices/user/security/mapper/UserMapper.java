package pl.reskilled.menteeManagerMicroservices.user.security.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.UserDto;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegister(UserDto register) {
        final User user = new User();
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        return user;
    }
}
