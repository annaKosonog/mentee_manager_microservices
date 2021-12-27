package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(error -> errors.add((error.getDefaultMessage())));

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }
}
