package pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final String ERROR_UNAUTHORIZED = "Error: Unauthorized";
    private static final String UNAUTHORIZED_ERROR = "Unauthorized error: {}";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error(UNAUTHORIZED_ERROR, authException.getMessage());
        response.sendError(response.SC_UNAUTHORIZED, ERROR_UNAUTHORIZED);
    }
}
