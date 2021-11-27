package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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


}
