package pl.reskilled.menteeManagerMicroservices.user.mapper;

import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;

public interface SampleUserDto {

    default LoginRequestDto allParameterUserDto(String name, String email, String password){
        return new LoginRequestDto(name, email,password);
    }

    default LoginRequestDto afterSaveDb(){
        return allParameterUserDto("Wacek", "test@example.pl", "test1");
    }
}
