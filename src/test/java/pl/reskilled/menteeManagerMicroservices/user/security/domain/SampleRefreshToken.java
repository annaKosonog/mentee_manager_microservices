package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.RefreshToken;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.User;

import java.time.Instant;

public interface SampleRefreshToken extends SampleUser {

    default RefreshToken allRefreshToken(String id, User user, String token, Instant expireDate) {
        return new RefreshToken(id, user, token, expireDate);
    }

    default RefreshToken generateSimpleRefreshToken() {
        return allRefreshToken("61d4737ba295b275a0eb9db5", exampleUser(), "john", Instant.now());
    }
}
