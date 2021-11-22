package pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.UserDto;

public interface SampleUser {

    UserMapper userMapper = null;

    default UserDto userDto(String name, String email, String password, String confirmPassword) {
        return new UserDto(name, email, password, confirmPassword);
    }

    default User userParameterWithoutId(String name, String email, String password, String confirmPassword) {
        final User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        return user;
    }

    default User allParameters(String id, String name, String email, String password, String confirmPassword) {
        return new User(id, name, email, password, confirmPassword);
    }

    default User saveDb() {
        return allParameters("619568ac09f09b5a3c24d6d1", "Wacek", "zdzislaw@onet.pl", "test1", "test1");
    }

    default User beforeSaveDb() {
        return userParameterWithoutId("Wacek", "zdzislaw@onet.pl", "test1", "test1");
    }
}
