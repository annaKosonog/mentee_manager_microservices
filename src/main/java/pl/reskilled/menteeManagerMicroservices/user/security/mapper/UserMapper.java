package pl.reskilled.menteeManagerMicroservices.user.security.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.user.security.model.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegisterToUser(SignUpDto register) {
        return User.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .authorities(register.getAuthorities())
                .build();
    }

    public SignUpDto mapToSignUpDto(User from) {
        return SignUpDto.builder()
                .username(from.getUsername())
                .email(from.getEmail())
                .password(from.getPassword())
                .authorities(from.getAuthorities())
                .build();
    }
}
