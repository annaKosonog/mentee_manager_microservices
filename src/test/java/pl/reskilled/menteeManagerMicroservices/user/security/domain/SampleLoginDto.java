package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.LoginDto;

public interface SampleLoginDto {

    default LoginDto allParameterLoginDto(String email, String password){
        return new LoginDto( email,password);
    }

    default LoginDto userTestDto(){
        return allParameterLoginDto( "test@contact.pl", "test1");
    }
    default LoginDto userExistsWithDb(){
        return allParameterLoginDto("soki@hortex.pl", "Jan123");
    }
}
