package pl.reskilled.menteeManagerMicroservices.user.exception.api.valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class MessageResponse {

    public final String message;
    public final HttpStatus status;
}
