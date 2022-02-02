package pl.reskilled.menteeManagerMicroservices.project.exception.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ProjectControllerHandler {

    @ExceptionHandler(NameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ProjectErrorResponse developerConflict(NameExistsException exception){
        String message = "The name is exists to the db: " + exception.getInfo();
        log.info("Conflict: {" + exception.getMessage() + "}");
        return new ProjectErrorResponse(HttpStatus.CONFLICT, message);
    }
}
