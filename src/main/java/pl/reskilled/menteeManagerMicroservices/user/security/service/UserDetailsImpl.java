package pl.reskilled.menteeManagerMicroservices.user.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;

import java.util.Collection;


public class UserDetailsImpl extends User implements UserDetails {

    public UserDetailsImpl(String username, String email, String password) {
        super(username, email, password);
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
