package pl.reskilled.menteeManagerMicroservices.user.exception.api.valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class ValidationErrorResponse {

    private List<ValidationError> errors = new ArrayList<>();


    public ValidationErrorResponse addError(ValidationError error) {
        this.errors.add(error);
        return this;
    }
}
