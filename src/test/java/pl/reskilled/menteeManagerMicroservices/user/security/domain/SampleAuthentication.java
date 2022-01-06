package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.UserDetailsImpl;

public interface SampleAuthentication {

    default UsernamePasswordAuthenticationToken sampleAuthentication(UserDetailsImpl user) {
        return new UsernamePasswordAuthenticationToken(user, null);
    }
}
