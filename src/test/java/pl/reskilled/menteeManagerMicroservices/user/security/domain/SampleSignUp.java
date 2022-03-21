package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.SignUpDto;

import java.util.Collections;
import java.util.Set;

public interface SampleSignUp {

    default SignUpDto allParameterSignUpDto(String username, String email, String password, Set<String> authorities) {
        return new SignUpDto(username, email, password, authorities);
    }

    default SignUpDto registerUser() {
        return allParameterSignUpDto("Wacek", "soki@hortex.pl", "Jan123", Collections.singleton(Authority.valueOf("STUDENT").toString()));
    }
}
