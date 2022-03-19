package pl.reskilled.menteeManagerMicroservices.user.security.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
@ToString
@AllArgsConstructor
public enum Authority {

    STUDENT("student"),
    MENTOR("mentor");

    private final String role;

    public static Authority fetchValue(String role) {
        return Stream.of(values())
                .filter(authority -> authority.name().equals(role.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
