package pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.model.SignUpDto;

public interface SampleSignUp {

    default SignUpDto allParameterSignUpDto(String username, String email, String password){
        return new SignUpDto(username,email,password);
    }

    default SignUpDto registerUser(){
        return allParameterSignUpDto("test", "test@example.pl", "test1");
    }
}
