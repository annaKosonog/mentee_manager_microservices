package pl.reskilled.menteeManagerMicroservices.user.model;

import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.LoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.User;

import java.util.Collections;
import java.util.Set;

public interface SampleUser {

    UserMapper userMapper = null;

    default LoginDto userDto(String email, String password) {
        return new LoginDto(email, password);
    }

    default User userParametersWithoutId(String name, String email, String password, Set<Authority> authorities) {
        final User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAuthorities(authorities);
        return user;
    }

    default User allParameters(String id, String name, String email, String password, Set<Authority> authorities) {
        return new User(id, name, email, password, authorities);
    }

    default User saveDb() {
        return allParameters("619568ac09f09b5a3c24d6d1", "Wacek", "zdzislaw@onet.pl", "test1", Collections.singleton(Authority.STUDENT));
    }

    default User beforeSaveDb() {
        return userParametersWithoutId("Wacek", "zdzislaw@onet.pl", "test1", Collections.singleton(Authority.STUDENT));
    }
}
