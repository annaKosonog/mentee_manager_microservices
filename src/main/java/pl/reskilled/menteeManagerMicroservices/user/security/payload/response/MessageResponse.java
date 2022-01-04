package pl.reskilled.menteeManagerMicroservices.user.security.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public class MessageResponse {

    private final String message;
    private final HttpStatus status;
}
