package pl.reskilled.menteeManagerMicroservices.user.security.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.User;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegisterToUser(SignUpDto register) {

        final User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setAuthorities(register.getAuthorities());
        return user;
    }
}
