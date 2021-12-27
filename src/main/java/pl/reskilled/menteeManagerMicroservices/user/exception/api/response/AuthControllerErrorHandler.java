package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.reskilled.menteeManagerMicroservices.user.exception.api.valid.MessageResponse;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AuthControllerErrorHandler {


    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class, UserExistEmailException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public AuthErrorResponse userConflict(UserExistEmailException exception) {
        String message = "The given email already exists in the database: " + exception.getInfo();
        log.info("Conflict: {}", exception.getMessage());
        log.debug("Conflict: ", exception);
        return new AuthErrorResponse(HttpStatus.CONFLICT, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageResponse handleValidationExceptions(MethodArgumentNotValidException ex) {

        final List<String> message = getResponse(ex);
        log.info("Bad Request: {}", ex.getMessage());
        log.debug("Bad Request: ", ex);

        return new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    public List<String> getResponse(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
