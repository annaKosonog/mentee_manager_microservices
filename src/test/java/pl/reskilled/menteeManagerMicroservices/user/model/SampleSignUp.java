package java.pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.model.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.model.SignUpDto;

import java.util.Collections;
import java.util.Set;

public interface SampleSignUp {

    default SignUpDto allParameterSignUpDto(String username, String email, String password, Set<Authority> roles){
        return new SignUpDto(username,email,password, roles);
    }

    default SignUpDto registerUser(){
        return allParameterSignUpDto("test", "test@example.pl", "test1", Collections.singleton(Authority.STUDENT));
    }
}
