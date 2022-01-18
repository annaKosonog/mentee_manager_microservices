package pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleRefreshToken;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.RefreshToken;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.MongoDetailsServiceImpl;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.RefreshTokenService;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.RefreshTokenRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class JwtTestConfig implements SampleRefreshToken {

    @Value("${app.jwt.secret:testExampleSecret}")
    String jwtSecret;

    @Value("${app.jwt.expiration.time:60000}")
    int jwtExpirationTime;

    @Value("${app.bezkoder.app.jwtRefreshExpirationMs:120000}")
    Long refreshTokenDurationMs;


    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return mock(AuthenticationEntryPoint.class);
    }

    @Bean
    AuthEntryPointJwt authEntryPointJwt() {
        return mock(AuthEntryPointJwt.class);
    }

    @Bean
    JwtUtils jwtUtils() {
        JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.validateJwtToken(any())).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(any())).thenReturn("user");
        //  when(jwtUtils.generateJwtToken(any())).thenReturn();
        return jwtUtils;
    }


    @Bean
    AuthTokenFilter authTokenFilter() {
        AuthTokenFilter authTokenFilter = mock(AuthTokenFilter.class);
        when(authTokenFilter.parseJwt(any())).thenReturn("jwt");
        return authTokenFilter;
    }

    @Bean
    AuthTokenFilter filter() {
        return new AuthTokenFilter(jwtUtils(), mongoDetailsServiceImp());
    }

    @Bean
    MongoDetailsServiceImpl mongoDetailsServiceImp() {
        UserRepository userRepository = mock(UserRepository.class);
        return new MongoDetailsServiceImpl(userRepository) {
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return new User("soki@hortex.pl", "Jan123", Collections.singleton(new SimpleGrantedAuthority("STUDENT")));
            }
        };
    }

    @Bean
    RefreshTokenService refreshTokenService() {
        RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        return new RefreshTokenService(refreshTokenRepository, userRepository) {
            public Optional<RefreshToken> findByToken(String token) {
                return Optional.of(refreshTokenRepository.save(generateSimpleRefreshToken()));
            }

        };
    }
}
