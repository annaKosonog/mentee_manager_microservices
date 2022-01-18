package pl.reskilled.menteeManagerMicroservices.user.security.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class AuthErrorResponse {

    public final HttpStatus status;
    public final String message;
}

