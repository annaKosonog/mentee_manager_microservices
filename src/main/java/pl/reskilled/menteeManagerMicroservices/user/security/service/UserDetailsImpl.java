package pl.reskilled.menteeManagerMicroservices.user.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;


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
