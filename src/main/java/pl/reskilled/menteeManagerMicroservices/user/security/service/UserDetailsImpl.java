package pl.reskilled.menteeManagerMicroservices.user.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.reskilled.menteeManagerMicroservices.user.security.model.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class UserDetailsImpl extends User implements UserDetails {

    public UserDetailsImpl(String email, String password) {
        super(email, password);
    }


    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> roles = getRoles();
        return roles.stream().map(role -> new SimpleGrantedAuthority
                (role.getAuthority())).
                collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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
