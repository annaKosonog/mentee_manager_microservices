package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.SignUpDto;

import java.util.Collections;
import java.util.Set;

public interface SampleSignUp {

    default SignUpDto allParameterSignUpDto(String username, String email, String password, Set<Authority> authorities) {
        return new SignUpDto(username, email, password, authorities);
    }

    default SignUpDto registerUser() {
        return allParameterSignUpDto("test", "test@example.pl", "test1", Collections.singleton(Authority.STUDENT));
    }
}
