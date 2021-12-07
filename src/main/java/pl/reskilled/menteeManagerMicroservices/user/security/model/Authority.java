package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Authority implements GrantedAuthority {


    STUDENT("student"),
    MENTEE("mentee");

    private String roles;

    Authority(String roles) {
        this.roles = roles;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
