package pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.LoginDto;

public interface SampleLoginDto {

    default LoginDto allParameterLoginDto(String email, String password){
        return new LoginDto( email,password);
    }

    default LoginDto userTestDto(){
        return allParameterLoginDto( "test@contact.pl", "test1");
    }
}
