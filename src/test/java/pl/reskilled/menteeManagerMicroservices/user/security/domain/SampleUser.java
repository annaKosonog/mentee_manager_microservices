package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.User;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.UserDetailsImpl;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface SampleUser {

    UserMapper userMapper = null;

    default UserDetailsImpl getUser(String id, String email, String username, String password, Set<Authority> roles) {
        Set<GrantedAuthority> roleSet = new HashSet<>();
        roleSet.add(new SimpleGrantedAuthority("STUDENT"));
        return new UserDetailsImpl(id, email, password, roleSet);
    }

    default User userParametersWithoutId(String name, String email, String password, Set<Authority> authorities) {
        final User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(authorities);
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
