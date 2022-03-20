package pl.reskilled.menteeManagerMicroservices.mentee.domain.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class MenteeErrorResponse {

    public final HttpStatus status;
    public final String message;
}
