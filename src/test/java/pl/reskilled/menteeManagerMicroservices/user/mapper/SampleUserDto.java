package pl.reskilled.menteeManagerMicroservices.user.mapper;

import pl.reskilled.menteeManagerMicroservices.user.security.model.UserDto;

public interface SampleUserDto {

    default UserDto allParameterUserDto(String name, String email, String password, String confirmPassword){
        return new UserDto(name, email,password, confirmPassword);
    }

    default UserDto afterSaveDb(){
        return allParameterUserDto("Wacek", "test@example.pl", "test1", "test1");
    }
}
