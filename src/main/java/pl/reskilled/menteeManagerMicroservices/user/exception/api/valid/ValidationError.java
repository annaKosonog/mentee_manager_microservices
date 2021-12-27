package pl.reskilled.menteeManagerMicroservices.user.exception.api.valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ValidationError {
    private final String field;
    private final String message;
}
