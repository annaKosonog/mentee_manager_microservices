package pl.reskilled.menteeManagerMicroservices.mentee.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.NameExistsException;

@ControllerAdvice
@Slf4j
public class MenteeControllerHandler {

    @ExceptionHandler(NameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public MenteeErrorResponse developerConflict(NameExistsException exception) {
        String message = "The name is exists to the db: " + exception.getInfo();
        log.info("Conflict: {" + exception.getMessage() + "}");
        return new MenteeErrorResponse(HttpStatus.CONFLICT, message);
    }
}
