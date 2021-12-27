package pl.reskilled.menteeManagerMicroservices.user.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.User;

import java.util.Set;


public class UserDetailsImpl extends User implements UserDetails {

    public UserDetailsImpl(String email, String password, Set<Authority> authorities) {
        super(email, password, authorities);
    }


    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
