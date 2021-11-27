package pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;

public interface SampleLoginRequestDto {

    default LoginRequestDto loginRequestDtoWithoutId(String name, String email, String password){
        return new LoginRequestDto(name, email,password);
    }

    default LoginRequestDto userTestDto(){
        return loginRequestDtoWithoutId("test", "test@contact.pl", "test1");
    }
}
