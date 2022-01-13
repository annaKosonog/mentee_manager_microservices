package pl.reskilled.menteeManagerMicroservices.user.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.User;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.SignUpDto;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User mapRegisterToUser(SignUpDto register) {

        final User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRoles(register.getRoles());
        return user;
    }
}
