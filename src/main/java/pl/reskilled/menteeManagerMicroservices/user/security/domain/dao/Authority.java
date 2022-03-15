package pl.reskilled.menteeManagerMicroservices.user.security.domain.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@ToString
public enum Authority {

    STUDENT,
    MENTOR;

    public static Authority fetchValue() {
        return Arrays.stream(Authority.values())
                .filter(e -> e.name().equals("MENTOR"))
                .findFirst()
                .orElse(Authority.STUDENT);
    }
}
