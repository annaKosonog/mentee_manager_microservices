package pl.reskilled.menteeManagerMicroservices.user.exception.api.valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LoginValidationResponse {

    public final HttpStatus status;
    public final List<String> message;
}
