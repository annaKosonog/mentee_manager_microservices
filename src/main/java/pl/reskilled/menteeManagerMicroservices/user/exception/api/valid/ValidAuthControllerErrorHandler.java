package pl.reskilled.menteeManagerMicroservices.user.exception.api.valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidAuthControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class) // #2
    @ResponseStatus(HttpStatus.BAD_REQUEST) // #3
    @ResponseBody
    public ValidationErrorResponse validationError(ConstraintViolationException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        exception.getConstraintViolations().stream().map(error -> new ValidationError(
                error.getPropertyPath().toString(),
                error.getMessage())).forEach(response::addError);

        return response;
    }

}
