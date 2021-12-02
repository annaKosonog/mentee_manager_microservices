package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Authority implements GrantedAuthority {
    USER,
    MENTEE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
