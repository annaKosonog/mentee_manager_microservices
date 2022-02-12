package pl.reskilled.menteeManagerMicroservices.teams.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class TeamControllerHandler {


    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public TeamErrorResponse projectNotFound(ProjectNotFoundException exception){
        final String message = String.format("Project with name %s not found" + exception.getName());
        log.info(message);
        return new TeamErrorResponse(HttpStatus.NOT_FOUND, message);
    }

    @ExceptionHandler(MenteeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public TeamErrorResponse menteeNotFound(MenteeNotFoundException exception){
        final String message = String.format("Mentee with name %s not found" + exception.getName());
        log.info(message);
        return new TeamErrorResponse(HttpStatus.NOT_FOUND, message);
    }
}
