package pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;

public interface SampleUser {

    UserMapper userMapper = null;

    default LoginRequestDto userDto(String name, String email, String password) {
        return new LoginRequestDto(name, email, password);
    }

    default User userParameterWithoutId(String name, String email, String password) {
        final User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    default User allParameters(String id, String name, String email, String password) {
        return new User(id, name, email, password);
    }

    default User saveDb() {
        return allParameters("619568ac09f09b5a3c24d6d1", "Wacek", "zdzislaw@onet.pl", "test1");
    }

    default User beforeSaveDb() {
        return userParameterWithoutId("Wacek", "zdzislaw@onet.pl", "test1");
    }
}
